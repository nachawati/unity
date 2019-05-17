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

import numpy as np 
import tensorflow as tf

def absolute_difference(*args, **kwargs):
    return tf.losses.absolute_difference(*args, **kwargs)

def cosine_distance(*args, **kwargs):
    return tf.losses.cosine_distance(*args, **kwargs)

def hinge_loss(*args, **kwargs):
    return tf.losses.hinge_loss(*args, **kwargs)

def huber_loss(*args, **kwargs):
    return tf.losses.huber_loss(*args, **kwargs)

def log_loss(*args, **kwargs):
    return tf.losses.log_loss(*args, **kwargs)

def mean_pairwise_squared_error(*args, **kwargs):
    return tf.losses.mean_pairwise_squared_error(*args, **kwargs)

def mean_squared_error(*args, **kwargs):
    return tf.losses.mean_squared_error(*args, **kwargs)

def sigmoid_cross_entropy(*args, **kwargs):
    return tf.losses.sigmoid_cross_entropy(*args, **kwargs)

def softmax_cross_entropy(*args, **kwargs):
    return tf.losses.softmax_cross_entropy(*args, **kwargs)

def sparse_softmax_cross_entropy(*args, **kwargs):
    return tf.losses.sparse_softmax_cross_entropy(*args, **kwargs)

def mean_squared_error(*args, **kwargs):
    return tf.losses.mean_squared_error(*args, **kwargs)

def train(*args, **kwargs):
    return 0
