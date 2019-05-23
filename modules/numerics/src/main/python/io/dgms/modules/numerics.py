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
import numpy as np;
import pyomo.environ as pyo
import sympy as sym
import tensorflow as tf;
import python.io.dgms.modules.symbolics as symbolics

from tensorflow.python.framework import ops

def add(a, b, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(a, sym.Basic) or isinstance(b, sym.Basic)):
            return a + b
        return np.add(a, b)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(a, pyo.NumericValue) or isinstance(b, pyo.NumericValue)):
            return a + b
        return np.add(a, b)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(a, (cas.DM, cas.SX)) or isinstance(b, (cas.DM, cas.SX))):
            return a + b
        return np.add(a, b)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(a, (cas.DM, cas.MX)) or isinstance(b, (cas.DM, cas.MX))):
            return a + b
        return np.add(a, b)
    if (isinstance(a, (tf.Tensor, tf.Variable))):
        if (isinstance(b, (tf.Tensor, tf.Variable))):
            return tf.add(a, b, name)
        return tf.add(a, tf.constant(b, a.dtype), name)
    if (isinstance(b, (tf.Tensor, tf.Variable))):
        return tf.add(tf.constant(a, b.dtype), b, name)
    return np.add(a, b)

def subtract(a, b, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(a, sym.Basic) or isinstance(b, sym.Basic)):
            return a - b
        return np.subtract(a, b)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(a, pyo.NumericValue) or isinstance(b, pyo.NumericValue)):
            return a - b
        return np.subtract(a, b)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(a, (cas.DM, cas.SX)) or isinstance(b, (cas.DM, cas.SX))):
            return a - b
        return np.subtract(a, b)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(a, (cas.DM, cas.MX)) or isinstance(b, (cas.DM, cas.MX))):
            return a - b
        return np.subtract(a, b)
    if (isinstance(a, (tf.Tensor, tf.Variable))):
        if (isinstance(b, (tf.Tensor, tf.Variable))):
            return tf.subtract(a, b, name)
        return tf.subtract(a, tf.constant(b, a.dtype), name)
    if (isinstance(b, (tf.Tensor, tf.Variable))):
        return tf.subtract(tf.constant(a, b.dtype), b, name)
    return np.subtract(a, b)

def multiply(a, b, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(a, sym.Basic) or isinstance(b, sym.Basic)):
            return a * b
        return np.multiply(a, b)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(a, pyo.NumericValue) or isinstance(b, pyo.NumericValue)):
            return a * b
        return np.multiply(a, b)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(a, (cas.DM, cas.SX)) or isinstance(b, (cas.DM, cas.SX))):
            return a * b
        return np.multiply(a, b)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(a, (cas.DM, cas.MX)) or isinstance(b, (cas.DM, cas.MX))):
            return a * b
        return np.multiply(a, b)
    if (isinstance(a, (tf.Tensor, tf.Variable))):
        if (isinstance(b, (tf.Tensor, tf.Variable))):
            return tf.multiply(a, b, name)
        return tf.multiply(a, tf.constant(b, a.dtype), name)
    if (isinstance(b, (tf.Tensor, tf.Variable))):
        return tf.multiply(tf.constant(a, b.dtype), b, name)
    return np.multiply(a, b)

def divide(a, b, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(a, sym.Basic) or isinstance(b, sym.Basic)):
            return a / b
        return np.divide(a, b)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(a, pyo.NumericValue) or isinstance(b, pyo.NumericValue)):
            return a / b
        return np.divide(a, b)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(a, (cas.DM, cas.SX)) or isinstance(b, (cas.DM, cas.SX))):
            return a / b
        return np.divide(a, b)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(a, (cas.DM, cas.MX)) or isinstance(b, (cas.DM, cas.MX))):
            return a / b
        return np.divide(a, b)
    if (isinstance(a, (tf.Tensor, tf.Variable))):
        if (isinstance(b, (tf.Tensor, tf.Variable))):
            return tf.divide(a, b, name)
        return tf.divide(a, tf.constant(b, a.dtype), name)
    if (isinstance(b, (tf.Tensor, tf.Variable))):
        return tf.divide(tf.constant(a, b.dtype), b, name)
    return np.divide(a, b)

