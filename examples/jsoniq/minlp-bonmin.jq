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
 : BARON book instance gupta/gupta08
 : @see http://www.minlplib.org/nvs08.html
 : @see Tawarmalani, M and Sahinidis, N V, Convexification and Global
 :		Optimization in Continuous and Mixed-Integer Nonlinear Programming:
 :		Theory, Algorithms, Software, and Applications, Kluwer, 2002.
 :)

import module namespace n = "http://dgms.io/modules/numerics";
import module namespace o = "http://dgms.io/modules/optimization";
import module namespace s = "http://dgms.io/modules/symbolics";

variable $i1 := s:variable(1, "i1", { bounds: [0, 200], domain: "integer" });
variable $i2 := s:variable(1, "i2", { bounds: [0, 200], domain: "integer" });
variable $x3 := s:variable(1, "x3", { bounds: [0.001, 200] });

o:solve({
    minimize: {
        obj: n:square(-3 + $i1) + n:square(-2 + $i2) + n:square(4 + $x3)
    },
    subject-to: {
        e1: n:sqrt($x3) + $i1 + 2 * $i2 >= 10,
        e2: 0.240038406144983 * n:square($i1) - $i2 + 0.255036980362153 * $x3 >= -3,
        e3: n:square($i2) - 1 div (n:power($x3, 3) * n:sqrt($x3)) - 4 * $i1 >= -12
    },
    options: {
        solver: "bonmin"
    }
});
