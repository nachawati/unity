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

import module namespace ml = "http://dgms.io/modules/learning";
import module namespace n = "http://dgms.io/modules/numerics";
import module namespace r = "http://dgms.io/modules/random";
import module namespace s = "http://dgms.io/modules/symbolics";

s:set-mode("tensorflow");

variable $x := n:linspace(0, 100, 100) + r:uniform(-5, 5, 100);
variable $y := n:linspace(0, 100, 100) + r:uniform(-5, 5, 100);

variable $X := s:placeholder();
variable $Y := s:placeholder();

variable $W := s:variable(r:randn(), { name: "W" });
variable $b := s:variable(r:randn(), { name: "b" });

variable $y_pred := $X * $W + $b;

variable $loss := n:reduce-sum(n:pow($y_pred - $Y, 2)) div (2 * count($x));
