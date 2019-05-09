#
# بسم الله الرحمن الرحيم 
#
# In the name of Allah, the Most Compassionate, the Most Merciful
#
# This file is part of Unity DGMS <https://www.dgms.io/>
#
# Unity DGMS is free software; redistribution and use in source and binary
# forms, with or without modification, are permitted provided that the
# following conditions are met:
#
# 1. Redistributions of source code must retain the above notice, this list of
#    conditions and the following disclaimer.
#
# 2. Redistributions in binary form must reproduce the above notice, this list
#    of conditions and the following disclaimer in the documentation and/or
#    other materials provided with the distribution.
#
# THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHORS DISCLAIM ALL WARRANTIES
# WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
# MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY
# SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
# WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION
# OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN
# CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
#

import casadi as cas;
import collections
import numpy as np
import pickle
import plyvel as plyvel
import pygmo as pg
import pyomo.environ as pyo
import python.io.dgms.modules.symbolics as symbolics
import shutil
import tensorflow as tf

problem_seq = 0
problems_dict = dict()
surrogates_dict = dict()

def solve(*args, **kwargs):

    objectives = collections.OrderedDict()
    constraints = collections.OrderedDict()
    options = kwargs.get("options", {}) if kwargs.get("options", {}) is not None else {}

    f = 1;
    minimize = kwargs.get("minimize", None)
    if (isinstance(minimize, dict)):
        for key, objective in minimize.items():
            objectives[key] = objective
    elif (isinstance(minimize, (list, tuple))):
        for objective in minimize:
            objectives["obj" + str(f)] = objective
            f += 1
    elif (minimize is not None):
        objectives["obj" + str(f)] = minimize
        f += 1

    maximize = kwargs.get("maximize", None)
    if (isinstance(maximize, dict)):
        for key, objective in maximize.items():
            objectives[key] = -objective
    elif (isinstance(maximize, (list, tuple))):
        for objective in maximize:
            objectives["obj" + str(f)] = -objective
            f += 1
    elif (maximize is not None):
        objectives["obj" + str(f)] = -maximize
        f += 1

    g = 1
    subject_to = kwargs.get("subject-to", None)
    if (isinstance(subject_to, dict)):
        for key, constraint in subject_to.items():
            constraints[key] = constraint
    elif (isinstance(subject_to, (list, tuple))):
        for constraint in subject_to:
            constraints["con" + str(g)] = constraint
            g += 1
    elif (subject_to is not None):
        constraints["con" + str(g)] = subject_to
        g += 1

    if (symbolics.mode == symbolics.PYOMO):
        return solve_pyomo(objectives, constraints, options)
    if (symbolics.mode == symbolics.CASADI_SX):
        return solve_casadi(objectives, constraints, options)
    if (symbolics.mode == symbolics.CASADI_MX):
        return solve_casadi(objectives, constraints, options)
    if (symbolics.mode == symbolics.TENSORFLOW):
        return solve_tensorflow(objectives, constraints, options)
    raise ValueError("invalid mode: " + symbolics.get_mode())

def solve_pyomo(objectives, constraints, options={}):

    model = pyo.ConcreteModel()
    variables = dict()

    for key, objective in objectives.items():
        setattr(model, key, pyo.Objective(expr=objective))
        add_pyomo_variables(objective, variables)

    for key, constraint in constraints.items():
        setattr(model, key, pyo.Constraint(expr=constraint))
        add_pyomo_variables(constraint, variables)

    for variable in variables.values():
        setattr(model, str(id(variable)), variable)

    opt = pyo.SolverFactory(options.get("solver", "bonmin"))
    
    result = opt.solve(model)
    #result.write(num=1)
    return { str(id(variable)) : variable.value for variable in variables.values() }

def add_pyomo_variables(expression, variables):

    stack = [expression]

    while (stack):
        expression = stack.pop()
        if (hasattr(expression, 'is_variable_type') and expression.is_variable_type()):
            variables[id(expression)] = expression
        elif (hasattr(expression, 'nargs')):
            for i in range(expression.nargs()):
                stack.append(expression.arg(i))

def solve_casadi(objectives, constraints, options={}):
    
    variables = dict()

    lbx = list()
    ubx = list()
    lbg = list()
    ubg = list()
    
    f = list()
    for key, objective in objectives.items():
        f.append(objective)
        add_casadi_variables(objective, variables)

    g = list()
    for key, constraint in constraints.items():
        if (constraint.is_op(cas.OP_EQ)):
            g.append(constraint.dep(0) - constraint.dep(1))
            lbg.append(0)
            ubg.append(0)
        elif (constraint.is_op(cas.OP_LE)):
            g.append(constraint.dep(0) - constraint.dep(1))
            lbg.append(-cas.inf)
            ubg.append(0)
        else:
            raise ValueError("invalid constraint")
        add_casadi_variables(constraint, variables)

    discrete = list()
    for variable in variables.values():
        print("GOOD", symbolics.variables_dict)
        domain = symbolics.variables_dict[variable].get("domain", "")
        print("GOOD1")
        if (domain == "integer"):
            discrete.append(True)
        else:
            discrete.append(False)
        bounds = symbolics.variables_dict[variable].get("bounds", [-cas.inf, cas.inf])
        lbx.append(bounds[0])
        ubx.append(bounds[1])

    nlp = { 'x': cas.vertcat(*variables.values()), 'f': cas.vertcat(*f), 'g': cas.vertcat(*g) }
    solver = cas.nlpsol(options.get("name", "problem"), options.get("solver", "bonmin"), nlp, { "discrete": discrete })
    solution = solver(lbx=lbx, ubx=ubx, lbg=lbg, ubg=ubg)
    
    return { variable : value for variable, value in zip(variables.values(), solution.get("x").elements()) }