def floor_divide(a, b, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(a, sym.Basic) or isinstance(b, sym.Basic)):
            return a // b
        return np.floor_divide(a, b)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(a, pyo.NumericValue) or isinstance(b, pyo.NumericValue)):
            return pyo.floor(a / b)
        return np.floor_divide(a, b)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(a, (cas.DM, cas.SX)) or isinstance(b, (cas.DM, cas.SX))):
            return a // b
        return np.floor_divide(a, b)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(a, (cas.DM, cas.MX)) or isinstance(b, (cas.DM, cas.MX))):
            return a // b
        return np.floor_divide(a, b)
    if (isinstance(a, (tf.Tensor, tf.Variable))):
        if (isinstance(b, (tf.Tensor, tf.Variable))):
            return tf.floor_div(a, b, name)
        return tf.floor_div(a, tf.constant(b, a.dtype), name)
    if (isinstance(b, (tf.Tensor, tf.Variable))):
        return tf.floor_div(tf.constant(a, b.dtype), b, name)
    return np.floor_divide(a, b)

def mod(a, b, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(a, sym.Basic) or isinstance(b, sym.Basic)):
            return a % b
        return np.mod(a, b)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(a, pyo.NumericValue) or isinstance(b, pyo.NumericValue)):
            return a % b
        return np.mod(a, b)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(a, (cas.DM, cas.SX)) or isinstance(b, (cas.DM, cas.SX))):
            return a % b
        return np.mod(a, b)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(a, (cas.DM, cas.MX)) or isinstance(b, (cas.DM, cas.MX))):
            return a % b
        return np.mod(a, b)
    if (isinstance(a, (tf.Tensor, tf.Variable))):
        if (isinstance(b, (tf.Tensor, tf.Variable))):
            return tf.mod(a, b, name)
        return tf.mod(a, tf.constant(b, a.dtype), name)
    if (isinstance(b, (tf.Tensor, tf.Variable))):
        return tf.mod(tf.constant(a, b.dtype), b, name)
    return np.mod(a, b)

def neg(x, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic)):
            return -x
        return np.negative(x)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue)):
            return -x
        return np.negative(x)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX))):
            return -x
        return np.negative(x)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX))):
            return -x
        return np.negative(x)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        return tf.negative(x, name)
    return np.negative(x)

def logical_and(a, b, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(a, sym.Basic) or isinstance(b, sym.Basic)):
            return sym.And(a, b)
        return np.logical_and(a, b)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(a, pyo.NumericValue) and isinstance(b, pyo.ConstraintList)):
            b.add(a)
            return b
        elif (isinstance(b, pyo.NumericValue) and isinstance(a, pyo.ConstraintList)):
            a.add(b)
            return a
        else:
            x = pyo.ConstraintList()
            x.construct()
            x.add(a)
            x.add(b)
            return x
        return np.logical_and(a, b)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(a, (cas.DM, cas.SX)) or isinstance(b, (cas.DM, cas.SX))):
            return cas.logic_and(a, b)
        return np.logical_and(a, b)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(a, (cas.DM, cas.MX)) or isinstance(b, (cas.DM, cas.MX))):
            return cas.logic_and(a, b)
        return np.logical_and(a, b)
    if (isinstance(a, (tf.Tensor, tf.Variable))):
        if (isinstance(b, (tf.Tensor, tf.Variable))):
            return tf.logical_and(a, b, name)
        return tf.logical_and(a, tf.constant(b, a.dtype), name)
    if (isinstance(b, (tf.Tensor, tf.Variable))):
        return tf.logical_and(tf.constant(a, b.dtype), b, name)
    return np.logical_and(a, b)

def logical_not(x, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic)):
            return not x
        return np.logical_not(x)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue)):
            return not x
        return np.logical_not(x)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX))):
            return not x
        return np.logical_not(x)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX))):
            return not x
        return np.logical_not(x)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        return tf.logical_not(tf.cast(x, tf.bool), name)
    return np.logical_not(x)

