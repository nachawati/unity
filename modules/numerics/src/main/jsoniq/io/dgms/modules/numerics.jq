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

(:~
 : This module provides functions for numerical operations on tensors.
 :)
module namespace numerics = "http://dgms.io/modules/numerics";

declare namespace an = "http://zorba.io/annotations";
declare namespace err = "http://dgms.io/errors";
declare namespace options = "http://dgms.io/options";

declare option options:source-transformation "disabled";

(:~
 : Returns $a + $b element-wise.
 : @param $a The first tensor to add.
 : @param $b The second tensor to add. Must have the same dtype as $a.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:add() external;

(:~
 : Returns $a - $b element-wise.
 : @param $a The first tensor to subtract from.
 : @param $b The second tensor to be subtracted. Must have the same dtype as $a.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:subtract() external;

(:~
 : Returns $a * $b element-wise.
 : @param $a The first tensor to multiply.
 : @param $b The second tensor to multiply. Must have the same dtype as $a.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:multiply() external;

(:~
 : Returns $a / $b element-wise.
 : @param $a The first tensor as the numerator.
 : @param $b The second tensor as the denominator. Must have the same dtype as $a.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:divide() external;

(:~
 : Returns $a // $b element-wise.
 : @param $a The first tensor as the numerator.
 : @param $b The second tensor as the denominator. Must have the same dtype as $a.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:floor-divide() external;

(:~
 : Returns the mod of $a and $b element-wise.
 : @param $a The first tensor.
 : @param $b The second tensor. Must have the same dtype as $a.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:mod() external;

(:~
 : Computes the arithmetic negation of $x element-wise.
 : @param $x The input tensor.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:negative() external;

(:~
 : Returns the truth value of $a and $b element-wise.
 : @param $a The first tensor.
 : @param $b The second tensor. Must have the same dtype as $a.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:logical-and() external;

(:~
 : Returns the truth value of not($x) element-wise.
 : @param $x The input tensor.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:logical-not() external;

(:~
 : Returns the truth value of $a or $b element-wise.
 : @param $a The first tensor.
 : @param $b The second tensor. Must have the same dtype as $a.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:logical-or() external;

(:~
 : Returns the truth value of $a xor $b element-wise.
 : @param $a The first tensor.
 : @param $b The second tensor. Must have the same dtype as $a.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:logical-xor() external;

(:~
 : Computes the logical and of elements across dimensions of $x.
 : @param $x The input tensor. Must be of dtype bool.
 : @param $axis The dimension(s) to reduce. By default it reduces all dimensions. Optional
 : @param $keep-dims If true, retains reduced dimensions with size 1. Optional
 : @param $name A name for the operation. Optional
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:reduce-all() external;

(:~
 : Computes the logical or of elements across dimensions of $x.
 : @param $x The input tensor. Must be of dtype bool.
 : @param $axis The dimension(s) to reduce. By default it reduces all dimensions. Optional
 : @param $keep-dims If true, retains reduced dimensions with size 1. Optional
 : @param $name A name for the operation. Optional
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:reduce-any() external;

(:~
 : Returns the truth value of $a == $b element-wise.
 : @param $a The first tensor.
 : @param $b The second tensor. Must have the same dtype as $a.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:equal() external;

(:~
 : Returns the truth value of $a &gt; $b element-wise.
 : @param $a The first tensor.
 : @param $b The second tensor. Must have the same dtype as $a.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:greater() external;

(:~
 : Returns the truth value of $a &gt;= $b element-wise.
 : @param $a The first tensor.
 : @param $b The second tensor. Must have the same dtype as $a.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:greater-equal() external;

(:~
 : Returns the truth value of $a &lt; $b element-wise.
 : @param $a The first tensor.
 : @param $b The second tensor. Must have the same dtype as $a.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:less() external;

(:~
 : Returns the truth value of $a &lt;= $b element-wise.
 : @param $a The first tensor.
 : @param $b The second tensor. Must have the same dtype as $a.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:less-equal() external;

(:~
 : Returns the truth value of $a != $b element-wise.
 : @param $a The first tensor.
 : @param $b The second tensor. Must have the same dtype as $a.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:not-equal() external;

(:~
 : Returns the elements $a or $b depending on $condition.
 : @param $condition The input condition. Must be of dtype bool.
 : @param $a The first tensor.
 : @param $b The second tensor. Must have the same dtype as $a.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:where() external;

(:~
 : Computes the absolute value of $x element-wise.
 : @param $x The input tensor.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:abs() external;

(:~
 : Computes the ceiling of $x element-wise.
 : @param $x The input tensor.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:ceil() external;

(:~
 : Computes the floor of $x element-wise.
 : @param $x The input tensor.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:floor() external;

(:~
 : Computes the rounding of $x element-wise.
 : @param $x The input tensor.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:round() external;

(:~
 : Computes the sum of elements across dimensions of $x.
 : @param $x The input tensor.
 : @param $axis The dimension(s) to reduce. By default it reduces all dimensions. Optional
 : @param $keep-dims If true, retains reduced dimensions with size 1. Optional
 : @param $name A name for the operation. Optional
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:reduce-sum() external;


(:~
 : Computes the inverse cosine of $x element-wise.
 : @param $x The input tensor.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:acos() external;

(:~
 : Computes the inverse hyperbolic cosine of $x element-wise.
 : @param $x The input tensor.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:acosh() external;

(:~
 : Computes the inverse sine of $x element-wise.
 : @param $x The input tensor.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:asin() external;

(:~
 : Computes the inverse hyperbolic sine of $x element-wise.
 : @param $x The input tensor.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:asinh() external;

(:~
 : Computes the inverse tangent of $x element-wise.
 : @param $x The input tensor.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:atan() external;

(:~
 : Computes the inverse tangent of $a / $b element-wise.
 : @param $a The first tensor.
 : @param $b The second tensor. Must have the same dtype as $a.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:atan2() external;

(:~
 : Computes the inverse hyperbolic tangent of $x element-wise.
 : @param $x The input tensor.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:atanh() external;

(:~
 : Computes the cosine of $x element-wise.
 : @param $x The input tensor.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:cos() external;

(:~
 : Computes the hyperbolic cosine of $x element-wise.
 : @param $x The input tensor.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:cosh() external;

(:~
 : Computes the exponential of $x element-wise.
 : @param $x The input tensor.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:exp() external;

(:~
 : Computes the natural logarithm of $x element-wise.
 : @param $x The input tensor.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:log() external;

(:~
 : Computes the sine of $x element-wise.
 : @param $x The input tensor.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:sin() external;

(:~
 : Computes the hyperbolic sine of $x element-wise.
 : @param $x The input tensor.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:sinh() external;

(:~
 : Computes the square root of $x element-wise.
 : @param $x The input tensor.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:sqrt() external;

(:~
 : Computes the square of $x element-wise.
 : @param $x The input tensor.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:square() external;

(:~
 : Computes the tangent of $x element-wise.
 : @param $x The input tensor.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:tan() external;

(:~
 : Computes the hyperbolic tangent of $x element-wise.
 : @param $x The input tensor.
 : @param $name A name for the operation (optional).
 : @return tensor
 :)
declare %public %an:deterministic %an:variadic function numerics:tanh() external;

declare %public %an:deterministic %an:variadic function numerics:dtype() external;

declare %public %an:deterministic %an:variadic function numerics:constant() external;


declare %public %an:deterministic %an:variadic function numerics:pow() external;


declare %public %an:deterministic %an:variadic function numerics:asarray() external;

(:~
 : Return evenly spaced numbers over a specified interval.
 :)
declare %public %an:deterministic %an:variadic function numerics:linspace() external;
