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

from builtins import list
import casadi as cas;
import numpy as np;
import pyomo.environ as pyo
import sympy as sym
import tensorflow as tf
import python.io.dgms.modules.symbolics as symbolics

def add(x, y, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic) or isinstance(y, sym.Basic)):
            return x + y
        return np.add(x, y)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue) or isinstance(y, pyo.NumericValue)):
            return x + y
        return np.add(x, y)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX)) or isinstance(y, (cas.DM, cas.SX))):
            return x + y
        return np.add(x, y)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX)) or isinstance(y, (cas.DM, cas.MX))):
            return x + y
        return np.add(x, y)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        if (isinstance(y, (tf.Tensor, tf.Variable))):
            return tf.add(x, y, name)
        return tf.add(x, tf.constant(y, x.dtype), name)
    if (isinstance(y, (tf.Tensor, tf.Variable))):
        return tf.add(tf.constant(x, y.dtype), y, name)
    return np.add(x, y)

def subtract(x, y, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic) or isinstance(y, sym.Basic)):
            return x - y
        return np.subtract(x, y)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue) or isinstance(y, pyo.NumericValue)):
            return x - y
        return np.subtract(x, y)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX)) or isinstance(y, (cas.DM, cas.SX))):
            return x - y
        return np.subtract(x, y)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX)) or isinstance(y, (cas.DM, cas.MX))):
            return x - y
        return np.subtract(x, y)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        if (isinstance(y, (tf.Tensor, tf.Variable))):
            return tf.subtract(x, y, name)
        return tf.subtract(x, tf.constant(y, x.dtype), name)
    if (isinstance(y, (tf.Tensor, tf.Variable))):
        return tf.subtract(tf.constant(x, y.dtype), y, name)
    return np.subtract(x, y)

def multiply(x, y, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic) or isinstance(y, sym.Basic)):
            return x * y
        return np.multiply(x, y)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue) or isinstance(y, pyo.NumericValue)):
            return x * y
        return np.multiply(x, y)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX)) or isinstance(y, (cas.DM, cas.SX))):
            return x * y
        return np.multiply(x, y)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX)) or isinstance(y, (cas.DM, cas.MX))):
            return x * y
        return np.multiply(x, y)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        if (isinstance(y, (tf.Tensor, tf.Variable))):
            return tf.multiply(x, y, name)
        return tf.multiply(x, tf.constant(y, x.dtype), name)
    if (isinstance(y, (tf.Tensor, tf.Variable))):
        return tf.multiply(tf.constant(x, y.dtype), y, name)
    return np.multiply(x, y)

def divide(x, y, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic) or isinstance(y, sym.Basic)):
            return x / y
        return np.divide(x, y)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue) or isinstance(y, pyo.NumericValue)):
            return x / y
        return np.divide(x, y)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX)) or isinstance(y, (cas.DM, cas.SX))):
            return x / y
        return np.divide(x, y)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX)) or isinstance(y, (cas.DM, cas.MX))):
            return x / y
        return np.divide(x, y)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        if (isinstance(y, (tf.Tensor, tf.Variable))):
            return tf.divide(x, y, name)
        return tf.divide(x, tf.constant(y, x.dtype), name)
    if (isinstance(y, (tf.Tensor, tf.Variable))):
        return tf.divide(tf.constant(x, y.dtype), y, name)
    return np.divide(x, y)

def floor_divide(x, y, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic) or isinstance(y, sym.Basic)):
            return x // y
        return np.floor_divide(x, y)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue) or isinstance(y, pyo.NumericValue)):
            return pyo.floor(x / y)
        return np.floor_divide(x, y)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX)) or isinstance(y, (cas.DM, cas.SX))):
            return x // y
        return np.floor_divide(x, y)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX)) or isinstance(y, (cas.DM, cas.MX))):
            return x // y
        return np.floor_divide(x, y)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        if (isinstance(y, (tf.Tensor, tf.Variable))):
            return tf.floor_div(x, y, name)
        return tf.floor_div(x, tf.constant(y, x.dtype), name)
    if (isinstance(y, (tf.Tensor, tf.Variable))):
        return tf.floor_div(tf.constant(x, y.dtype), y, name)
    return np.floor_divide(x, y)