def logical_or(a, b, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(a, sym.Basic) or isinstance(b, sym.Basic)):
            return sym.Or(a, b)
        return np.logical_or(a, b)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(a, pyo.NumericValue) or isinstance(b, pyo.NumericValue)):
            return a | b
        return np.logical_or(a, b)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(a, (cas.DM, cas.SX)) or isinstance(b, (cas.DM, cas.SX))):
            return cas.logical_or(a, b)
        return np.logical_or(a, b)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(a, (cas.DM, cas.MX)) or isinstance(b, (cas.DM, cas.MX))):
            return cas.logical_or(a, b)
        return np.logical_or(a, b)
    if (isinstance(a, (tf.Tensor, tf.Variable))):
        if (isinstance(b, (tf.Tensor, tf.Variable))):
            return tf.logical_or(a, b, name)
        return tf.logical_or(a, tf.constant(b, a.dtype), name)
    if (isinstance(b, (tf.Tensor, tf.Variable))):
        return tf.logical_or(tf.constant(a, b.dtype), b, name)
    return np.logical_or(a, b)

def logical_xor(a, b, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(a, sym.Basic) or isinstance(b, sym.Basic)):
            return sym.Xor(a, b)
        return np.logical_xor(a, b)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(a, pyo.NumericValue) or isinstance(b, pyo.NumericValue)):
            return (a | b) & ~(a & b)
        return np.logical_xor(a, b)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(a, (cas.DM, cas.SX)) or isinstance(b, (cas.DM, cas.SX))):
            return cas.logic_and(cas.logic_or(a, b), cas.logic_not(cas.logic_and(a, b)))
        return np.logical_xor(a, b)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(a, (cas.DM, cas.MX)) or isinstance(b, (cas.DM, cas.MX))):
            return cas.logic_and(cas.logic_or(a, b), cas.logic_not(cas.logic_and(a, b)))
        return np.logical_xor(a, b)
    if (isinstance(a, (tf.Tensor, tf.Variable))):
        if (isinstance(b, (tf.Tensor, tf.Variable))):
            return tf.logical_xor(a, b, name)
        return tf.logical_xor(a, tf.constant(b, a.dtype), name)
    if (isinstance(b, (tf.Tensor, tf.Variable))):
        return tf.logical_xor(tf.constant(a, b.dtype), b, name)
    return np.logical_xor(a, b)


def all(x, axis=None, keep_dims=False, name=None):
    return None

def any(x, axis=None, keep_dims=False, name=None):
    return None

def equal(a, b, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(a, sym.Basic) or isinstance(b, sym.Basic)):
            return a == b
        return np.equal(a, b)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(a, pyo.NumericValue) or isinstance(b, pyo.NumericValue)):
            return a == b
        return np.equal(a, b)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(a, (cas.DM, cas.SX)) or isinstance(b, (cas.DM, cas.SX))):
            return cas.eq(a, b)
        return np.equal(a, b)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(a, (cas.DM, cas.MX)) or isinstance(b, (cas.DM, cas.MX))):
            return cas.eq(a, b)
        return np.equal(a, b)
    if (isinstance(a, (tf.Tensor, tf.Variable))):
        if (isinstance(b, (tf.Tensor, tf.Variable))):
            return tf.equal(a, b, name)
        return tf.equal(a, tf.constant(b, a.dtype), name)
    if (isinstance(b, (tf.Tensor, tf.Variable))):
        return tf.equal(tf.constant(a, b.dtype), b, name)
    return np.equal(a, b)

def greater(a, b, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(a, sym.Basic) or isinstance(b, sym.Basic)):
            return a > b
        return np.greater(a, b)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(a, pyo.NumericValue) or isinstance(b, pyo.NumericValue)):
            return a > b
        return np.greater(a, b)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(a, (cas.DM, cas.SX)) or isinstance(b, (cas.DM, cas.SX))):
            return cas.gt(a, b)
        return np.greater(a, b)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(a, (cas.DM, cas.MX)) or isinstance(b, (cas.DM, cas.MX))):
            return cas.gt(a, b)
        return np.greater(a, b)
    if (isinstance(a, (tf.Tensor, tf.Variable))):
        if (isinstance(b, (tf.Tensor, tf.Variable))):
            return tf.greater(a, b, name)
        return tf.greater(a, tf.constant(b, a.dtype), name)
    if (isinstance(b, (tf.Tensor, tf.Variable))):
        return tf.greater(tf.constant(a, b.dtype), b, name)
    return np.greater(a, b)

