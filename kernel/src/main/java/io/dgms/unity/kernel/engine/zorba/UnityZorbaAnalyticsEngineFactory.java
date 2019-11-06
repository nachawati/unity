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

package io.dgms.unity.kernel.engine.zorba;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.dgms.unity.kernel.engine.UnityAnalyticsEngineFactory;
import io.zorba.api.InMemoryStore;
import io.zorba.api.SerializationOptions;
import io.zorba.api.Zorba;

public abstract class UnityZorbaAnalyticsEngineFactory extends UnityAnalyticsEngineFactory
{
    @Override
    public String getEngineName()
    {
        return "Unity/Zorba";
    }

    @Override
    public String getEngineVersion()
    {
        return "3.1";
    }

    @Override
    public String getMethodCallSyntax(String obj, String m, String... args)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public String getOutputStatement(String arg0)
    {
        throw new UnsupportedOperationException();
    }

    @Override
    public Object getParameter(String arg0)
    {
        return null;
    }

    @Override
    public String getProgram(String... arg0)
    {
        throw new UnsupportedOperationException();
    }

    public static final List<String>    EXTENSIONS = Collections
            .unmodifiableList(Arrays.asList("module", "jq", "jqm", "jqy", "jql", "jqu", "jsoniq", "xq", "xqm", "xqy", "xql", "xqu", "xquery"));

    private final static Logger         LOGGER     = Logger.getLogger(UnityZorbaJSONiq10AnalyticsEngineFactory.class.getName());

    private static SerializationOptions OPTIONS;

    private static Zorba                ZORBA;

    static {
        try {
            System.loadLibrary("zorba_simplestore");
            System.loadLibrary("zorba_api_java");
            System.loadLibrary("unity-kernel");
            OPTIONS = new SerializationOptions();
            OPTIONS.setOmitXMLDeclaration(SerializationOptions.OmitXMLDeclaration.ZORBA_API_OMIT_XML_DECLARATION_YES);
            OPTIONS.setIndent(SerializationOptions.Indent.ZORBA_API_INDENT_YES);
            ZORBA = Zorba.getInstance(InMemoryStore.getInstance());
            Runtime.getRuntime().addShutdownHook(new Thread()
            {
                @Override
                public void run()
                {
                    ZORBA.shutdown();
                    InMemoryStore.shutdown(InMemoryStore.getInstance());
                }
            });
        } catch (Throwable t) {
            LOGGER.log(Level.SEVERE, "Cannot load dependencies");
        }
    }

    public static SerializationOptions getOptions()
    {
        return OPTIONS;
    }

    public static Zorba getZorba()
    {
        return ZORBA;
    }
}
