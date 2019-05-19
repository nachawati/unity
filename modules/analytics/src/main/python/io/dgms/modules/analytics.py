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

import tensorflow as tf

def parameter(value=None, name=None, shape=[], dtype="float64"):
    return { "@type": "http://dgms.io/schema/Parameter", "value": value, "name": name, "shape": shape, "dtype": tf.as_dtype(dtype) }

def placeholder(name=None, shape=[], dtype="float64"):
    return { "@type": "http://dgms.io/schema/Placeholder", "name": name, "shape": shape, "dtype": tf.as_dtype(dtype) }

def variable(initialize=None, name=None, shape=[], dtype="float64", bounds=None, domain=None):
    return { "@type": "http://dgms.io/schema/Variable", "initialize": initialize, "name": name, "shape": shape, "dtype": tf.as_dtype(dtype), "bounds": bounds, "domain": domain }