def greater_equal(a, b, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(a, sym.Basic) or isinstance(b, sym.Basic)):
            return a >= b
        return np.greater_equal(a, b)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(a, pyo.NumericValue) or isinstance(b, pyo.NumericValue)):
            return a >= b
        return np.greater_equal(a, b)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(a, (cas.DM, cas.SX)) or isinstance(b, (cas.DM, cas.SX))):
            return cas.ge(a, b)
        return np.greater_equal(a, b)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(a, (cas.DM, cas.MX)) or isinstance(b, (cas.DM, cas.MX))):
            return cas.ge(a, b)
        return np.greater_equal(a, b)
    if (isinstance(a, (tf.Tensor, tf.Variable))):
        if (isinstance(b, (tf.Tensor, tf.Variable))):
            return tf.greater_equal(a, b, name)
        return tf.greater_equal(a, tf.constant(b, a.dtype), name)
    if (isinstance(b, (tf.Tensor, tf.Variable))):
        return tf.greater_equal(tf.constant(a, b.dtype), b, name)
    return np.greater_equal(a, b)

def less(a, b, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(a, sym.Basic) or isinstance(b, sym.Basic)):
            return a < b
        return np.less(a, b)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(a, pyo.NumericValue) or isinstance(b, pyo.NumericValue)):
            return a < b
        return np.less(a, b)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(a, (cas.DM, cas.SX)) or isinstance(b, (cas.DM, cas.SX))):
            return cas.lt(a, b)
        return np.less(a, b)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(a, (cas.DM, cas.MX)) or isinstance(b, (cas.DM, cas.MX))):
            return cas.lt(a, b)
        return np.less(a, b)
    if (isinstance(a, (tf.Tensor, tf.Variable))):
        if (isinstance(b, (tf.Tensor, tf.Variable))):
            return tf.less(a, b, name)
        return tf.less(a, tf.constant(b, a.dtype), name)
    if (isinstance(b, (tf.Tensor, tf.Variable))):
        return tf.less(tf.constant(a, b.dtype), b, name)
    return np.less(a, b)

def less_equal(a, b, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(a, sym.Basic) or isinstance(b, sym.Basic)):
            return a <= b
        return np.less_equal(a, b)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(a, pyo.NumericValue) or isinstance(b, pyo.NumericValue)):
            return a <= b
        return np.less_equal(a, b)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(a, (cas.DM, cas.SX)) or isinstance(b, (cas.DM, cas.SX))):
            return cas.le(a, b)
        return np.less_equal(a, b)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(a, (cas.DM, cas.MX)) or isinstance(b, (cas.DM, cas.MX))):
            return cas.le(a, b)
        return np.less_equal(a, b)
    if (isinstance(a, (tf.Tensor, tf.Variable))):
        if (isinstance(b, (tf.Tensor, tf.Variable))):
            return tf.less_equal(a, b, name)
        return tf.less_equal(a, tf.constant(b, a.dtype), name)
    if (isinstance(b, (tf.Tensor, tf.Variable))):
        return tf.less_equal(tf.constant(a, b.dtype), b, name)
    return np.less_equal(a, b)

def not_equal(a, b, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(a, sym.Basic) or isinstance(b, sym.Basic)):
            return a != b
        return np.not_equal(a, b)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(a, pyo.NumericValue) or isinstance(b, pyo.NumericValue)):
            return a != b
        return np.not_equal(a, b)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(a, (cas.DM, cas.SX)) or isinstance(b, (cas.DM, cas.SX))):
            return cas.ne(a, b)
        return np.not_equal(a, b)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(a, (cas.DM, cas.MX)) or isinstance(b, (cas.DM, cas.MX))):
            return cas.ne(a, b)
        return np.not_equal(a, b)
    if (isinstance(a, (tf.Tensor, tf.Variable))):
        if (isinstance(b, (tf.Tensor, tf.Variable))):
            return tf.not_equal(a, b, name)
        return tf.not_equal(a, tf.constant(b, a.dtype), name)
    if (isinstance(b, (tf.Tensor, tf.Variable))):
        return tf.not_equal(tf.constant(a, b.dtype), b, name)
    return np.not_equal(a, b)

