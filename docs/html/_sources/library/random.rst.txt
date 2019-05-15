.. role:: xquery(code)
   :language: xquery

random Module
==========

**http://dgms.io/modules/random**



----------

Function Summary
**********

----------

.. raw:: html

  <code class="function-summary"><a href="#rand-0">rand</a>() external</code>

Random values in a given shape.


----------

.. raw:: html

  <code class="function-summary"><a href="#randn-0">randn</a>() external</code>

Return a sample (or samples) from the “standard normal” distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#randint-0">randint</a>() external</code>

Return random integers from low (inclusive) to high (exclusive).


----------

.. raw:: html

  <code class="function-summary"><a href="#random-integers-0">random-integers</a>() external</code>

Random integers of type np.int between low and high, inclusive.


----------

.. raw:: html

  <code class="function-summary"><a href="#random-sample-0">random-sample</a>() external</code>

Return random floats in the half-open interval [0.0, 1.0).


----------

.. raw:: html

  <code class="function-summary"><a href="#random-0">random</a>() external</code>

Return random floats in the half-open interval [0.0, 1.0).


----------

.. raw:: html

  <code class="function-summary"><a href="#ranf-0">ranf</a>() external</code>

Return random floats in the half-open interval [0.0, 1.0).


----------

.. raw:: html

  <code class="function-summary"><a href="#sample-0">sample</a>() external</code>

Return random floats in the half-open interval [0.0, 1.0).


----------

.. raw:: html

  <code class="function-summary"><a href="#choice-0">choice</a>() external</code>

Generates a random sample from a given 1-D array


----------

.. raw:: html

  <code class="function-summary"><a href="#bytes-0">bytes</a>() external</code>

Return random bytes.


----------

.. raw:: html

  <code class="function-summary"><a href="#shuffle-0">shuffle</a>() external</code>

Modify a sequence in-place by shuffling its contents.


----------

.. raw:: html

  <code class="function-summary"><a href="#permutation-0">permutation</a>() external</code>

Randomly permute a sequence, or return a permuted range.


----------

.. raw:: html

  <code class="function-summary"><a href="#beta-0">beta</a>() external</code>

Draw samples from a Beta distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#binomial-0">binomial</a>() external</code>

Draw samples from a binomial distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#chisquare-0">chisquare</a>() external</code>

Draw samples from a chi-square distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#dirichlet-0">dirichlet</a>() external</code>

Draw samples from the Dirichlet distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#exponential-0">exponential</a>() external</code>

Draw samples from an exponential distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#f-0">f</a>() external</code>

Draw samples from an F distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#gamma-0">gamma</a>() external</code>

Draw samples from a Gamma distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#geometric-0">geometric</a>() external</code>

Draw samples from the geometric distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#gumbel-0">gumbel</a>() external</code>

Draw samples from a Gumbel distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#hypergeometric-0">hypergeometric</a>() external</code>

Draw samples from a Hypergeometric distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#laplace-0">laplace</a>() external</code>

Draw samples from the Laplace or double exponential distribution with specified location (or mean) and scale (decay).


----------

.. raw:: html

  <code class="function-summary"><a href="#logistic-0">logistic</a>() external</code>

Draw samples from a logistic distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#lognormal-0">lognormal</a>() external</code>

Draw samples from a log-normal distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#logseries-0">logseries</a>() external</code>

Draw samples from a logarithmic series distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#multinomial-0">multinomial</a>() external</code>

Draw samples from a multinomial distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#multivariate-normal-0">multivariate-normal</a>() external</code>

Draw random samples from a multivariate normal distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#negative-binomial-0">negative-binomial</a>() external</code>

Draw samples from a negative binomial distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#noncentral-chisquare-0">noncentral-chisquare</a>() external</code>

Draw samples from a noncentral chi-square distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#noncentral-f-0">noncentral-f</a>() external</code>

Draw samples from the noncentral F distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#normal-0">normal</a>() external</code>

Draw random samples from a normal (Gaussian) distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#pareto-0">pareto</a>() external</code>

Draw samples from a Pareto II or Lomax distribution with specified shape.


----------

.. raw:: html

  <code class="function-summary"><a href="#poisson-0">poisson</a>() external</code>

Draw samples from a Poisson distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#power-0">power</a>() external</code>

Draws samples in [0, 1] from a power distribution with positive exponent a - 1.


