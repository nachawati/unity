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

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.script.ScriptContext;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.SimpleScriptContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.RegexFileFilter;
import org.apache.commons.lang3.SystemUtils;

public class UnityAnalyticsEngineManager extends ScriptEngineManager
{
    private final URLClassLoader                    classLoader;
    private final List<UnityAnalyticsEngineFactory> factories   = new LinkedList<>();
    private final List<Path>                        modulePaths = new LinkedList<Path>();

    public UnityAnalyticsEngineManager(ClassLoader classLoader, Path basePath, Path... additionalPaths) throws IOException
    {
        super(classLoader = init(classLoader, basePath, additionalPaths));

        if (System.getProperty("symbolics-mode") == null)
            Py_Initialize("pyomo");
        else
            Py_Initialize(System.getProperty("symbolics-mode"));

        this.classLoader = (URLClassLoader) classLoader;

        for (Path modulePath : listPaths(basePath, additionalPaths)) {
            modulePaths.add(modulePath);
            if (Files.isDirectory(modulePath.resolve("lib")))
                modulePaths.add(modulePath.resolve("lib"));
        }

        ServiceLoader<UnityAnalyticsEngineFactory> serviceLoader = ServiceLoader.load(UnityAnalyticsEngineFactory.class, classLoader);
        serviceLoader.forEach(factory -> {
            factory.manager = this;
            factories.add(factory);
        });
    }

    public UnityAnalyticsEngineManager(Path basePath, Path... additionalPaths) throws IOException
    {
        this(UnityAnalyticsEngineManager.class.getClassLoader(), basePath, additionalPaths);
    }

    public void finalize()
    {
        Py_Finalize();
    }

    public URLClassLoader getClassLoader()
    {
        return classLoader;
    }

    public UnityAnalyticsContext getContext() throws IOException
    {
        return new UnityAnalyticsContext(new SimpleScriptContext(), this);
    }

    public UnityAnalyticsContext getContext(ScriptContext context) throws IOException
    {
        return new UnityAnalyticsContext(context, this);
    }

    @Override
    public UnityAnalyticsEngine getEngineByExtension(String extension)
    {
        for (final UnityAnalyticsEngineFactory factory : factories) {

            List<String> extensions = null;
            try {
                extensions = factory.getExtensions();
            } catch (final Exception e) {
            } finally {
                if (extensions == null)
                    continue;
                for (final String value : extensions) {
                    if (extension.equals(value)) {
                        try {
                            final UnityAnalyticsEngine engine = factory.getScriptEngine();
                            return engine;
                        } catch (final Exception e) {
                        }
                    }
                }
            }
        }

        return null;
    }

    @Override
    public UnityAnalyticsEngine getEngineByMimeType(String mimeType)
    {
        for (final UnityAnalyticsEngineFactory factory : factories) {
            List<String> mimeTypes = null;
            try {
                mimeTypes = factory.getMimeTypes();
            } catch (final Exception e) {
            } finally {
                if (mimeTypes == null)
                    continue;
                for (final String value : mimeTypes) {
                    if (mimeType.equals(value)) {
                        try {
                            final UnityAnalyticsEngine engine = factory.getScriptEngine();
                            return engine;
                        } catch (final Exception e) {
                        }
                    }
                }
            }
        }

        return null;
    }