def add_casadi_variables(expression, variables):

    stack = [expression]

    while (stack):
        expression = stack.pop()
        if (expression.is_symbolic()):
            variables[expression] = expression
        else:
            for i in range(expression.n_dep()):
                stack.append(expression.dep(i))

def solve_tensorflow(objectives, constraints, options={}):

    with tf.Session() as session:

        problem = UnityTensorflowProblem(session, objectives, constraints, options)
        session.run(tf.global_variables_initializer())
        prob = pg.problem(problem)
        algo = pg.algorithm(pg.ihs())
        pop = pg.population(prob, 10)
        
        for i in range(1000):
            pop = algo.evolve(pop)
        print(pop)

class UnityTensorflowProblem:

    def __init__(self, session, objectives, constraints, options=dict()):
        
        global problem_seq
        
        self.problem = problem_seq; problem_seq += 1
        self.obj, self.ec, self.ic = list(), list(), list()
        self.session = session
        self.options = options

        for key, objective in objectives.items():
            self.obj.append(objective)
        
        for key, constraint in constraints.items():
            if (constraint.op.type == "Equal"):
                self.ec.append(constraint.op.inputs[0] - constraint.op.inputs[1])
            elif (constraint.op.type == "LessEqual"):
                self.ic.append(constraint.op.inputs[0] - constraint.op.inputs[1])
            elif (constraint.op.type == "GreaterEqual"):
                self.ic.append(constraint.op.inputs[1] - constraint.op.inputs[0])
            else:
                raise ValueError("invalid constraint: " + constraint.op.type)

        self._fitness = self.obj + self.ec + self.ic
        
        self.cx, self.ix, self.bx = set(), set(), set()
        for expression in self._fitness:
            self.add_variables(expression)
        self.variables = list(self.cx) + list(self.ix) + list(self.bx)

        self.bounds = (
            [symbolics.variables_dict[variable.op]["bounds"][0] for variable in self.variables],
            [symbolics.variables_dict[variable.op]["bounds"][1] for variable in self.variables])

    def fitness(self, x):
        values = self.session.run(self._fitness, feed_dict={variable: value for variable, value in zip(self.variables, x)})
        fitness = [ value.item() for value in values ]
        return fitness

    '''
    def fitness_surrogate(self, x):
        values = self.session.run(self._fitness_surrogate, feed_dict={variable: value for variable, value in zip(self.variables, x)})
        fitness_surrogate = [ value.item() for value in values ]
        return fitness_surrogate
    '''

    def get_bounds(self):
        return self.bounds

    def get_nobj(self):
        return len(self.obj)

    def get_nec(self):
        return len(self.ec)

    def get_nic(self):
        return len(self.ic)

    def get_nix(self):
        return len(self.ix) + len(self.bx)

    '''
    def has_gradient(self):
        return True

    def gradient(self, x):
        values = self.session.run(self._gradient, feed_dict={variable: value for variable, value in zip(self.variables, x)})
        gradient = [ value.item() for value in values ]
        return gradient

    def gradient_surrogate(self, x):
        values = self.session.run(self._gradient_surrogate, feed_dict={variable: value for variable, value in zip(self.variables, x)})
        gradient_surrogate = [ value.item() for value in values ]
        return gradient_surrogate

    def has_hessians(self):
        return True

    def hessians(self, x):
        values = self.session.run(self._hessians, feed_dict={variable: value for variable, value in zip(self.variables, x)})
        hessians = [ value.item() for value in values ]
        return hessians
    
    def hessians_surrogate(self, x):
        values = self.session.run(self._hessians_surrogate, feed_dict={variable: value for variable, value in zip(self.variables, x)})
        _hessians_surrogate = [ value.item() for value in values ]
        return _hessians_surrogate
    '''

    def get_name(self):
        return self.options.get("name", "TensorflowProblem")

    def get_extra_info(self):
        return self.options.get("info", "")

    def add_variables(self, expression):

        stack = [expression]
        while (stack):
            expression = stack.pop()
            if (expression.op.type == "VariableV2"):
                domain = symbolics.variables_dict[expression.op]["domain"]
                if (domain == "binary"):
                    self.bx.add(expression)
                elif (domain == "integer"):
                    self.ix.add(expression)
                else:
                    self.cx.add(expression)
            else:
                for input in expression.op.inputs:
                    stack.append(input)

    def __getstate__(self):
        
        global problems_dict
        
        problems_dict[self.problem] = self.__dict__
        state = dict()
        state["problem"] = self.problem
        return state

    def __setstate__(self, dict):
        
        global problems_dict
        
        self.__dict__ = problems_dict[dict["problem"]]
