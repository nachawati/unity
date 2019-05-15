.. role:: xquery(code)
   :language: xquery

numerics Module
==========

**http://dgms.io/modules/numerics**

This module provides functions for numerical operations on tensors.


----------

Function Summary
**********

----------

.. raw:: html

  <code class="function-summary"><a href="#add-0">add</a>() external</code>

Returns $a + $b element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#subtract-0">subtract</a>() external</code>

Returns $a - $b element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#multiply-0">multiply</a>() external</code>

Returns $a * $b element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#divide-0">divide</a>() external</code>

Returns $a / $b element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#floor-divide-0">floor-divide</a>() external</code>

Returns $a // $b element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#mod-0">mod</a>() external</code>

Returns the mod of $a and $b element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#neg-0">neg</a>() external</code>

Computes the arithmetic negation of $x element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#logical-and-0">logical-and</a>() external</code>

Returns the truth value of $a and $b element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#logical-not-0">logical-not</a>() external</code>

Returns the truth value of not($x) element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#logical-or-0">logical-or</a>() external</code>

Returns the truth value of $a or $b element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#logical-xor-0">logical-xor</a>() external</code>

Returns the truth value of $a xor $b element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#all-0">all</a>() external</code>

Computes the logical and of elements across dimensions of $x.


----------

.. raw:: html

  <code class="function-summary"><a href="#any-0">any</a>() external</code>

Computes the logical or of elements across dimensions of $x.


----------

.. raw:: html

  <code class="function-summary"><a href="#equal-0">equal</a>() external</code>

Returns the truth value of $a == $b element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#greater-0">greater</a>() external</code>

Returns the truth value of $a > $b element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#greater-equal-0">greater-equal</a>() external</code>

Returns the truth value of $a >= $b element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#less-0">less</a>() external</code>

Returns the truth value of $a < $b element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#less-equal-0">less-equal</a>() external</code>

Returns the truth value of $a <= $b element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#not-equal-0">not-equal</a>() external</code>

Returns the truth value of $a != $b element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#where-0">where</a>() external</code>

Returns the elements $a or $b depending on $condition.


----------

.. raw:: html

  <code class="function-summary"><a href="#abs-0">abs</a>() external</code>

Computes the absolute value of $x element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#ceil-0">ceil</a>() external</code>

Computes the ceiling of $x element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#floor-0">floor</a>() external</code>

Computes the floor of $x element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#round-0">round</a>() external</code>

Computes the rounding of $x element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#reduce-sum-0">reduce-sum</a>() external</code>

Computes the sum of elements across dimensions of $x.


----------

.. raw:: html

  <code class="function-summary"><a href="#acos-0">acos</a>() external</code>

Computes the inverse cosine of $x element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#acosh-0">acosh</a>() external</code>

Computes the inverse hyperbolic cosine of $x element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#asin-0">asin</a>() external</code>

Computes the inverse sine of $x element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#asinh-0">asinh</a>() external</code>

Computes the inverse hyperbolic sine of $x element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#atan-0">atan</a>() external</code>

Computes the inverse tangent of $x element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#atan2-0">atan2</a>() external</code>

Computes the inverse tangent of $a / $b element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#atanh-0">atanh</a>() external</code>

Computes the inverse hyperbolic tangent of $x element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#cos-0">cos</a>() external</code>

Computes the cosine of $x element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#cosh-0">cosh</a>() external</code>

Computes the hyperbolic cosine of $x element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#exp-0">exp</a>() external</code>

Computes the exponential of $x element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#log-0">log</a>() external</code>

Computes the natural logarithm of $x element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#sin-0">sin</a>() external</code>

Computes the sine of $x element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#sinh-0">sinh</a>() external</code>

Computes the hyperbolic sine of $x element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#sqrt-0">sqrt</a>() external</code>

Computes the square root of $x element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#square-0">square</a>() external</code>

Computes the square of $x element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#tan-0">tan</a>() external</code>

Computes the tangent of $x element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#tanh-0">tanh</a>() external</code>

Computes the hyperbolic tangent of $x element-wise.


----------

.. raw:: html

  <code class="function-summary"><a href="#dtype-0">dtype</a>() external</code>



----------

.. raw:: html

  <code class="function-summary"><a href="#constant-0">constant</a>() external</code>



----------

.. raw:: html

  <code class="function-summary"><a href="#pow-0">pow</a>() external</code>



----------

.. raw:: html

  <code class="function-summary"><a href="#asarray-0">asarray</a>() external</code>



----------

.. raw:: html

  <code class="function-summary"><a href="#linspace-0">linspace</a>() external</code>

Return evenly spaced numbers over a specified interval.


----------

Functions
**********

----------

.. _add-0:

numerics:add#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:add() external</code>


Returns $a + $b element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _subtract-0:

numerics:subtract#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:subtract() external</code>


Returns $a - $b element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _multiply-0:

numerics:multiply#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:multiply() external</code>