def where(condition, a, b, name=None):
    if (symbolics.mode == symbolics.PYOMO):
        return pyo.Expr_if(IF=condition, THEN=a, ELSE=b)
    if (symbolics.mode == symbolics.CASADI_SX):
        return cas.if_else(condition, a, b)
    if (symbolics.mode == symbolics.CASADI_MX):
        return cas.if_else(condition, a, b)
    if (isinstance(a, (tf.Tensor, tf.Variable)) or isinstance(b, (tf.Tensor, tf.Variable))):
        return tf.where(tf.cast(condition, tf.bool), tf.cast(a, tf.float64), tf.cast(b, tf.float64), name)
    return np.where(condition, a, b)

def abs(x, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic)):
            return __builtins__.abs(x)
        return np.abs(x)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue)):
            return __builtins__.abs(x)
        return np.abs(x)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX))):
            return __builtins__.abs(x)
        return np.abs(x)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX))):
            return __builtins__.abs(x)
        return np.abs(x)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        return tf.abs(x, name)
    return np.abs(x)

def ceil(x, name=None):
    return None

def floor(x, name=None):
    return None

def round(x, name=None):
    return None

def pow(a, b, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(a, sym.Basic) or isinstance(b, sym.Basic)):
            return a ** b
        return np.power(a, b)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(a, pyo.NumericValue) or isinstance(b, pyo.NumericValue)):
            return a ** b
        return np.power(a, b)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(a, (cas.DM, cas.SX)) or isinstance(b, (cas.DM, cas.SX))):
            return cas.power(a, b)
        return np.power(a, b)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(a, (cas.DM, cas.MX)) or isinstance(b, (cas.DM, cas.MX))):
            return cas.power(a, b)
        return np.power(a, b)
    if (isinstance(a, (tf.Tensor, tf.Variable))):
        if (isinstance(b, (tf.Tensor, tf.Variable))):
            return tf.pow(a, b, name)
        return np.power(a, b)
    if (isinstance(b, (tf.Tensor, tf.Variable))):
        return tf.pow(tf.constant(a, b.dtype), b, name)
    return np.power(a, b)

def reduce_sum(x, axis=None, keep_dims=False, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        return np.sum(x, axis)
    if (symbolics.mode == symbolics.PYOMO):
        return np.sum(x, axis)
    if (symbolics.mode == symbolics.CASADI_SX):
        return np.sum(x, axis)
    if (symbolics.mode == symbolics.CASADI_MX):
        return np.sum(x, axis)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        return tf.reduce_sum(x, axis, keep_dims, name)
    return np.sum(x, axis)

def acos(x, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic)):
            return sym.acos(x)
        return np.acos(x)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue)):
            return pyo.acos(x)
        return np.acos(x)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX))):
            return cas.acos(x)
        return np.acos(x)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX))):
            return cas.acos(x)
        return np.acos(x)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        return tf.acos(x, name)
    return np.acos(x)

def acosh(x, name=None):
    return None

def asin(x, name=None):
    return None

def asinh(x, name=None):
    return None

def atan(x, name=None):
    return None

def atan2(y, x, name=None):
    return None

def atanh(x, name=None):
    return None

def cos(x, name=None):
    return None

def cosh(x, name=None):
    return None

def exp(x, name=None):
    return None

def log(x, name=None):
    return None

def sin(x, name=None):
    return None

def sinh(x, name=None):
    return None

def sqrt(x, name=None):
    return None

def square(x, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic)):
            return x * x
        return np.square(x)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue)):
            return x * x
        return np.square(x)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX))):
            return cas.acos(x)
        return np.square(x)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX))):
            return x * x
        return np.square(x)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        return tf.square(x, name)
    return np.square(x)

def tan(x, name=None):
    return None

def tanh(x, name=None):
    return None

def dtype(x):
    return None

def constant(x, type=None):
    return None


def asarray(*args, **kwargs):
    return np.asarray(*args, **kwargs);

def linspace(*args, **kwargs):
    return np.linspace(*args, **kwargs)
