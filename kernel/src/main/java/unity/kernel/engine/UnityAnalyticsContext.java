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

package unity.kernel.engine;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.script.Bindings;
import javax.script.ScriptContext;

import org.apache.commons.io.IOUtils;

public class UnityAnalyticsContext implements ScriptContext
{
    private final Map<String, Map<String, Path>> cache                = new HashMap<>();
    private final ScriptContext                  context;
    private final UnityAnalyticsEngineManager    manager;
    protected boolean                            sourceTransformation = true;

    public UnityAnalyticsContext(ScriptContext context, UnityAnalyticsEngineManager manager) throws IOException
    {
        this.context = context;
        this.manager = manager;
    }

    private Path cache(String uri, List<String> extensions)
    {
        Path path = null;
        Map<String, Path> paths = cache.get(uri);
        if (paths == null)
            return null;
        if ((path = paths.get("")) != null)
            return path;
        for (String extension : extensions)
            if ((path = paths.get(extension)) != null)
                return path;
        return null;
    }

    private void cache(String uri, String extension, Path path)
    {
        Map<String, Path> paths = cache.get(uri);
        if (paths == null)
            cache.put(uri, paths = new HashMap<String, Path>());
        paths.put(extension, path);
    }

    @Override
    public Object getAttribute(String name)
    {
        return context.getAttribute(name);
    }

    @Override
    public Object getAttribute(String name, int scope)
    {
        return context.getAttribute(name, scope);
    }

    @Override
    public int getAttributesScope(String name)
    {
        return context.getAttributesScope(name);
    }

    @Override
    public Bindings getBindings(int scope)
    {
        return context.getBindings(scope);
    }

    public ClassLoader getClassLoader()
    {
        return manager.getClassLoader();
    }

    @Override
    public Writer getErrorWriter()
    {
        return context.getErrorWriter();
    }

    public Stream<Path> getModulePaths()
    {
        return manager.getModulePaths().stream();
    }

    @Override
    public Reader getReader()
    {
        return context.getReader();
    }

    public Path getResource(String uri, List<String> extensions)
    {
        Path resource = cache(uri, extensions);
        if (resource != null)
            return resource;
        String suffix = toPathString(URI.create(uri));
        extensions = new LinkedList<>(extensions);
        for (Path path : manager.getModulePaths()) {
            for (String extension : extensions) {
                Path fullPath = path.resolve(suffix + "." + extension).toAbsolutePath();
                if (!fullPath.startsWith(path))
                    continue;
                if (Files.isRegularFile(fullPath)) {
                    cache(uri, extension, fullPath);
                    return fullPath;
                }
            }

            Path fullPath = path.resolve(suffix).toAbsolutePath();
            if (!fullPath.startsWith(path))
                continue;
            if (Files.isRegularFile(fullPath)) {
                cache(uri, "", fullPath);
                return fullPath;
            }
        }

        return null;
    }

    public Path getResource(String uri, String... extensions)
    {
        return getResource(uri, Arrays.asList(extensions));
    }

    public InputStream getResourceAsStream(String uri, List<String> extensions) throws IOException
    {
        Path path = getResource(uri, extensions);
        if (path != null)
            return Files.newInputStream(path);
        return null;
    }

    public InputStream getResourceAsStream(String uri, String... extensions) throws IOException
    {
        return getResourceAsStream(uri, Arrays.asList(extensions));
    }

    public String getResourceAsString(String uri, List<String> extensions) throws IOException
    {
        InputStream stream = getResourceAsStream(uri, extensions);
        if (stream != null)
            return IOUtils.toString(stream, StandardCharsets.UTF_8);
        return null;
    }

    public String getResourceAsString(String uri, String... extensions) throws IOException
    {
        return getResourceAsString(uri, Arrays.asList(extensions));
    }

    @Override
    public List<Integer> getScopes()
    {
        return context.getScopes();
    }

    @Override
    public Writer getWriter()
    {
        return context.getWriter();
    }

    public boolean isSourceTransformation()
    {
        return sourceTransformation;
    }

    @Override
    public Object removeAttribute(String name, int scope)
    {
        return context.removeAttribute(name, scope);
    }

    @Override
    public void setAttribute(String name, Object value, int scope)
    {
        context.setAttribute(name, value, scope);
    }

    @Override
    public void setBindings(Bindings bindings, int scope)
    {
        context.setBindings(bindings, scope);
    }

    @Override
    public void setErrorWriter(Writer writer)
    {
        context.setErrorWriter(writer);
    }

    @Override
    public void setReader(Reader reader)
    {
        context.setReader(reader);
    }

    public void setSourceTransformation(boolean sourceTransformation)
    {
        this.sourceTransformation = sourceTransformation;
    }

    @Override
    public void setWriter(Writer writer)
    {
        context.setWriter(writer);
    }

    public static Path getBinPath()
    {
        if (System.getenv("DGMS_BINPATH") != null)
            return Paths.get(System.getenv("DGMS_BINPATH")).toAbsolutePath();
        return Paths.get(System.getProperty("user.home")).resolve("bin").toAbsolutePath();
    }

    public static Path getLibPath()
    {
        if (System.getenv("DGMS_LIBPATH") != null)
            return Paths.get(System.getenv("DGMS_LIBPATH")).toAbsolutePath();
        return Paths.get(System.getProperty("user.home")).resolve("lib").toAbsolutePath();
    }

    public static Path getPackagesPath()
    {
        if (System.getenv("DGMS_PACKAGES") != null)
            return Paths.get(System.getenv("DGMS_PACKAGES")).toAbsolutePath();
        return Paths.get(System.getProperty("user.home")).resolve("packages").toAbsolutePath();
    }

    public static Path getSettingsPath()
    {
        Path path;
        if (System.getenv("DGMS_SETTINGS") != null)
            path = Paths.get(System.getenv("DGMS_SETTINGS")).toAbsolutePath();
        path = Paths.get(System.getProperty("user.home")).resolve(".dgms").toAbsolutePath();
        try {
            if (!Files.exists(path))
                Files.createDirectories(path);
        } catch (IOException e) {
        }
        return path;
    }

    public static Path getWorkspacePath()
    {
        if (System.getenv("DGMS_WORKSPACE") != null)
            return Paths.get(System.getenv("DGMS_WORKSPACE")).toAbsolutePath();
        return Paths.get("").toAbsolutePath();
    }

    public static String toPathString(URI uri)
    {
        uri = uri.normalize();
        final StringBuilder sb = new StringBuilder();
        if (uri.getHost() != null) {
            final String host = uri.getHost();
            final String[] hostParts = host.split("\\.");
            for (int i = hostParts.length - 1; i >= 0; i--) {
                sb.append(hostParts[i]);
                if (i > 0)
                    sb.append("/");
            }
            sb.append("/");
        }
        final String path = uri.getPath();
        final String[] pathParts = path.split("/");
        for (int i = 0; i < pathParts.length; i++) {
            if (pathParts[i].isEmpty())
                continue;
            sb.append(pathParts[i]);
            if (i < pathParts.length - 1)
                sb.append("/");
        }
        return sb.toString();
    }
}