Returns $a * $b element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _divide-0:

numerics:divide#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:divide() external</code>


Returns $a / $b element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _floor-divide-0:

numerics:floor-divide#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:floor-divide() external</code>


Returns $a // $b element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _mod-0:

numerics:mod#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:mod() external</code>


Returns the mod of $a and $b element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _neg-0:

numerics:neg#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:neg() external</code>


Computes the arithmetic negation of $x element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _logical-and-0:

numerics:logical-and#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:logical-and() external</code>


Returns the truth value of $a and $b element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _logical-not-0:

numerics:logical-not#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:logical-not() external</code>


Returns the truth value of not($x) element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _logical-or-0:

numerics:logical-or#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:logical-or() external</code>


Returns the truth value of $a or $b element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _logical-xor-0:

numerics:logical-xor#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:logical-xor() external</code>


Returns the truth value of $a xor $b element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _all-0:

numerics:all#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:all() external</code>


Computes the logical and of elements across dimensions of $x.


Returns
^^^^^^^^^^

tensor



----------

.. _any-0:

numerics:any#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:any() external</code>


Computes the logical or of elements across dimensions of $x.


Returns
^^^^^^^^^^

tensor



----------

.. _equal-0:

numerics:equal#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:equal() external</code>


Returns the truth value of $a == $b element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _greater-0:

numerics:greater#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:greater() external</code>


Returns the truth value of $a > $b element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _greater-equal-0:

numerics:greater-equal#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:greater-equal() external</code>


Returns the truth value of $a >= $b element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _less-0:

numerics:less#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:less() external</code>


Returns the truth value of $a < $b element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _less-equal-0:

numerics:less-equal#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:less-equal() external</code>


Returns the truth value of $a <= $b element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _not-equal-0:

numerics:not-equal#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:not-equal() external</code>


Returns the truth value of $a != $b element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _where-0:

numerics:where#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:where() external</code>


Returns the elements $a or $b depending on $condition.


Returns
^^^^^^^^^^

tensor



----------

.. _abs-0:

numerics:abs#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:abs() external</code>


Computes the absolute value of $x element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _ceil-0:

numerics:ceil#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:ceil() external</code>


Computes the ceiling of $x element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _floor-0:

numerics:floor#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:floor() external</code>


Computes the floor of $x element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _round-0:

numerics:round#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:round() external</code>


Computes the rounding of $x element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _reduce-sum-0:

numerics:reduce-sum#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:reduce-sum() external</code>


Computes the sum of elements across dimensions of $x.


Returns
^^^^^^^^^^

tensor



----------

.. _acos-0:

numerics:acos#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:acos() external</code>


Computes the inverse cosine of $x element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _acosh-0:

numerics:acosh#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:acosh() external</code>


Computes the inverse hyperbolic cosine of $x element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _asin-0:

numerics:asin#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:asin() external</code>


Computes the inverse sine of $x element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _asinh-0:

numerics:asinh#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:asinh() external</code>


Computes the inverse hyperbolic sine of $x element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _atan-0:

numerics:atan#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:atan() external</code>


Computes the inverse tangent of $x element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _atan2-0:

numerics:atan2#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:atan2() external</code>


Computes the inverse tangent of $a / $b element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _atanh-0:

numerics:atanh#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:atanh() external</code>


Computes the inverse hyperbolic tangent of $x element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _cos-0:

numerics:cos#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:cos() external</code>


Computes the cosine of $x element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _cosh-0:

numerics:cosh#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:cosh() external</code>


Computes the hyperbolic cosine of $x element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _exp-0:

numerics:exp#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:exp() external</code>


Computes the exponential of $x element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _log-0:

numerics:log#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:log() external</code>


Computes the natural logarithm of $x element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _sin-0:

numerics:sin#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:sin() external</code>


Computes the sine of $x element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _sinh-0:

numerics:sinh#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:sinh() external</code>


Computes the hyperbolic sine of $x element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _sqrt-0:

numerics:sqrt#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:sqrt() external</code>


Computes the square root of $x element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _square-0:

numerics:square#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:square() external</code>


Computes the square of $x element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _tan-0:

numerics:tan#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:tan() external</code>


Computes the tangent of $x element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _tanh-0:

numerics:tanh#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:tanh() external</code>


Computes the hyperbolic tangent of $x element-wise.


Returns
^^^^^^^^^^

tensor



----------

.. _dtype-0:

numerics:dtype#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:dtype() external</code>





----------

.. _constant-0:

numerics:constant#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:constant() external</code>





----------

.. _pow-0:

numerics:pow#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:pow() external</code>





----------

.. _asarray-0:

numerics:asarray#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:asarray() external</code>





----------

.. _linspace-0:

numerics:linspace#0
##########
.. raw:: html

  <code class="function-summary">declare %public %an:deterministic %an:variadic function numerics:linspace() external</code>


Return evenly spaced numbers over a specified interval.