def mod(x, y, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic) or isinstance(y, sym.Basic)):
            return x % y
        return np.mod(x, y)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue) or isinstance(y, pyo.NumericValue)):
            return x - pyo.floor(x / y) * y 
        return np.mod(x, y)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX)) or isinstance(y, (cas.DM, cas.SX))):
            return x % y
        return np.mod(x, y)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX)) or isinstance(y, (cas.DM, cas.MX))):
            return x % y
        return np.mod(x, y)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        if (isinstance(y, (tf.Tensor, tf.Variable))):
            return tf.mod(x, y, name)
        return tf.mod(x, tf.constant(y, x.dtype), name)
    if (isinstance(y, (tf.Tensor, tf.Variable))):
        return tf.mod(tf.constant(x, y.dtype), y, name)
    return np.mod(x, y)

def negative(x, name=None):
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

def equal(x, y, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic) or isinstance(y, sym.Basic)):
            return x == y
        return np.equal(x, y)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue) or isinstance(y, pyo.NumericValue)):
            return x == y
        return np.equal(x, y)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX)) or isinstance(y, (cas.DM, cas.SX))):
            return cas.eq(x, y)
        return np.equal(x, y)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX)) or isinstance(y, (cas.DM, cas.MX))):
            return cas.eq(x, y)
        return np.equal(x, y)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        if (isinstance(y, (tf.Tensor, tf.Variable))):
            return tf.equal(x, y, name)
        return tf.equal(x, tf.constant(y, x.dtype), name)
    if (isinstance(y, (tf.Tensor, tf.Variable))):
        return tf.equal(tf.constant(x, y.dtype), y, name)
    return np.equal(x, y)

def greater(x, y, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic) or isinstance(y, sym.Basic)):
            return x > y
        return np.greater(x, y)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue) or isinstance(y, pyo.NumericValue)):
            return x > y
        return np.greater(x, y)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX)) or isinstance(y, (cas.DM, cas.SX))):
            return cas.gt(x, y)
        return np.greater(x, y)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX)) or isinstance(y, (cas.DM, cas.MX))):
            return cas.gt(x, y)
        return np.greater(x, y)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        if (isinstance(y, (tf.Tensor, tf.Variable))):
            return tf.greater(x, y, name)
        return tf.greater(x, tf.constant(y, x.dtype), name)
    if (isinstance(y, (tf.Tensor, tf.Variable))):
        return tf.greater(tf.constant(x, y.dtype), y, name)
    return np.greater(x, y)

def greater_equal(x, y, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic) or isinstance(y, sym.Basic)):
            return x >= y
        return np.greater_equal(x, y)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue) or isinstance(y, pyo.NumericValue)):
            return x >= y
        return np.greater_equal(x, y)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX)) or isinstance(y, (cas.DM, cas.SX))):
            return cas.ge(x, y)
        return np.greater_equal(x, y)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX)) or isinstance(y, (cas.DM, cas.MX))):
            return cas.ge(x, y)
        return np.greater_equal(x, y)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        if (isinstance(y, (tf.Tensor, tf.Variable))):
            return tf.greater_equal(x, y, name)
        return tf.greater_equal(x, tf.constant(y, x.dtype), name)
    if (isinstance(y, (tf.Tensor, tf.Variable))):
        return tf.greater_equal(tf.constant(x, y.dtype), y, name)
    return np.greater_equal(x, y)

