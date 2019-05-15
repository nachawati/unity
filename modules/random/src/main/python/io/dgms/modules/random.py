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

import numpy.random;

#===============================================================================
# Random sampling
#=============================================================================:)

#-------------------------------------------------------------------------------
# Simple random data
#-----------------------------------------------------------------------------:)

def rand(*args, **kwargs):
    return numpy.random.rand(*args, **kwargs);

def randn(*args, **kwargs):
    return numpy.random.randn(*args, **kwargs);

def randint(*args, **kwargs):
    return numpy.random.randint(*args, **kwargs);

def random_integers(*args, **kwargs):
    return numpy.random.random_integers(*args, **kwargs);

def random_sample(*args, **kwargs):
    return numpy.random.random_sample(*args, **kwargs);

def random(*args, **kwargs):
    return numpy.random.random(*args, **kwargs);

def ranf(*args, **kwargs):
    return numpy.random.ranf(*args, **kwargs);

def sample(*args, **kwargs):
    return numpy.random.sample(*args, **kwargs);

def choice(*args, **kwargs):
    return numpy.random.choice(*args, **kwargs);

def bytes(*args, **kwargs):
    return numpy.random.bytes(*args, **kwargs);

#-------------------------------------------------------------------------------
# Permutations
#-----------------------------------------------------------------------------:)

def shuffle(*args, **kwargs):
    return numpy.random.shuffle(*args, **kwargs);

def permutation(*args, **kwargs):
    return numpy.random.permutation(*args, **kwargs);

#-------------------------------------------------------------------------------
# Distributions
#-----------------------------------------------------------------------------:)

def beta(*args, **kwargs):
    return numpy.random.beta(*args, **kwargs);

def binomial(*args, **kwargs):
    return numpy.random.binomial(*args, **kwargs);

def chisquare(*args, **kwargs):
    return numpy.random.chisquare(*args, **kwargs);

def dirichlet(*args, **kwargs):
    return numpy.random.dirichlet(*args, **kwargs);

def exponential(*args, **kwargs):
    return numpy.random.exponential(*args, **kwargs);

def f(*args, **kwargs):
    return numpy.random.f(*args, **kwargs);

def gamma(*args, **kwargs):
    return numpy.random.gamma(*args, **kwargs);

def geometric(*args, **kwargs):
    return numpy.random.geometric(*args, **kwargs);

def gumbel(*args, **kwargs):
    return numpy.random.gumbel(*args, **kwargs);

def hypergeometric(*args, **kwargs):
    return numpy.random.hypergeometric(*args, **kwargs);

def laplace(*args, **kwargs):
    return numpy.random.laplace(*args, **kwargs);

def logistic(*args, **kwargs):
    return numpy.random.logistic(*args, **kwargs);

def lognormal(*args, **kwargs):
    return numpy.random.lognormal(*args, **kwargs);

def logseries(*args, **kwargs):
    return numpy.random.logseries(*args, **kwargs);

def multinomial(*args, **kwargs):
    return numpy.random.multinomial(*args, **kwargs);

def multivariate_normal(*args, **kwargs):
    return numpy.random.multivariate_normal(*args, **kwargs);

def negative_binomial(*args, **kwargs):
    return numpy.random.negative_binomial(*args, **kwargs);

def noncentral_chisquare(*args, **kwargs):
    return numpy.random.noncentral_chisquare(*args, **kwargs);

def noncentral_f(*args, **kwargs):
    return numpy.random.noncentral_f(*args, **kwargs);

def normal(*args, **kwargs):
    return numpy.random.normal(*args, **kwargs);

def pareto(*args, **kwargs):
    return numpy.random.pareto(*args, **kwargs);

def poisson(*args, **kwargs):
    return numpy.random.poisson(*args, **kwargs);

def power(*args, **kwargs):
    return numpy.random.power(*args, **kwargs);

def rayleigh(*args, **kwargs):
    return numpy.random.rayleigh(*args, **kwargs);

def standard_cauchy(*args, **kwargs):
    return numpy.random.standard_cauchy(*args, **kwargs);

def standard_exponential(*args, **kwargs):
    return numpy.random.standard_exponential(*args, **kwargs);

def standard_gamma(*args, **kwargs):
    return numpy.random.standard_gamma(*args, **kwargs);

def standard_normal(*args, **kwargs):
    return numpy.random.standard_normal(*args, **kwargs);

def standard_t(*args, **kwargs):
    return numpy.random.standard_t(*args, **kwargs);

def triangular(*args, **kwargs):
    return numpy.random.triangular(*args, **kwargs);

def uniform(*args, **kwargs):
    return numpy.random.uniform(*args, **kwargs);

def vonmises(*args, **kwargs):
    return numpy.random.vonmises(*args, **kwargs);

def wald(*args, **kwargs):
    return numpy.random.wald(*args, **kwargs);

def weibull(*args, **kwargs):
    return numpy.random.weibull(*args, **kwargs);

def zipf(*args, **kwargs):
    return numpy.random.zipf(*args, **kwargs);

#-------------------------------------------------------------------------------
# Random generator
#-----------------------------------------------------------------------------:)

def RandomState(*args, **kwargs):
    return numpy.random.RandomState(*args, **kwargs);

def seed(*args, **kwargs):
    return numpy.random.seed(*args, **kwargs);

def get_state(*args, **kwargs):
    return numpy.random.get-state(*args, **kwargs);

def set_state(*args, **kwargs):
    return numpy.random.set_state(*args, **kwargs);
