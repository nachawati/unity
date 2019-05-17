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

module namespace learning = "http://dgms.io/modules/learning";

declare namespace an = "http://zorba.io/annotations";
declare namespace err = "http://dgms.io/errors";
declare namespace options = "http://dgms.io/options";

declare option options:source-transformation "disabled";

declare %public %an:nondeterministic %an:variadic function learning:absolute-difference() external;

declare %public %an:nondeterministic %an:variadic function learning:cosine-distance() external;

declare %public %an:nondeterministic %an:variadic function learning:hinge-loss() external;

declare %public %an:nondeterministic %an:variadic function learning:huber-loss() external;

declare %public %an:nondeterministic %an:variadic function learning:log-loss() external;

declare %public %an:nondeterministic %an:variadic function learning:mean-pairwise-squared-error() external;

declare %public %an:nondeterministic %an:variadic function learning:mean-squared-error() external;

declare %public %an:nondeterministic %an:variadic function learning:sigmoid-cross-entropy() external;

declare %public %an:nondeterministic %an:variadic function learning:softmax-cross-entropy() external;

declare %public %an:nondeterministic %an:variadic function learning:sparse-softmax-cross-entropy() external;

declare %public %an:nondeterministic %an:variadic function learning:train() external;

