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

module namespace data = "http://dgms.io/modules/data";

declare namespace an = "http://zorba.io/annotations";
declare namespace err = "http://dgms.io/errors";
declare namespace options = "http://dgms.io/options";

declare option options:source-transformation "disabled";

(:~
 : Two-dimensional size-mutable, potentially heterogeneous tabular data structure with labeled axes (rows and columns).
 :)
declare %public %an:deterministic %an:variadic function data:data-frame() external;

(:~
 : Normalize semi-structured JSON data into a flat table.
 :)
declare %public %an:deterministic %an:variadic function data:json-normalize() external;

(:~
 : Read a comma-separated values (csv) file into DataFrame.
 :)
declare %public %an:deterministic %an:variadic function data:read-csv() external;

(:~
 : Read an Excel file into a pandas DataFrame.
 :)
declare %public %an:deterministic %an:variadic function data:read-excel() external;

(:~
 : Read HTML tables into a list of DataFrame objects.
 :)
declare %public %an:deterministic %an:variadic function data:read-html() external;

(:~
 : Convert a JSON string to pandas object.
 :)
declare %public %an:deterministic %an:variadic function data:read-json() external;

(:~
 : One-dimensional ndarray with axis labels (including time series).
 :)
declare %public %an:deterministic %an:variadic function data:series() external;
