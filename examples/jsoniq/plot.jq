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

import module namespace n   = "http://dgms.io/modules/numerics";
import module namespace plt = "http://dgms.io/modules/plot";

declare function local:f($t)
{
	1 + n:sin(2 * $n:pi * $t)
};

declare function local:g($t)
{
    n:exp(-$t) * n:cos(2 * $n:pi * $t)
};

(: Plot #1 :)
variable $t := n:arange(0.0, 2.0, 0.01);
plt:plot($t, local:f($t));
plt:xlabel("X-axis");
plt:ylabel("Y-axis");
plt:title("Title");
plt:grid(true);
plt:show();

(: Plot #2 :)
variable $x := n:linspace(0, 50, 50);
variable $y := n:linspace(0, 50, 50) + n:uniform(-4, 4, 50);
plt:scatter($x, $y);
plt:show();

(: Plot #3 :)
variable $t1 := n:arange(0.0, 5.0, 0.1);
variable $t2 := n:arange(0.0, 5.0, 0.02);
plt:figure(1);
plt:subplot(211);
plt:plot($t1, local:g($t1), "bo", $t2, local:g($t2), "k");
plt:subplot(212);
plt:plot($t2, n:cos(2 * $n:pi * $t2), "r--");
plt:show();
