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

import java.io.StringWriter;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.basex.util.Strings;

import io.dgms.unity.kernel.engine.UnityAnalyticsContext;
import io.dgms.unity.kernel.engine.UnityAnalyticsEngineManager;
import io.zorba.api.StaticContext;
import io.zorba.api.StringVector;
import io.zorba.api.XQuery;

public class UnityZorbaStaticContext extends StaticContext implements AutoCloseable
{
    private boolean                     closed;
    private final UnityAnalyticsContext context;
    private final Set<String>           modules      = new HashSet<>();
    private boolean                     jsoniq       = false;
    private final long                  resolverCPtr = 0;

    public UnityZorbaStaticContext(StaticContext sctx, UnityAnalyticsContext context, boolean jsoniq)
    {
        super(sctx);
        this.context = context;
        this.jsoniq = jsoniq;
        if (jsoniq)
            sctx.setJSONiqVersion(100);
        else
            sctx.setXQueryVersion(300);
        initialize();

        final StringVector uriPath = new StringVector();
        final StringVector libPath = new StringVector();
        uriPath.add(UnityAnalyticsEngineManager.getInstallPath().resolve("share/zorba/uris/core/3.1.0").toString());
        libPath.add(UnityAnalyticsEngineManager.getInstallPath().resolve("lib/zorba/core/3.1.0").toString());

        for (Iterator<Path> i = context.getModulePaths().iterator(); i.hasNext();) {
            Path modulePath = i.next();
            String modulePathString = modulePath.toAbsolutePath().toString();
            uriPath.add(modulePathString);
            libPath.add(modulePathString);
            if (Files.isDirectory(modulePath.resolve("python"))) {
                addPythonPath(modulePathString);
            }
        }

        sctx.setURIPath(uriPath);
        sctx.setLIBPath(libPath);
    }

    private native void addPythonPath(String path);

    @Override
    public void close()
    {
        if (!closed) {
            closed = true;
            release();
        }
    }

    public native void configure(XQuery query);

    @Override
    public void finalize()
    {
        close();
    }

    private native boolean initialize();

    private String map(final String uri, final int kind)
    {
        try {
            if (uri == null)
                return null;
            if (uri.startsWith("http://jsoniq.org"))
                return null;
            if (uri.startsWith("http://jsound.io"))
                return null;
            if (uri.startsWith("http://www.functx.com"))
                return null;
            if (uri.startsWith("http://www.w3.org"))
                return null;
            if (uri.startsWith("http://expath.org"))
                return null;
            if (uri.startsWith("http://www.zorba-xquery.com"))
                return null;
            if (uri.startsWith("http://zorba.io"))
                return null;

            String path = UnityAnalyticsContext.toPathString(URI.create(uri));
            if (!modules.contains(uri)) {
                modules.add(uri);
                Path resource = context.getResource(uri, UnityZorbaAnalyticsEngineFactory.EXTENSIONS);
                if (resource != null && Files.exists(resource)) {
                    Path python = Paths.get("/").resolve(resource.subpath(0, resource.getNameCount() - Paths.get(path).getNameCount())).resolve("python")
                            .resolve(path);
                    if (Files.isRegularFile(python.getParent().resolve(python.getFileName().toString() + ".py"))
                            || Files.isRegularFile(python.resolve("__init__.py")))
                        registerPythonModule(uri, "python." + Strings.className(path).toLowerCase());
                }
            }
            return "file:///" + path;
        } catch (final Exception e) {
            return null;
        }
    }

    private native boolean registerPythonModule(String uri, String name);

    private native boolean release();

    private String resolve(String uri, final int kind)
    {
        try {
            if (uri == null)
                return null;
            switch (kind) {
            case 1:
                if (uri.startsWith("file:///home/osboxes/Desktop/zorba-3.0/"))
                    return null;
                if (uri.startsWith("file:///org/jsoniq/"))
                    return null;
                if (uri.startsWith("file:///io/jsound/"))
                    return null;
                if (uri.startsWith("file:///com/functx/www/"))
                    return null;
                if (uri.startsWith("file:///org/w3/www/"))
                    return null;
                if (uri.startsWith("file:///org/expath/"))
                    return null;
                if (uri.startsWith("file:///com/zorba-xquery/www/"))
                    return null;
                if (uri.startsWith("file:///io/zorba/"))
                    return null;
                // if (uri.startsWith("file:///"))
                // uri = uri.substring(8);
                if (uri.endsWith(".module"))
                    uri = uri.substring(0, uri.length() - 7);
                String script = context.getResourceAsString(uri, UnityZorbaAnalyticsEngineFactory.EXTENSIONS).trim();
                if (script == null)
                    return null;
                try {
                    if (context.isSourceTransformation()) {
                        script = transform(script, jsoniq);
                        return script;
                    } else
                        return script;
                } catch (Exception e) {
                    return script;
                }
            default:
                return context.getResourceAsString(uri, Collections.emptyList());
            }
        } catch (final Exception e) {
            return null;
        }
    }

    public static String transform(String script, boolean jsoniq) throws Exception
    {
        script = script.trim();
        StringWriter writer = new StringWriter();
        String namespace_operators = "http://dgms.io/modules/operators";
        String namespace_reflection = "http://dgms.io/modules/reflection";

        if (script.startsWith("jsoniq")) {
            new UnityZorbaJSONiqTransformer(namespace_operators, namespace_reflection, script, writer, "jsoniq10").compile();
        } else if (script.startsWith("xquery")) {
            new UnityZorbaXQueryTransformer(namespace_operators, namespace_reflection, script, writer, "xquery30").compile();
        } else if (jsoniq) {
            new UnityZorbaJSONiqTransformer(namespace_operators, namespace_reflection, script, writer, "jsoniq10").compile();
        } else {
            new UnityZorbaXQueryTransformer(namespace_operators, namespace_reflection, script, writer, "xquery30").compile();
        }

        return writer.toString();
    }
}