def less(x, y, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic) or isinstance(y, sym.Basic)):
            return x < y
        return np.less(x, y)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue) or isinstance(y, pyo.NumericValue)):
            return x < y
        return np.less(x, y)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX)) or isinstance(y, (cas.DM, cas.SX))):
            return cas.lt(x, y)
        return np.less(x, y)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX)) or isinstance(y, (cas.DM, cas.MX))):
            return cas.lt(x, y)
        return np.less(x, y)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        if (isinstance(y, (tf.Tensor, tf.Variable))):
            return tf.less(x, y, name)
        return tf.less(x, tf.constant(y, x.dtype), name)
    if (isinstance(y, (tf.Tensor, tf.Variable))):
        return tf.less(tf.constant(x, y.dtype), y, name)
    return np.less(x, y)

def less_equal(x, y, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic) or isinstance(y, sym.Basic)):
            return x <= y
        return np.less_equal(x, y)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue) or isinstance(y, pyo.NumericValue)):
            return x <= y
        return np.less_equal(x, y)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX)) or isinstance(y, (cas.DM, cas.SX))):
            return cas.le(x, y)
        return np.less_equal(x, y)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX)) or isinstance(y, (cas.DM, cas.MX))):
            return cas.le(x, y)
        return np.less_equal(x, y)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        if (isinstance(y, (tf.Tensor, tf.Variable))):
            return tf.less_equal(x, y, name)
        return tf.less_equal(x, tf.constant(y, x.dtype), name)
    if (isinstance(y, (tf.Tensor, tf.Variable))):
        return tf.less_equal(tf.constant(x, y.dtype), y, name)
    return np.less_equal(x, y)

def not_equal(x, y, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic) or isinstance(y, sym.Basic)):
            return x != y
        return np.not_equal(x, y)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue) or isinstance(y, pyo.NumericValue)):
            raise Exception("not_equal operator not supported with Pyomo")
        return np.not_equal(x, y)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX)) or isinstance(y, (cas.DM, cas.SX))):
            return cas.ne(x, y)
        return np.not_equal(x, y)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX)) or isinstance(y, (cas.DM, cas.MX))):
            return cas.ne(x, y)
        return np.not_equal(x, y)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        if (isinstance(y, (tf.Tensor, tf.Variable))):
            return tf.not_equal(x, y, name)
        return tf.not_equal(x, tf.constant(y, x.dtype), name)
    if (isinstance(y, (tf.Tensor, tf.Variable))):
        return tf.not_equal(tf.constant(x, y.dtype), y, name)
    return np.not_equal(x, y)

def logical_and(x, y, name=None):
    if (isinstance(x, bool)):
        if (not x):
            return False
        return y
    if (isinstance(y, bool)):
        if (not y):
            return False
        return x
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic) or isinstance(y, sym.Basic)):
            return sym.And(x, y)
        return np.logical_and(x, y)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, (list, tuple))):
            if (isinstance(y, (list, tuple))):
                return x + y
            return x + (y,)
        if (isinstance(y, (list, tuple))):
            return (x,) + y
        return (x, y)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX)) or isinstance(y, (cas.DM, cas.SX))):
            return cas.logic_and(x, y)
        return np.logical_and(x, y)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX)) or isinstance(y, (cas.DM, cas.MX))):
            return cas.logic_and(x, y)
        return np.logical_and(x, y)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        if (isinstance(y, (tf.Tensor, tf.Variable))):
            return tf.logical_and(x, y, name)
        return tf.logical_and(x, tf.constant(y, x.dtype), name)
    if (isinstance(y, (tf.Tensor, tf.Variable))):
        return tf.logical_and(tf.constant(x, y.dtype), y, name)
    return np.logical_and(x, y)