----------

.. raw:: html

  <code class="function-summary"><a href="#rayleigh-0">rayleigh</a>() external</code>

Draw samples from a Rayleigh distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#standard-cauchy-0">standard-cauchy</a>() external</code>

Draw samples from a standard Cauchy distribution with mode = 0.


----------

.. raw:: html

  <code class="function-summary"><a href="#standard-exponential-0">standard-exponential</a>() external</code>

Draw samples from the standard exponential distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#standard-gamma-0">standard-gamma</a>() external</code>

Draw samples from a standard Gamma distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#standard-normal-0">standard-normal</a>() external</code>

Draw samples from a standard Normal distribution (mean=0, stdev=1).


----------

.. raw:: html

  <code class="function-summary"><a href="#standard-t-0">standard-t</a>() external</code>

Draw samples from a standard Student’s t distribution with df degrees of freedom.


----------

.. raw:: html

  <code class="function-summary"><a href="#triangular-0">triangular</a>() external</code>

Draw samples from the triangular distribution over the interval [left, right].


----------

.. raw:: html

  <code class="function-summary"><a href="#uniform-0">uniform</a>() external</code>

Draw samples from a uniform distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#vonmises-0">vonmises</a>() external</code>

Draw samples from a von Mises distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#wald-0">wald</a>() external</code>

Draw samples from a Wald, or inverse Gaussian, distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#weibull-0">weibull</a>() external</code>

Draw samples from a Weibull distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#zipf-0">zipf</a>() external</code>

Draw samples from a Zipf distribution.


----------

.. raw:: html

  <code class="function-summary"><a href="#RandomState-0">RandomState</a>() external</code>

Container for the Mersenne Twister pseudo-random number generator.


----------

.. raw:: html

  <code class="function-summary"><a href="#seed-0">seed</a>() external</code>

Seed the generator.


----------

.. raw:: html

  <code class="function-summary"><a href="#get-state-0">get-state</a>() external</code>

Return a tuple representing the internal state of the generator.


----------

.. raw:: html

  <code class="function-summary"><a href="#set-state-0">set-state</a>() external</code>

Set the internal state of the generator from a tuple.


----------

Functions
**********

----------

.. _rand-0:

random:rand#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:rand() external</code>


Random values in a given shape.



----------

.. _randn-0:

random:randn#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:randn() external</code>


Return a sample (or samples) from the “standard normal” distribution.



----------

.. _randint-0:

random:randint#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:randint() external</code>


Return random integers from low (inclusive) to high (exclusive).



----------

.. _random-integers-0:

random:random-integers#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:random-integers() external</code>


Random integers of type np.int between low and high, inclusive.



----------

.. _random-sample-0:

random:random-sample#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:random-sample() external</code>


Return random floats in the half-open interval [0.0, 1.0).



----------

.. _random-0:

random:random#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:random() external</code>


Return random floats in the half-open interval [0.0, 1.0).



----------

.. _ranf-0:

random:ranf#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:ranf() external</code>


Return random floats in the half-open interval [0.0, 1.0).



----------

.. _sample-0:

random:sample#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:sample() external</code>


Return random floats in the half-open interval [0.0, 1.0).



----------

.. _choice-0:

random:choice#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:choice() external</code>


Generates a random sample from a given 1-D array



----------

.. _bytes-0:

random:bytes#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:bytes() external</code>


Return random bytes.



----------

.. _shuffle-0:

random:shuffle#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:shuffle() external</code>


Modify a sequence in-place by shuffling its contents.



----------

.. _permutation-0:

random:permutation#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:permutation() external</code>


Randomly permute a sequence, or return a permuted range.



----------

.. _beta-0:

random:beta#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:beta() external</code>


Draw samples from a Beta distribution.



----------

.. _binomial-0:

random:binomial#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:binomial() external</code>


Draw samples from a binomial distribution.



----------

.. _chisquare-0:

random:chisquare#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:chisquare() external</code>


Draw samples from a chi-square distribution.



----------

.. _dirichlet-0:

random:dirichlet#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:dirichlet() external</code>


Draw samples from the Dirichlet distribution.



----------

.. _exponential-0:

random:exponential#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:exponential() external</code>


Draw samples from an exponential distribution.



----------

.. _f-0:

random:f#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:f() external</code>


Draw samples from an F distribution.



----------

.. _gamma-0:

random:gamma#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:gamma() external</code>


Draw samples from a Gamma distribution.



----------

.. _geometric-0:

random:geometric#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:geometric() external</code>


Draw samples from the geometric distribution.



----------

.. _gumbel-0:

random:gumbel#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:gumbel() external</code>


Draw samples from a Gumbel distribution.



----------

.. _hypergeometric-0:

random:hypergeometric#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:hypergeometric() external</code>


Draw samples from a Hypergeometric distribution.



----------

.. _laplace-0:

random:laplace#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:laplace() external</code>


Draw samples from the Laplace or double exponential distribution with specified location (or mean) and scale (decay).



----------

.. _logistic-0:

random:logistic#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:logistic() external</code>


Draw samples from a logistic distribution.



----------

.. _lognormal-0:

random:lognormal#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:lognormal() external</code>


Draw samples from a log-normal distribution.



----------

.. _logseries-0:

random:logseries#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:logseries() external</code>


Draw samples from a logarithmic series distribution.



----------

.. _multinomial-0:

random:multinomial#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:multinomial() external</code>


Draw samples from a multinomial distribution.



----------

.. _multivariate-normal-0:

random:multivariate-normal#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:multivariate-normal() external</code>


Draw random samples from a multivariate normal distribution.



----------

.. _negative-binomial-0:

random:negative-binomial#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:negative-binomial() external</code>


Draw samples from a negative binomial distribution.



----------

.. _noncentral-chisquare-0:

random:noncentral-chisquare#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:noncentral-chisquare() external</code>


Draw samples from a noncentral chi-square distribution.



----------

.. _noncentral-f-0:

random:noncentral-f#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:noncentral-f() external</code>


Draw samples from the noncentral F distribution.



----------

.. _normal-0:

random:normal#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:normal() external</code>


Draw random samples from a normal (Gaussian) distribution.



----------

.. _pareto-0:

random:pareto#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:pareto() external</code>


Draw samples from a Pareto II or Lomax distribution with specified shape.



----------

.. _poisson-0:

random:poisson#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:poisson() external</code>


Draw samples from a Poisson distribution.



----------

.. _power-0:

random:power#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:power() external</code>


Draws samples in [0, 1] from a power distribution with positive exponent a - 1.



----------

.. _rayleigh-0:

random:rayleigh#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:rayleigh() external</code>


Draw samples from a Rayleigh distribution.



----------

.. _standard-cauchy-0:

random:standard-cauchy#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:standard-cauchy() external</code>


Draw samples from a standard Cauchy distribution with mode = 0.



----------

.. _standard-exponential-0:

random:standard-exponential#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:standard-exponential() external</code>


Draw samples from the standard exponential distribution.



----------

.. _standard-gamma-0:

random:standard-gamma#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:standard-gamma() external</code>


Draw samples from a standard Gamma distribution.



----------

.. _standard-normal-0:

random:standard-normal#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:standard-normal() external</code>


Draw samples from a standard Normal distribution (mean=0, stdev=1).



----------

.. _standard-t-0:

random:standard-t#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:standard-t() external</code>


Draw samples from a standard Student’s t distribution with df degrees of freedom.



----------

.. _triangular-0:

random:triangular#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:triangular() external</code>


Draw samples from the triangular distribution over the interval [left, right].



----------

.. _uniform-0:

random:uniform#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:uniform() external</code>


Draw samples from a uniform distribution.



----------

.. _vonmises-0:

random:vonmises#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:vonmises() external</code>


Draw samples from a von Mises distribution.



----------

.. _wald-0:

random:wald#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:wald() external</code>


Draw samples from a Wald, or inverse Gaussian, distribution.



----------

.. _weibull-0:

random:weibull#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:weibull() external</code>


Draw samples from a Weibull distribution.



----------

.. _zipf-0:

random:zipf#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:zipf() external</code>


Draw samples from a Zipf distribution.



----------

.. _RandomState-0:

random:RandomState#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:RandomState() external</code>


Container for the Mersenne Twister pseudo-random number generator.



----------

.. _seed-0:

random:seed#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:seed() external</code>


Seed the generator.



----------

.. _get-state-0:

random:get-state#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:get-state() external</code>


Return a tuple representing the internal state of the generator.



----------

.. _set-state-0:

random:set-state#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:nondeterministic %an:variadic function random:set-state() external</code>


Set the internal state of the generator from a tuple.


