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
 : Example models from AlphaECP
 : @see http://www.minlplib.org/prob10.html
 : @see Westerlund, Tapio and Lundqvist, Kurt, Alpha-ECP, Version 5.01
 :      An Interactive MINLP-Solver Based on the Extended Cutting Plane Method,
 :      Tech. Rep. 01-178-A, Process Design Laboratory at Abo University, 2001.
 :)

import module namespace n = "http://dgms.io/modules/numerics";
import module namespace o = "http://dgms.io/modules/optimization";
import module namespace s = "http://dgms.io/modules/symbolics";

variable $x2 := s:variable({ name: "x2", bounds: [0, 10] });
variable $i3 := s:variable({ name: "i3", bounds: [0, 10], domain: "integer" });

o:solve({
    minimize: {
        obj: 1.1 * (n:square(-10 + 2 * $x2) + n:square(-5 + $i3)) +
             sin(n:square(-10 + 2 * $x2) + n:square(-5 + $i3))
    },
    subject-to: {
        e1: 0.7 * $x2 + $i3 <= 7,
        e2: 2.5 * $x2 + $i3 <= 19
    },
    options: {
        solver: "couenne"
    }
});