def logical_or(x, y, name=None):
    if (isinstance(x, bool)):
        if (x):
            return True
        return y
    if (isinstance(y, bool)):
        if (y):
            return True
        return x
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic) or isinstance(y, sym.Basic)):
            return sym.Or(x, y)
        return np.logical_or(x, y)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue) or isinstance(b, pyo.NumericValue)):
            raise Exception("logical_or operator not supported with Pyomo")
        return np.logical_or(x, b)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX)) or isinstance(b, (cas.DM, cas.SX))):
            return cas.logical_or(x, b)
        return np.logical_or(x, b)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX)) or isinstance(b, (cas.DM, cas.MX))):
            return cas.logical_or(x, b)
        return np.logical_or(x, b)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        if (isinstance(b, (tf.Tensor, tf.Variable))):
            return tf.logical_or(x, b, name)
        return tf.logical_or(x, tf.constant(b, x.dtype), name)
    if (isinstance(b, (tf.Tensor, tf.Variable))):
        return tf.logical_or(tf.constant(x, b.dtype), b, name)
    return np.logical_or(x, b)

def logical_not(x, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic)):
            return not x
        return np.logical_not(x)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue)):
            raise Exception("logical_not operator not supported with Pyomo")
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

def logical_xor(x, y, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic) or isinstance(y, sym.Basic)):
            return sym.Xor(x, y)
        return np.logical_xor(x, y)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue) or isinstance(y, pyo.NumericValue)):
            raise Exception("logical_xor operator not supported with Pyomo")
        return np.logical_xor(x, y)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX)) or isinstance(y, (cas.DM, cas.SX))):
            return cas.logic_and(cas.logic_or(x, y), cas.logic_not(cas.logic_and(x, y)))
        return np.logical_xor(x, y)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX)) or isinstance(y, (cas.DM, cas.MX))):
            return cas.logic_and(cas.logic_or(x, y), cas.logic_not(cas.logic_and(x, y)))
        return np.logical_xor(x, y)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        if (isinstance(y, (tf.Tensor, tf.Variable))):
            return tf.logical_xor(x, y, name)
        return tf.logical_xor(x, tf.constant(y, x.dtype), name)
    if (isinstance(y, (tf.Tensor, tf.Variable))):
        return tf.logical_xor(tf.constant(x, y.dtype), y, name)
    return np.logical_xor(x, y)

def flatten(input):
    output = []
    for item in input:
        if (isinstance(item, (list, tuple))):
            output += flatten(item)
        else:
            output += [item]
    return output

def reduce_all(input, axis=None, keepdims=False, name=None):
    items = None
    if (isinstance(input, (list, tuple))):
        items = []
        input = flatten(input)
        for x in input:
            if (isinstance(x, bool)):
                if (not x):
                    return False
            else:
                items.append(x)
        if (len(items) == 0):
            return True
    if (symbolics.mode == symbolics.SYMPY):
        if (items is not None):
            return sym.And(*items)
        return np.all(input, axis, keepdims=keepdims)
    if (symbolics.mode == symbolics.PYOMO):
        if (items is not None):
            return items
        return np.all(input, axis, keepdims=keepdims)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (items is not None):
            result = items[0]
            for x in items[1:]:
                result = cas.logic_and(result, x)
            return result
        if (isinstance(input, (cas.DM, cas.SX))):
            return cas.logic_all(input)
        return np.all(input, axis, keepdims=keepdims)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (items is not None):
            result = items[0]
            for x in items[1:]:
                result = cas.logic_and(result, x)
            return result
        if (isinstance(input, (cas.DM, cas.MX))):
            return cas.logic_all(input)
        return np.all(input, axis, keepdims=keepdims)
    if (items is not None):
        result = items[0]
        for x in items[1:]:
            result = tf.logical_and(result, x)
        return result
    if (isinstance(input, (tf.Tensor, tf.Variable))):
        return tf.reduce_all(input, axis, keepdims, name)
    return np.all(input, axis, keepdims=keepdims)
        
