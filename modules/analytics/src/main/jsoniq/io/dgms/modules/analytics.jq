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
 : This module provides core analytical operators for decision guidance (DG)
 : analytics.
 :)

module namespace analytics = "http://dgms.io/modules/analytics";

import module namespace learning = "http://dgms.io/modules/learning";
import module namespace optimization = "http://dgms.io/modules/optimization";
import module namespace symbolics = "http://dgms.io/modules/symbolics";

import module namespace reflection = "http://zorba.io/modules/reflection";

declare namespace an = "http://zorba.io/annotations";
declare namespace err = "http://dgms.io/errors";
declare namespace options = "http://dgms.io/options";

declare option options:source-transformation "disabled";

(:~
 : Calibrate analytical model parameters
 :)
declare %public %an:nondeterministic function analytics:calibrate($kwargs)
{
	variable $mode := symbolics:get-mode();
	symbolics:set-mode(if (exists($kwargs.options.mode)) then $kwargs.options.mode else "tensorflow");
	variable $input := analytics:symbolify($kwargs.input);
	variable $output := analytics:symbolify($kwargs.output);
	variable $predicted := analytics:resolve-model($kwargs.model)($input);
	variable $loss := analytics:resolve-model($kwargs.loss)($predicted, $output);
	variable $result := learning:train({
		loss: $loss,
		feed: $kwargs.feed,
		options: $kwargs.options
	});
	symbolics:set-mode($mode);
	{
		solution: if (exists($result.solution)) then analytics:instantiate($input, $result.solution) else {},
		status: $result.status,
		termination-condition: $result.termination-condition
	}
};

(:~
 : Computes an analytical model with parametric input.
 :)
declare %public %an:nondeterministic %an:variadic function analytics:compute($kwargs)
{
	analytics:resolve-model($kwargs.model)(analytics:symbolify($kwargs.input))
};

(:~
 : Instantiate parametric object
 :)
declare %public %an:nondeterministic function analytics:instantiate($items, $solution)
{
	for $item in $items
	return
		if ($item instance of xs:anyURI) then
			if (fn:exists($solution.$item)) then
				$solution.$item
			else
				$item
    	else if ($item instance of object) then {|
        	for $key in keys($item)
        	return
          		{ $key: analytics:instantiate($item.$key, $solution) }
      	|}
    	else if ($item instance of array) then
      		[ for $element in $item[] return analytics:instantiate($element, $solution) ]
    	else $item
};

(:~
 : Find decision variable values that maximize analytical model objective
 :)
declare %public %an:nondeterministic function analytics:maximize($kwargs)
{
	variable $mode := symbolics:get-mode();
	symbolics:set-mode(if (exists($kwargs.options.mode)) then $kwargs.options.mode else "pyomo");
	variable $input := analytics:symbolify($kwargs.input);
	variable $output := analytics:resolve-model($kwargs.model)($input);
	variable $result := optimization:solve({
		maximize: $kwargs.objective($output),
		subject-to: if (exists($kwargs.constraints)) then $kwargs.constraints($output) else $output.constraints,
		options: $kwargs.options
	});
	symbolics:set-mode($mode);
	{
		solution: if (exists($result.solution)) then analytics:instantiate($input, $result.solution) else {},
		status: $result.status,
		termination-condition: $result.termination-condition
	}
};

(:~
 : Find decision variable values that minimize analytical model objective
 :)
declare %public %an:nondeterministic function analytics:minimize($kwargs)
{
	variable $mode := symbolics:get-mode();
	symbolics:set-mode(if (exists($kwargs.options.mode)) then $kwargs.options.mode else "pyomo");
	variable $input := analytics:symbolify($kwargs.input);
	variable $output := analytics:resolve-model($kwargs.model)($input);
	variable $result := optimization:solve({
		minimize: $kwargs.objective($output),
		subject-to: if (exists($kwargs.constraints)) then $kwargs.constraints($output) else $output.constraints,
		options: $kwargs.options
	});
	symbolics:set-mode($mode);
	{
		solution: if (exists($result.solution)) then analytics:instantiate($input, $result.solution) else {},
		status: $result.status,
		termination-condition: $result.termination-condition
	}
};

(:~
 : Constructs a parameter object.
 :)
declare %public %an:deterministic %an:variadic function analytics:parameter() external;

(:~
 : Constructs a placeholder object.
 :)
declare %public %an:deterministic %an:variadic function analytics:placeholder() external;

(:~
 : Resolve analytical model.
 :)
declare %private function analytics:resolve-model($model) as function(*)
{
    if ($model instance of function(*)) then
        $model
    else
        let $model-id := normalize-space($model)
        return if (starts-with($model-id, "Q{")) then
            reflection:eval("jsoniq version \"1.0\"; import module namespace ns = \"" || substring-before(substring-after($model-id, "Q{"), "}")  || "\"; ns:" || substring-before(substring-after($model-id, "}"), "#") || "#" || substring-after($model-id, "#"))
        else
            reflection:eval($model-id)
};

(:~
 : Constructs a symbolic object
 :)
