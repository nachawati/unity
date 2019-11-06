/*
 * بسم الله الرحمن الرحيم 
 *
 * In the name of Allah, the Most Compassionate, the Most Merciful
 *
 * This file is part of Unity DGMS <https://www.dgms.io/>
 *
 * Unity DGMS is free software; redistribution and use in source and binary
 * forms, with or without modification, are permitted provided that the
 * following conditions are met:
 *
 * 1. Redistributions of source code must retain the above notice, this list of
 *    conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above notice, this list
 *    of conditions and the following disclaimer in the documentation and/or
 *    other materials provided with the distribution.
 *
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHORS DISCLAIM ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION
 * OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN
 * CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package io.dgms.unity.kernel.engine;

import java.io.IOException;
import java.io.Reader;

import javax.script.AbstractScriptEngine;
import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptException;
import javax.script.SimpleBindings;

import org.apache.commons.io.IOUtils;

public abstract class UnityAnalyticsEngine extends AbstractScriptEngine implements AutoCloseable
{
    protected UnityAnalyticsEngineManager manager;

    @Override
    public Bindings createBindings()
    {
        return new SimpleBindings();
    }

    public abstract UnityAnalyticsEngineFactory getFactory();

    @Override
    public Object eval(Reader reader, ScriptContext context) throws ScriptException
    {
        try {
            return eval(IOUtils.toString(reader), context);
        } catch (IOException e) {
            throw new ScriptException(e);
        }
    }

    @Override
    protected UnityAnalyticsContext getScriptContext(Bindings nn)
    {
        try {
            return new UnityAnalyticsContext(super.getScriptContext(nn), manager);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
