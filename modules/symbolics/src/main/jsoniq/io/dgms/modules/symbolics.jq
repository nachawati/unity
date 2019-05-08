jsoniq version "1.0-dg";

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

module namespace symbolics = "http://dgms.io/modules/symbolics";

declare namespace an = "http://zorba.io/annotations";
declare namespace err = "http://dgms.io/errors";
declare namespace options = "http://dgms.io/options";

declare option options:source-transformation "disabled";

(:~
 : Constructs a symbolic function call.
 :)
declare %public %an:deterministic %an:variadic function symbolics:function() external;

(:~
 : Gets symbolic mode.
 :)
declare %public %an:nondeterministic %an:variadic function symbolics:get-mode() external;

(:~
 : Constructs a symbolic parameter.
 :)
declare %public %an:nondeterministic %an:variadic function symbolics:parameter() external;

(:~
 : Gets a reference.
 :)
declare %public %an:nondeterministic %an:variadic function symbolics:reference() external;

(:~
 : Sets symbolic mode
 :)
declare %public %an:nondeterministic %an:variadic function symbolics:set-mode() external;

(:~
 : Constructs a symbolic variable.
 :)
declare %public %an:nondeterministic %an:variadic function symbolics:variable() external;