declare %public %an:nondeterministic function analytics:symbolify($items)
{
    for $item in $items
    return
        if ($item instance of object) then
            if ($item."@type" eq "http://dgms.io/schema/Parameter") then
				symbolics:parameter({ value: $item.value, name: $item.name, shape: $item.shape, dtype: $item.dtype })
            else if ($item."@type" eq "http://dgms.io/schema/Placeholder") then
				symbolics:placeholder({ name: $item.name, shape: $item.shape, dtype: $item.dtype })
            else if ($item."@type" eq "http://dgms.io/schema/Variable") then
                symbolics:variable({ initialize: $item.initialize, name: $item.name, shape: $item.shape, dtype: $item.dtype, bounds: $item.bounds, domain: $item.domain })
            else if (exists($item."integer?")) then
                if ($item."integer?" eq null) then
                    symbolics:variable({ domain: "integer" })
                else if ($item."integer?" instance of object) then
                    if ($item."integer?".name) then
                        symbolics:variable($item."integer?".value, $item."integer?".name, { domain: "integer", bounds: $item."integer?".bounds })
                    else
                        symbolics:variable($item."integer?".value, { domain: "integer", bounds: $item."integer?".bounds })
                else
                    symbolics:variable(null, string($item."integer?"), { domain: "integer" })
			else if (exists($item."decimal?")) then
                if ($item."decimal?" eq null) then
                    symbolics:variable({ domain: "decimal" })
                else if ($item."decimal?" instance of object) then
                    if ($item."decimal?".name) then
                        symbolics:variable($item."decimal?".value, $item."decimal?".name, { domain: "decimal", bounds: $item."decimal?".bounds })
                    else
                        symbolics:variable($item."decimal?".value, { domain: "decimal", bounds: $item."decimal?".bounds })
                else
                    symbolics:variable(null, string($item."decimal?"), { domain: "decimal" })
            else if (exists($item."real?")) then
                if ($item."real?" eq null) then
                    symbolics:variable({ domain: "real" })
                else if ($item."real?" instance of object) then
                    if ($item."real?".name) then
                        symbolics:variable($item."real?".value, $item."real?".name, { domain: "real", bounds: $item."real?".bounds })
                    else
                        symbolics:variable($item."real?".value, { domain: "real", bounds: $item."real?".bounds })
                else
                    symbolics:variable(null, string($item."real?"), { domain: "real" })
			else if (exists($item."binary?")) then
                if ($item."binary?" eq null) then
                    symbolics:variable({ domain: "binary" })
                else if ($item."binary?" instance of object) then
                    if ($item."binary?".name) then
                        symbolics:variable($item."binary?".value, $item."binary?".name, { domain: "binary", bounds: $item."binary?".bounds })
                    else
                        symbolics:variable($item."binary?".value, { domain: "binary", bounds: $item."binary?".bounds })
                else
                    symbolics:variable(null, string($item."binary?"), { domain: "binary" })
            else if (exists($item."integer...")) then
                if ($item."integer..." eq null) then
                    symbolics:parameter(0, { domain: "integer" })
                else if ($item."integer..." instance of object) then
                    if ($item."integer...".name) then
                        symbolics:parameter($item."integer...".value, $item."integer...".name, { domain: "integer" })
                    else
                        symbolics:parameter($item."integer...".value, { domain: "integer" })
                else
                    symbolics:parameter(null, string($item."integer..."), { domain: "integer" })
            else if (exists($item."real...")) then
                if ($item."real..." eq null) then
                    symbolics:parameter(0, { domain: "real" })
                else if ($item."real..." instance of object) then
                    if ($item."real...".name) then
                        symbolics:parameter($item."real...".value, $item."real...".name, { domain: "real" })
                    else
                        symbolics:parameter($item."real...".value, { domain: "real" })
                else
                    symbolics:parameter(null, string($item."real..."), { domain: "real" })
			else if (exists($item."decimal...")) then
                if ($item."decimal..." eq null) then
                    symbolics:parameter(0, { domain: "decimal" })
                else if ($item."decimal..." instance of object) then
                    if ($item."decimal...".name) then
                        symbolics:parameter($item."decimal...".value, $item."decimal...".name, { domain: "decimal" })
                    else
                        symbolics:parameter($item."decimal...".value, { domain: "decimal" })
                else
                    symbolics:parameter(null, string($item."decimal..."), { domain: "decimal" })
            else if (exists($item."binary...")) then
                if ($item."binary..." eq null) then
                    symbolics:parameter(0, { domain: "binary" })
                else if ($item."binary..." instance of object) then
                    if ($item."binary...".name) then
                        symbolics:parameter($item."binary...".value, $item."binary...".name, { domain: "binary" })
                    else
                        symbolics:parameter($item."binary...".value, { domain: "binary" })
                else
                    symbolics:parameter(null, string($item."binary..."), { domain: "binary" })
            else {|
            	for $key in keys($item)
        		return { $key : analytics:symbolify($item.$key) }
            |}
        else if ($item instance of array) then
            [ for $element in $item[] return analytics:symbolify($element) ]
        else $item
};

(:~
 : Constructs a variable object.
 :)
declare %public %an:deterministic %an:variadic function analytics:variable() external;