    @Override
    public UnityAnalyticsEngine getEngineByName(String shortName)
    {
        for (final UnityAnalyticsEngineFactory factory : factories) {
            List<String> shortNames = null;
            try {
                shortNames = factory.getNames();
            } catch (final Exception e) {
                e.printStackTrace();
            } finally {
                if (shortNames == null)
                    continue;
                for (final String value : shortNames) {
                    if (shortName.equals(value)) {
                        try {
                            final UnityAnalyticsEngine engine = factory.getScriptEngine();
                            return engine;
                        } catch (final Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return null;
    }

    @Override
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<ScriptEngineFactory> getEngineFactories()
    {
        return (List) factories;
    }

    public List<Path> getModulePaths()
    {
        return modulePaths;
    }

    private native void Py_Finalize();

    private native void Py_Initialize(String symbolicsMode);

    private final static Logger LOGGER = Logger.getLogger(UnityAnalyticsEngineManager.class.getName());

    static {
        try {
            System.loadLibrary("unity-kernel");
        } catch (Throwable t) {
            t.printStackTrace();
            LOGGER.log(Level.SEVERE, "Cannot load dependencies");
        }
    }

    public static Path getInstallPath()
    {
        try {
            String path = UnityAnalyticsContext.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            if (path.endsWith("classes/")) {
                File location = new File(path).getParentFile().getParentFile().getParentFile();
                if (SystemUtils.IS_OS_LINUX)
                    switch (SystemUtils.OS_ARCH) {
                    case "amd64":
                    case "x86_64":
                        return new File(location, "target/linux-gcc-amd64").toPath();
                    }
            }
            return new File(path).getParentFile().getParentFile().toPath();
        } catch (final URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static URLClassLoader init(ClassLoader loader, Path basePath, Path... additionalPaths)
    {
        StringBuilder libraryPath = new StringBuilder();
        List<URL> jars = new LinkedList<URL>();

        for (Path modulePath : listPaths(basePath, additionalPaths)) {
            Path libPath = modulePath.resolve("lib").toAbsolutePath();
            if (!Files.isDirectory(libPath))
                continue;
            libraryPath.append(File.pathSeparator);
            libraryPath.append(libPath);
            Path libPathShare = libPath.resolve("../share/java").toAbsolutePath();
            if (Files.isDirectory(libPathShare)) {
                libraryPath.append(File.pathSeparator);
                libraryPath.append(libPathShare);
            }
            FileUtils.listFiles(libPath.toFile(), new RegexFileFilter("^.*\\.jar$"), DirectoryFileFilter.DIRECTORY).forEach(file -> {
                try {
                    jars.add(file.toURI().toURL());
                } catch (MalformedURLException e) {
                }
            });
        }

        loader = new URLClassLoader(jars.toArray(new URL[jars.size()]), loader);
        try {
            System.setProperty("java.library.path", System.getProperty("java.library.path") + libraryPath);
            final Field sysPathsField = ClassLoader.class.getDeclaredField("sys_paths");
            sysPathsField.setAccessible(true);
            sysPathsField.set(null, null);
        } catch (Exception e) {
        }

        return (URLClassLoader) loader;
    }

    private static List<Path> listNodePaths(Path packagePath)
    {
        LinkedList<Path> paths = new LinkedList<>();

        if (Files.isDirectory(packagePath.resolve("node_modules"))) {
            try {
                Files.newDirectoryStream(packagePath.resolve("node_modules")).forEach(path -> {
                    if (path != null && Files.isDirectory(path)) {
                        if (path.getFileName().toString().startsWith("@")) {
                            try {
                                Files.newDirectoryStream(path).forEach(subPath -> {
                                    if (subPath != null && Files.isDirectory(subPath))
                                        paths.add(subPath.toAbsolutePath());
                                });
                            } catch (IOException e) {
                            }
                        } else {
                            paths.add(path.toAbsolutePath());
                        }
                    }
                });
            } catch (IOException e) {
            }
        }

        return paths;
    }

    public static List<Path> listPaths(Path basePath, Path... additionalPaths)
    {
        LinkedList<Path> paths = new LinkedList<>();
        paths.add(getInstallPath().toAbsolutePath());

        if (basePath != null && Files.isDirectory(basePath)) {
            paths.add(basePath.toAbsolutePath());
        }

        for (Path additionalPath : additionalPaths) {
            if (!Files.isDirectory(additionalPath))
                continue;
            paths.add(additionalPath.toAbsolutePath());
        }

        if (basePath != null && Files.isDirectory(basePath)) {
            paths.addAll(listNodePaths(basePath.toAbsolutePath()));
        }

        for (Path additionalPath : additionalPaths) {
            if (!Files.isDirectory(additionalPath))
                continue;
            paths.addAll(listNodePaths(additionalPath.toAbsolutePath()));
        }

        return paths;
    }
}
