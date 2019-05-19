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

import os
import casadi as cas
import pyomo.environ as pyo
import sympy as sym
import tensorflow as tf

SYMPY = 1
PYOMO = 2
CASADI_SX = 3
CASADI_MX = 4
TENSORFLOW = 5

function_seq = 0
functions_dict = dict()
parameter_seq = 0
parameters_dict = dict()
variable_seq = 0
variables_dict = dict()

mode = 2

def function():
    return None

def get_mode():
    global mode
    if (mode == SYMPY):
        return "sympy"
    if (mode == PYOMO):
        return "pyomo"
    if (mode == CASADI_SX):
        return "casadi-sx"
    if (mode == CASADI_MX):
        return "casadi-mx"
    if (mode == TENSORFLOW):
        return "tensorflow"
    return "sympy"

def parameter(value=None, name=None, shape=None, dtype=tf.float64):

    global mode, parameter_seq, parameter_dict
    parameter = None
    rank = len(shape if shape is not None else [])

    if (name == None):
        parameter_seq += 1
        name = "Parameter_" + str(parameter_seq)

    if (mode == PYOMO):
        if (rank == 0):
            parameter = pyo.Param(default=value)
        else:
            raise ValueError("invalid rank: " + str(rank))
        parameters_dict[id(parameter)] = { "value": value, "name": name }

    elif (mode == CASADI_SX):
        if (rank == 0):
            parameter = cas.SX.sym(name)
        elif (rank == 1):
            parameter = cas.SX.sym(name, shape[0])
        elif (rank == 2):
            parameter = cas.SX.sym(name, shape[0], shape[1])
        else:
            raise ValueError("invalid rank: " + str(rank))
        parameters_dict[str(parameter.__hash__())] = { "value": value, "name": name }

    elif (mode == CASADI_MX):
        if (rank == 0):
            parameter = cas.MX.sym(name)
        elif (rank == 1):
            parameter = cas.MX.sym(name, shape[0])
        elif (rank == 2):
            parameter = cas.MX.sym(name, shape[0], shape[1])
        else:
            raise ValueError("invalid rank: " + str(rank))
        parameters_dict[str(parameter.__hash__())] = { "value": value, "name": name }

    elif (mode == TENSORFLOW):
        if (value is None and shape is not None):
            value = tf.random.uniform(shape, minval=dtype.min, maxval=dtype.max, dtype=dtype)
        parameter = tf.Variable(value, name=name, dtype=tf.as_dtype(dtype), expected_shape=shape, trainable=True)    
        parameters_dict[parameter.op] = { "value": value, "name": name }

    else:
        if (rank == 0):
            parameter = sym.Symbol(name)
            parameters_dict[parameter] = { "value": value, "name": name }
        else:
            raise ValueError("invalid rank: " + str(rank))

    return parameter

def placeholder(name=None, shape=None, dtype=tf.float64):    
    global mode
    placeholder = None
    if (mode == TENSORFLOW):
        placeholder = tf.placeholder(dtype=dtype, name=name, shape=shape)
    else:
        raise ValueError("invalid mode: " + str(mode))    
    return placeholder

def id(value):
    if (mode == SYMPY):
        return value
    if (mode == PYOMO):
        return value
    if (mode == CASADI_SX):
        return str(value.__hash__())
    if (mode == CASADI_MX):
        return str(value.__hash__())
    if (mode == TENSORFLOW):
        return value.op
    return None

def reference():
    return None

def set_mode(value):
    global mode
    if (value.lower() == "sympy"):
        mode = SYMPY
    elif (value.lower() == "pyomo"):
        mode = PYOMO
    elif (value.lower() == "casadi-sx"):
        mode = CASADI_SX
    elif (value.lower() == "casadi-mx"):
        mode = CASADI_MX
    elif (value.lower() == "tensorflow"):
        mode = TENSORFLOW
    else:
        raise ValueError("invalid mode: " + value + ", must be one of ['sympy', 'pyomo', 'casadi-sx', 'casadi-mx', 'tensorflow']")


def variable(initialize=None, name=None, shape=[], dtype=tf.float64, bounds=None, domain=None):

    global mode, variable_seq, variables_dict
    variable = None
    rank = len(shape)

    if (name == None):
        variable_seq += 1
        name = "Variable_" + str(variable_seq)

    if (mode == PYOMO):
        if (rank == 0):
            if (domain == "binary"):
                variable = pyo.Var(initialize=initialize, bounds=bounds, domain=pyo.Binary)
            elif (domain == "integer"):
                variable = pyo.Var(initialize=initialize, bounds=bounds, domain=pyo.Integers)
            else:
                variable = pyo.Var(initialize=initialize, bounds=bounds, domain=pyo.Reals)
            variable._name = name
            variables_dict[str(id(variable))] = { "bounds": bounds if (bounds is not None) else [tf.as_dtype(dtype).min, tf.as_dtype(dtype).max], "domain": domain, "initialize": initialize, "name": name }
        else:
            raise ValueError("invalid rank: " + str(rank))

    elif (mode == CASADI_SX):
        if (rank == 0):
            variable = cas.SX.sym(name)
        elif (rank == 1):
            variable = cas.SX.sym(name, shape[0])
        elif (rank == 2):
            variable = cas.SX.sym(name, shape[0], shape[1])
        else:
            raise ValueError("invalid rank: " + str(rank))
        variables_dict[str(variable.__hash__())] = { "bounds": bounds if (bounds is not None) else [tf.as_dtype(dtype).min, tf.as_dtype(dtype).max], "domain": domain, "initialize": initialize, "name": name }

    elif (mode == CASADI_MX):
        if (rank == 0):
            variable = cas.MX.sym(name)
        elif (rank == 1):
            variable = cas.MX.sym(name, shape[0])
        elif (rank == 2):
            variable = cas.MX.sym(name, shape[0], shape[1])
        else:
            raise ValueError("invalid rank: " + str(rank))
        variables_dict[str(variable.__hash__())] = { "bounds": bounds if (bounds is not None) else [tf.as_dtype(dtype).min, tf.as_dtype(dtype).max], "domain": domain, "initialize": initialize, "name": name }

    elif (mode == TENSORFLOW):
        if (initialize is None):
            if (bounds is not None):
                initialize = tf.random.uniform(shape, minval=bounds[0], maxval=bounds[1], dtype=dtype)
            else:
                initialize = tf.random.uniform(shape, minval=-100, maxval=100, dtype=dtype)
        variable = tf.Variable(initialize, name=name, dtype=dtype, expected_shape=shape, trainable=False)    
        variables_dict[variable.op] = { "bounds": bounds if (bounds is not None) else [tf.as_dtype(dtype).min, tf.as_dtype(dtype).max], "domain": domain, "initialize": initialize, "name": name }

    else:
        if (rank == 0):
            variable = sym.Symbol(name)
            variables_dict[variable] = { "bounds": bounds if (bounds is not None) else [tf.as_dtype(dtype).min, tf.as_dtype(dtype).max], "domain": domain, "initialize": initialize, "name": name }
        else:
            raise ValueError("invalid rank: " + str(rank))

    return variable

set_mode(os.environ['SYMBOLICS_MODE'])
