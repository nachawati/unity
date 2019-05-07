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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import io.github.spencerpark.jupyter.kernel.LanguageInfo;
import io.zorba.api.InMemoryStore;
import io.zorba.api.SerializationOptions;
import io.zorba.api.Zorba;
import unity.kernel.engine.UnityAnalyticsEngine;

public class UnityZorbaJSONiq10AnalyticsEngineFactory extends UnityZorbaAnalyticsEngineFactory
{
    private final LanguageInfo languageInfo;

    public UnityZorbaJSONiq10AnalyticsEngineFactory()
    {
        languageInfo = new LanguageInfo.Builder(getLanguageName()).version(getLanguageVersion()).mimetype("application/jsoniq").fileExtension(".jq")
                .pygments("xquery").codemirror("xquery").build();
    }

    @Override
    public List<String> getExtensions()
    {
        return EXTENSIONS;
    }

    @Override
    public String getLanguageName()
    {
        return "JSONiq 1.0/DGAL";
    }

    @Override
    public String getLanguageVersion()
    {
        return "1.0";
    }

    @Override
    public List<String> getMimeTypes()
    {
        return MIME_TYPES;
    }

    @Override
    public List<String> getNames()
    {
        return NAMES;
    }

    @Override
    public LanguageInfo getLanguageInfo()
    {
        return languageInfo;
    }

    @Override
    public UnityAnalyticsEngine getScriptEngine()
    {
        return new UnityZorbaJSONiq10AnalyticsEngine(this);
    }

    public static final List<String>    EXTENSIONS = Collections.unmodifiableList(Arrays.asList("jq", "jqm", "jqy", "jql", "jqu", "jsoniq"));

    private final static Logger         LOGGER     = Logger.getLogger(UnityZorbaJSONiq10AnalyticsEngineFactory.class.getName());

    public static final List<String>    MIME_TYPES = Collections.unmodifiableList(Arrays.asList("application/jsoniq", "text/jsoniq"));

    public static final List<String>    NAMES      = Collections.unmodifiableList(Arrays.asList("jsoniq10-zorba31"));

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
            t.printStackTrace();
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
