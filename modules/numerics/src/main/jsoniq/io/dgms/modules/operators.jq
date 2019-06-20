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

module namespace operators = "http://dgms.io/modules/operators";

import module namespace n = "http://dgms.io/modules/numerics";
import module namespace r = "http://dgms.io/modules/reflection";

import module namespace math = "http://www.w3.org/2005/xpath-functions/math";
import module namespace reflection = "http://zorba.io/modules/reflection";

declare namespace an = "http://zorba.io/annotations";
declare namespace err = "http://dgms.io/errors";
declare namespace options = "http://dgms.io/options";

declare option options:source-transformation "disabled";

declare %public %an:deterministic function operators:add($x, $y)
{
	if ($x instance of xs:anyURI or $y instance of xs:anyURI) then
		n:add($x, $y)
	else
		$x + $y
};

declare %public %an:deterministic function operators:subtract($x, $y)
{
	if ($x instance of xs:anyURI or $y instance of xs:anyURI) then
		n:subtract($x, $y)
	else
		$x - $y
};

declare %public %an:deterministic function operators:multiply($x, $y)
{
	if ($x instance of xs:anyURI or $y instance of xs:anyURI) then
		n:multiply($x, $y)
	else
		$x * $y
};

declare %public %an:deterministic function operators:divide($x, $y)
{
	if ($x instance of xs:anyURI or $y instance of xs:anyURI) then
		n:divide($x, $y)
	else
		$x div $y
};

declare %public %an:deterministic function operators:integer-divide($x, $y)
{
	if ($x instance of xs:anyURI or $y instance of xs:anyURI) then
		n:floor-divide($x, $y)
	else
		$x idiv $y
};

declare %public %an:deterministic function operators:mod($x, $y)
{
	if ($x instance of xs:anyURI or $y instance of xs:anyURI) then
		n:mod($x, $y)
	else
		$x mod $y
};

declare %public %an:deterministic function operators:unary-plus($x)
{
	if ($x instance of xs:anyURI) then
		$x
	else
		+$x
};

declare %public %an:deterministic function operators:unary-minus($x)
{
	if ($x instance of xs:anyURI) then
		n:negative($x)
	else
		-$x
};

declare %public %an:deterministic function operators:logical-and($x, $y)
{
	if ($x instance of xs:boolean and $y instance of xs:boolean) then
		$x and $y
	else
		n:logical-and($x, $y)
};

declare %public %an:deterministic function operators:logical-or($x, $y)
{
	if ($x instance of xs:boolean and $y instance of xs:boolean) then
		$x or $y
	else
		n:logical-or($x, $y)
};

declare %public %an:deterministic function operators:logical-not($x)
{
	if ($x instance of xs:boolean) then
		fn:not($x)
	else
		n:logical-not($x)
};

declare %public %an:deterministic function operators:some($input)
{
	n:reduce-any($input)
};

declare %public %an:deterministic function operators:every($input)
{
	n:reduce-all($input)
};

declare %public %an:deterministic function operators:if-else($condition, $x, $y)
{
	if ($condition instance of xs:anyURI) then
		n:where($condition, $x, $y)
	else
		if ($condition) then $x else $y
};

declare %public %an:deterministic function operators:get-instance($x)
{
	if ($x instance of xs:anyURI) then
		0.0
	else
		$x
};

declare %public %an:deterministic function operators:value-equal($x, $y)
{
	if ($x instance of xs:anyURI or $y instance of xs:anyURI) then
		n:equal($x, $y)
	else
		$x eq $y
};

declare %public %an:deterministic function operators:value-not-equal($x, $y)
{
	if ($x instance of xs:anyURI or $y instance of xs:anyURI) then
		n:not-equal($x, $y)
	else
		$x ne $y
};

declare %public %an:deterministic function operators:value-less($x, $y)
{
	if ($x instance of xs:anyURI or $y instance of xs:anyURI) then
		n:less($x, $y)
	else
		$x lt $y
};

declare %public %an:deterministic function operators:value-greater($x, $y)
{
	if ($x instance of xs:anyURI or $y instance of xs:anyURI) then
		n:greater($x, $y)
	else
		$x gt $y
};

declare %public %an:deterministic function operators:value-less-equal($x, $y)
{
	if ($x instance of xs:anyURI or $y instance of xs:anyURI) then
		n:less-equal($x, $y)
	else
		$x le $y
};

declare %public %an:deterministic function operators:value-greater-equal($x, $y)
{
	if ($x instance of xs:anyURI or $y instance of xs:anyURI) then
		n:greater-equal($x, $y)
	else
		$x ge $y
};

declare %public %an:deterministic function operators:general-equal($x, $y)
{
	if ($x instance of xs:anyURI or $y instance of xs:anyURI) then
		n:equal($x, $y)
	else
		$x = $y
};

declare %public %an:deterministic function operators:general-not-equal($x, $y)
{
	if ($x instance of xs:anyURI or $y instance of xs:anyURI) then
		n:not-equal($x, $y)
	else
		$x != $y
};

declare %public %an:deterministic function operators:general-less($x, $y)
{
	if ($x instance of xs:anyURI or $y instance of xs:anyURI) then
		n:less($x, $y)
	else
		$x < $y
};

