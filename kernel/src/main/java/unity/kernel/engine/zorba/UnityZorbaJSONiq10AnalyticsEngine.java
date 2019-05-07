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

package unity.kernel.engine.zorba;

import java.io.IOException;
import java.io.Reader;

import javax.script.ScriptContext;
import javax.script.ScriptException;

import org.apache.commons.io.IOUtils;

import io.zorba.api.StaticContext;
import io.zorba.api.XQuery;
import unity.kernel.engine.UnityAnalyticsContext;
import unity.kernel.engine.UnityAnalyticsEngine;

public class UnityZorbaJSONiq10AnalyticsEngine extends UnityAnalyticsEngine
{
    private final UnityZorbaAnalyticsEngineFactory factory;

    public UnityZorbaJSONiq10AnalyticsEngine(UnityZorbaAnalyticsEngineFactory factory)
    {
        this.factory = factory;
    }

    @Override
    public void close() throws Exception
    {
    }

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
    public Object eval(String script, ScriptContext context) throws ScriptException
    {
        String result = null;
        XQuery query = null;
        StaticContext lctx = null;
        UnityZorbaStaticContext sctx = null;

        try {
            if (!(context instanceof UnityAnalyticsContext))
                context = new UnityAnalyticsContext(context, factory.getManger());
            sctx = new UnityZorbaStaticContext(lctx = UnityZorbaAnalyticsEngineFactory.getZorba().createStaticContext(), (UnityAnalyticsContext) context, true);

            try {
                if (((UnityAnalyticsContext) context).isSourceTransformation())
                    script = UnityZorbaStaticContext.transform(script, true);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            query = UnityZorbaAnalyticsEngineFactory.getZorba().compileQuery(script.trim(), sctx);
            sctx.configure(query);
            result = query.execute(UnityZorbaAnalyticsEngineFactory.getOptions());
        } catch (Exception e) {
            result = e.getMessage();
        } finally {
            if (query != null)
                query.destroy();
            sctx.close();
            if (sctx != null)
                sctx.destroy();
            if (lctx != null)
                lctx.destroy();
        }

        return result;
    }

    @Override
    public UnityZorbaAnalyticsEngineFactory getFactory()
    {
        return factory;
    }
}
