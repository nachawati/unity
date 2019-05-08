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

import pyomo.environ as pyo
import numbers
from pyomo.core.expr import current as EXPR
import python.io.dgms.modules.numerics as numerics

serialization_mode = 0

def min(*args, **kwargs):
    min_s = None
    min_n = None
    for x in args[0]:
        if (isinstance(x, numbers.Number)):
            if (min_n is None or min_n > x):
                min_n = x
        else:
            if (min_s is None):
                min_s = x
            else:
                min_s = numerics.minimum(min_s, x)
    if (min_s is None):
        return min_n
    if (min_n is None):
        return min_s
    return numerics.minimum(min_s, min_n)

def max(*args, **kwargs):
    max_s = None
    max_n = None
    for x in args[0]:
        if (isinstance(x, numbers.Number)):
            if (max_n is None or max_n < x):
                max_n = x
        else:
            if (max_s is None):
                max_s = x
            else:
                max_s = numerics.maximum(max_s, x)
    if (max_s is None):
        return max_n
    if (max_n is None):
        return max_s
    return numerics.maximum(max_s, max_n)

def lookup(object, member):
    return object.__getattribute__(member);

def serialize_item(arg, mode):
    if (isinstance(arg, pyo.NumericValue)):
        return EXPR.expression_to_string(arg)
    return arg.__repr__();

def serialization_mode():
    global serialization_mode
    return serialization_mode

def set_serialization_mode(mode):
    global serialization_mode
    serialization_mode = mode