declare %public %an:deterministic function operators:general-greater($x, $y)
{
	if ($x instance of xs:anyURI or $y instance of xs:anyURI) then
		n:greater($x, $y)
	else
		$x > $y
};

declare %public %an:deterministic function operators:general-less-equal($x, $y)
{
	if ($x instance of xs:anyURI or $y instance of xs:anyURI) then
		n:less-equal($x, $y)
	else
		$x <= $y
};

declare %public %an:deterministic function operators:general-greater-equal($x, $y)
{
	if ($x instance of xs:anyURI or $y instance of xs:anyURI) then
		n:greater-equal($x, $y)
	else
		$x >= $y
};

declare %public %an:deterministic function operators:abs($x)
{
	if ($x instance of xs:anyURI) then
		n:abs($x)
	else
		fn:abs($x)
};

declare %public %an:deterministic function operators:ceiling($x)
{
	if ($x instance of xs:anyURI) then
		n:ceil($x)
	else
		fn:ceiling($x)
};

declare %public %an:deterministic function operators:floor($x)
{
	if ($x instance of xs:anyURI) then
		n:floor($x)
	else
		fn:floor($x)
};

declare %public %an:deterministic %an:variadic function operators:max() external;

declare %public %an:deterministic %an:variadic function operators:min() external;

declare %public %an:deterministic function operators:round($x)
{
	if ($x instance of xs:anyURI) then
		n:round($x)
	else
		fn:round($x)
};

declare %public %an:deterministic function operators:sum($args)
{
	if ($args = null) then
		0
	else
		n:reduce-sum($args)
};

declare %public %an:deterministic function operators:acos($x)
{
	if ($x instance of xs:anyURI) then
		n:acos($x)
	else
		math:acos($x)
};

declare %public %an:deterministic function operators:asin($x)
{
	if ($x instance of xs:anyURI) then
		n:asin($x)
	else
		math:asin($x)
};

declare %public %an:deterministic function operators:atan($x)
{
	if ($x instance of xs:anyURI) then
		n:atan($x)
	else
		math:atan($x)
};

declare %public %an:deterministic function operators:atan2($y, $x)
{
	if ($y instance of xs:anyURI or $x instance of xs:anyURI) then
		n:atan2($y, $x)
	else
		math:atan2($y, $x)
};

declare %public %an:deterministic function operators:cos($x)
{
	if ($x instance of xs:anyURI) then
		n:cos($x)
	else
		math:cos($x)
};

declare %public %an:deterministic function operators:exp($x)
{
	if ($x instance of xs:anyURI) then
		n:exp($x)
	else
		math:exp($x)
};

declare %public %an:deterministic function operators:exp10($x)
{
	if ($x instance of xs:anyURI) then
		n:pow(n:constant(10, { dtype: n:dtype($x) }), $x)
	else
		math:exp10($x)
};

declare %public %an:deterministic function operators:log($x)
{
	if ($x instance of xs:anyURI) then
		n:log($x)
	else
		math:log($x)
};

declare %public %an:deterministic function operators:log10($x)
{
	if ($x instance of xs:anyURI) then
  		n:divide(n:log($x), n:log(n:constant(10, { dtype: n:dtype($x) })))
  	else
		math:log10($x)
};

declare %public %an:deterministic function operators:pow($x, $n)
{
	if ($x instance of xs:anyURI or $n instance of xs:anyURI) then
		n:pow($x, $n)
	else
		math:pow($x, $n)
};

declare %public %an:deterministic function operators:sin($x)
{
	if ($x instance of xs:anyURI) then
		n:sin($x)
	else
		math:sin($x)
};

declare %public %an:deterministic function operators:sqrt($x)
{
	if ($x instance of xs:anyURI) then
		n:sqrt($x)
	else
		math:sqrt($x)
};

declare %public %an:deterministic function operators:tan($x)
{
	if ($x instance of xs:anyURI) then
		n:tan($x)
	else
		math:tan($x)
};

declare %public %an:deterministic function operators:set-serialization-mode($mode) external;

declare %public %an:deterministic function operators:serialization-mode() external;

declare %public %an:deterministic function operators:lookup($object, $member) external;

declare %public %an:deterministic function operators:object-lookup($object, $member)
{
	if ($object instance of xs:anyURI) then
		operators:lookup($object, $member)
	else
		$object.$member
};

declare %public %an:deterministic function operators:array-unboxing($object)
{
	if ($object instance of xs:anyURI) then
		r:invoke-variadic(operators:object-lookup($object, "tolist"))
	else
		$object[]
};

declare %private %an:deterministic function operators:serialize-item($arg, $mode) external;

declare %public %an:deterministic function operators:serialize($args, $mode)
{
	for $arg in $args
		return if ($arg instance of xs:anyURI) then
			operators:serialize-item($arg, $mode)
		else if ($arg instance of array) then
			[ for $i in $arg[] return operators:serialize($i, $mode) ]
		else if ($arg instance of object) then
			{| for $i in keys($arg) return { $i: operators:serialize($arg.$i, $mode) } |}
		else
			$arg
};