def reduce_any(input, axis=None, keepdims=False, name=None):
    items = None
    if (isinstance(input, (list, tuple))):
        items = []
        for x in input:
            if (isinstance(x, bool)):
                if (x):
                    return True
            else:
                items.append(x)
        if (len(items) == 0):
            return False
    if (symbolics.mode == symbolics.SYMPY):
        if (items is not None):
            return sym.Or(*items)
        return np.any(input, axis, keepdims=keepdims)
    if (symbolics.mode == symbolics.PYOMO):
        if (items is not None):
            raise Exception("reduce_any operator not supported with Pyomo")
        return np.any(input, axis, keepdims=keepdims)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (items is not None):
            result = items[0]
            for x in items[1:]:
                result = cas.logic_or(result, x)
            return result
        if (isinstance(input, (cas.DM, cas.SX))):
            return cas.logic_any(input)
        return np.any(input, axis, keepdims=keepdims)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (items is not None):
            result = items[0]
            for x in items[1:]:
                result = cas.logic_or(result, x)
            return result
        if (isinstance(input, (cas.DM, cas.MX))):
            return cas.logic_any(input)
        return np.any(input, axis, keepdims=keepdims)
    if (items is not None):
        result = items[0]
        for x in items[1:]:
            result = tf.logical_or(result, x)
        return result
    if (isinstance(input, (tf.Tensor, tf.Variable))):
        return tf.reduce_any(input, axis, keepdims, name)
    return np.any(input, axis, keepdims=keepdims)











def where(condition, x, y, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        raise Exception("where operator not supported with Pyomo")
    if (symbolics.mode == symbolics.PYOMO):
        return pyo.Expr_if(condition, x, y)
    if (symbolics.mode == symbolics.CASADI_SX):
        return cas.if_else(condition, x, y, True)
    if (symbolics.mode == symbolics.CASADI_MX):
        return cas.if_else(condition, x, y, True)
    if (isinstance(condition, (tf.Tensor, tf.Variable))):
        tf.where(condition, x, y, name)
    return np.where(condition, x, y)

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
    print("ceil")
    return None

def floor(x, name=None):
    print("floor")
    return None

def round(x, name=None):
    print("round")
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

def reduce_sum(x, axis=None, keepdims=False, name=None):
    if (x is None):
        return 0
    if (isinstance(x, np.ndarray)):
        return np.sum(x, axis, keepdims=keepdims)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        return tf.reduce_sum(x, axis, keepdims, name)
    if (not isinstance(x, (list, tuple))):
        return x
    if (len(x) == 0):
        return 0
    result = x[0]
    for i in x[1:]:
        result = result + i
    return result

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
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic)):
            return sym.acosh(x)
        return np.acosh(x)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue)):
            return pyo.acosh(x)
        return np.acosh(x)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX))):
            return cas.acosh(x)
        return np.acosh(x)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX))):
            return cas.acosh(x)
        return np.acosh(x)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        return tf.acosh(x, name)
    return np.acosh(x)

def asin(x, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic)):
            return sym.asin(x)
        return np.asin(x)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue)):
            return pyo.asin(x)
        return np.asin(x)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX))):
            return cas.asin(x)
        return np.asin(x)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX))):
            return cas.asin(x)
        return np.asin(x)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        return tf.asin(x, name)
    return np.asin(x)

def asinh(x, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic)):
            return sym.asinh(x)
        return np.asinh(x)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue)):
            return pyo.asinh(x)
        return np.asinh(x)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX))):
            return cas.asinh(x)
        return np.asinh(x)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX))):
            return cas.asinh(x)
        return np.asinh(x)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        return tf.asinh(x, name)
    return np.asinh(x)

def atan(x, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic)):
            return sym.atan(x)
        return np.atan(x)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue)):
            return pyo.atan(x)
        return np.atan(x)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX))):
            return cas.atan(x)
        return np.atan(x)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX))):
            return cas.atan(x)
        return np.atan(x)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        return tf.atan(x, name)
    return np.atan(x)

def atan2(y, x, name=None):
    print("atan2")
    return None

def atanh(x, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic)):
            return sym.atanh(x)
        return np.atanh(x)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue)):
            return pyo.atanh(x)
        return np.atanh(x)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX))):
            return cas.atanh(x)
        return np.atanh(x)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX))):
            return cas.atanh(x)
        return np.atanh(x)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        return tf.atanh(x, name)
    return np.atanh(x)

