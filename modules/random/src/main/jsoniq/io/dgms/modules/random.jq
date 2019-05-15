jsoniq version "1.0";

(:
 : بسم الله الرحمن الرحيم
 :
 : In the name of Allah, the Most Compassionate, the Most Merciful
 :
 : This file is part of Unity DGMS <https://www.dgms.io/>
 :
 : Unity DGMS is free software; redistribution and use in source and binary
 : forms, with or without modification, are permitted provided that the
 : following conditions are met:
 :
 : 1. Redistributions of source code must retain the above notice, this list of
 :    conditions and the following disclaimer.
 :
 : 2. Redistributions in binary form must reproduce the above notice, this list
 :    of conditions and the following disclaimer in the documentation and/or
 :    other materials provided with the distribution.
 :
 : THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHORS DISCLAIM ALL WARRANTIES
 : WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 : MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY
 : SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 : WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION
 : OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN
 : CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 :)

module namespace random = "http://dgms.io/modules/random";

declare namespace an = "http://zorba.io/annotations";
declare namespace err = "http://dgms.io/errors";
declare namespace options = "http://dgms.io/options";

declare option options:source-transformation "disabled";

(:==============================================================================
 : Random sampling
 :============================================================================:)

(:------------------------------------------------------------------------------
 : Simple random data
 :----------------------------------------------------------------------------:)

(:~
 : Random values in a given shape.
 :)
declare %public %an:nondeterministic %an:variadic function random:rand() external;

(:~
 : Return a sample (or samples) from the “standard normal” distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:randn() external;

(:~
 : Return random integers from low (inclusive) to high (exclusive).
 :)
declare %public %an:nondeterministic %an:variadic function random:randint() external;

(:~
 : Random integers of type np.int between low and high, inclusive.
 :)
declare %public %an:nondeterministic %an:variadic function random:random-integers() external;

(:~
 : Return random floats in the half-open interval [0.0, 1.0).
 :)
declare %public %an:nondeterministic %an:variadic function random:random-sample() external;

(:~
 : Return random floats in the half-open interval [0.0, 1.0).
 :)
declare %public %an:nondeterministic %an:variadic function random:random() external;

(:~
 : Return random floats in the half-open interval [0.0, 1.0).
 :)
declare %public %an:nondeterministic %an:variadic function random:ranf() external;

(:~
 : Return random floats in the half-open interval [0.0, 1.0).
 :)
declare %public %an:nondeterministic %an:variadic function random:sample() external;

(:~
 : Generates a random sample from a given 1-D array
 :)
declare %public %an:nondeterministic %an:variadic function random:choice() external;

(:~
 : Return random bytes.
 :)
declare %public %an:nondeterministic %an:variadic function random:bytes() external;

(:------------------------------------------------------------------------------
 : Permutations
 :----------------------------------------------------------------------------:)

(:~
 : Modify a sequence in-place by shuffling its contents.
 :)
declare %public %an:nondeterministic %an:variadic function random:shuffle() external;

(:~
 : Randomly permute a sequence, or return a permuted range.
 :)
declare %public %an:nondeterministic %an:variadic function random:permutation() external;

(:------------------------------------------------------------------------------
 : Distributions
 :----------------------------------------------------------------------------:)

(:~
 : Draw samples from a Beta distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:beta() external;

(:~
 : Draw samples from a binomial distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:binomial() external;

(:~
 : Draw samples from a chi-square distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:chisquare() external;

(:~
 : Draw samples from the Dirichlet distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:dirichlet() external;

(:~
 : Draw samples from an exponential distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:exponential() external;

(:~
 : Draw samples from an F distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:f() external;

(:~
 : Draw samples from a Gamma distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:gamma() external;

(:~
 : Draw samples from the geometric distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:geometric() external;

(:~
 : Draw samples from a Gumbel distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:gumbel() external;

(:~
 : Draw samples from a Hypergeometric distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:hypergeometric() external;

(:~
 : Draw samples from the Laplace or double exponential distribution with specified location (or mean) and scale (decay).
 :)
declare %public %an:nondeterministic %an:variadic function random:laplace() external;

(:~
 : Draw samples from a logistic distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:logistic() external;

(:~
 : Draw samples from a log-normal distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:lognormal() external;

(:~
 : Draw samples from a logarithmic series distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:logseries() external;

(:~
 : Draw samples from a multinomial distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:multinomial() external;

(:~
 : Draw random samples from a multivariate normal distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:multivariate-normal() external;

(:~
 : Draw samples from a negative binomial distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:negative-binomial() external;

(:~
 : Draw samples from a noncentral chi-square distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:noncentral-chisquare() external;

(:~
 : Draw samples from the noncentral F distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:noncentral-f() external;

(:~
 : Draw random samples from a normal (Gaussian) distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:normal() external;

(:~
 : Draw samples from a Pareto II or Lomax distribution with specified shape.
 :)
declare %public %an:nondeterministic %an:variadic function random:pareto() external;

(:~
 : Draw samples from a Poisson distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:poisson() external;

(:~
 : Draws samples in [0, 1] from a power distribution with positive exponent a - 1.
 :)
declare %public %an:nondeterministic %an:variadic function random:power() external;

(:~
 : Draw samples from a Rayleigh distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:rayleigh() external;

(:~
 : Draw samples from a standard Cauchy distribution with mode = 0.
 :)
declare %public %an:nondeterministic %an:variadic function random:standard-cauchy() external;

(:~
 : Draw samples from the standard exponential distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:standard-exponential() external;

(:~
 : Draw samples from a standard Gamma distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:standard-gamma() external;

(:~
 : Draw samples from a standard Normal distribution (mean=0, stdev=1).
 :)
declare %public %an:nondeterministic %an:variadic function random:standard-normal() external;

(:~
 : Draw samples from a standard Student’s t distribution with df degrees of freedom.
 :)
declare %public %an:nondeterministic %an:variadic function random:standard-t() external;

(:~
 : Draw samples from the triangular distribution over the interval [left, right].
 :)
declare %public %an:nondeterministic %an:variadic function random:triangular() external;

(:~
 : Draw samples from a uniform distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:uniform() external;

(:~
 : Draw samples from a von Mises distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:vonmises() external;

(:~
 : Draw samples from a Wald, or inverse Gaussian, distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:wald() external;

(:~
 : Draw samples from a Weibull distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:weibull() external;

(:~
 : Draw samples from a Zipf distribution.
 :)
declare %public %an:nondeterministic %an:variadic function random:zipf() external;

(:------------------------------------------------------------------------------
 : Random generator
 :----------------------------------------------------------------------------:)

(:~
 : Container for the Mersenne Twister pseudo-random number generator.
 :)
declare %public %an:nondeterministic %an:variadic function random:RandomState() external;

(:~
 : Seed the generator.
 :)
declare %public %an:nondeterministic %an:variadic function random:seed() external;

(:~
 : Return a tuple representing the internal state of the generator.
 :)
declare %public %an:nondeterministic %an:variadic function random:get-state() external;

(:~
 : Set the internal state of the generator from a tuple.
 :)
declare %public %an:nondeterministic %an:variadic function random:set-state() external;