def cos(x, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic)):
            return sym.cos(x)
        return np.cos(x)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue)):
            return pyo.cos(x)
        return np.cos(x)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX))):
            return cas.cos(x)
        return np.cos(x)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX))):
            return cas.cos(x)
        return np.cos(x)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        return tf.cos(x, name)
    return np.cos(x)

def cosh(x, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic)):
            return sym.cosh(x)
        return np.cosh(x)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue)):
            return pyo.cosh(x)
        return np.cosh(x)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX))):
            return cas.cosh(x)
        return np.cosh(x)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX))):
            return cas.cosh(x)
        return np.cosh(x)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        return tf.cosh(x, name)
    return np.cosh(x)

def exp(x, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic)):
            return sym.exp(x)
        return np.exp(x)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue)):
            return pyo.exp(x)
        return np.exp(x)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX))):
            return cas.exp(x)
        return np.exp(x)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX))):
            return cas.exp(x)
        return np.exp(x)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        return tf.exp(x, name)
    return np.exp(x)

def log(x, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic)):
            return sym.log(x)
        return np.log(x)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue)):
            return pyo.log(x)
        return np.log(x)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX))):
            return cas.log(x)
        return np.log(x)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX))):
            return cas.log(x)
        return np.log(x)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        return tf.log(x, name)
    return np.log(x)

def sin(x, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic)):
            return sym.sin(x)
        return np.sin(x)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue)):
            return pyo.sin(x)
        return np.sin(x)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX))):
            return cas.sin(x)
        return np.sin(x)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX))):
            return cas.sin(x)
        return np.sin(x)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        return tf.sin(x, name)
    return np.sin(x)

def sinh(x, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic)):
            return sym.sinh(x)
        return np.sinh(x)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue)):
            return pyo.sinh(x)
        return np.sinh(x)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX))):
            return cas.sinh(x)
        return np.sinh(x)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX))):
            return cas.sinh(x)
        return np.sinh(x)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        return tf.sinh(x, name)
    return np.sinh(x)

def sqrt(x, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic)):
            return sym.sqrt(x)
        return np.sqrt(x)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue)):
            return pyo.sqrt(x)
        return np.sqrt(x)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX))):
            return cas.sqrt(x)
        return np.sqrt(x)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX))):
            return cas.sqrt(x)
        return np.sqrt(x)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        return tf.sqrt(x, name)
    return np.sqrt(x)

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
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic)):
            return sym.tan(x)
        return np.tan(x)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue)):
            return pyo.tan(x)
        return np.tan(x)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX))):
            return cas.tan(x)
        return np.tan(x)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX))):
            return cas.tan(x)
        return np.tan(x)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        return tf.tan(x, name)
    return np.tan(x)

def tanh(x, name=None):
    if (symbolics.mode == symbolics.SYMPY):
        if (isinstance(x, sym.Basic)):
            return sym.tanh(x)
        return np.tanh(x)
    if (symbolics.mode == symbolics.PYOMO):
        if (isinstance(x, pyo.NumericValue)):
            return pyo.tanh(x)
        return np.tanh(x)
    if (symbolics.mode == symbolics.CASADI_SX):
        if (isinstance(x, (cas.DM, cas.SX))):
            return cas.tanh(x)
        return np.tanh(x)
    if (symbolics.mode == symbolics.CASADI_MX):
        if (isinstance(x, (cas.DM, cas.MX))):
            return cas.tanh(x)
        return np.tanh(x)
    if (isinstance(x, (tf.Tensor, tf.Variable))):
        return tf.tanh(x, name)
    return np.tanh(x)

def dtype(x):
    print("dtype")
    return None

def constant(x, type=None):
    print("constant")
    return None


def asarray(*args, **kwargs):
    return np.asarray(*args, **kwargs);

def linspace(*args, **kwargs):
    return np.linspace(*args, **kwargs)
