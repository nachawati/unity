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

package unity.cli;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.stream.Collectors;

import io.github.spencerpark.jupyter.channels.JupyterConnection;
import io.github.spencerpark.jupyter.channels.JupyterSocket;
import io.github.spencerpark.jupyter.kernel.KernelConnectionProperties;
import picocli.CommandLine.Command;
import picocli.CommandLine.Help;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import unity.kernel.UnityAnalyticsKernel;
import unity.kernel.engine.UnityAnalyticsContext;
import unity.kernel.engine.UnityAnalyticsEngine;
import unity.kernel.engine.UnityAnalyticsEngineManager;

@Command(name = "kernel", description = "Start kernel", hidden = true)
public class Kernel extends Main.Command
{
    @Parameters(description = "Connection file", arity = "1..*", showDefaultValue = Help.Visibility.NEVER)
    private java.util.List<String> connection;

    @Option(names = { "-e", "--engine" }, description = "Specify analytics engine")
    protected String               engine = "jsoniq10";

    @Override
    public void exec() throws Exception
    {
        String connectionFile = null;
        if (connection != null && !connection.isEmpty())
            connectionFile = connection.stream().collect(Collectors.joining(" "));

        Path connectionFilePath = Paths.get(connectionFile);
        if (!Files.isRegularFile(connectionFilePath))
            throw new IllegalArgumentException("Problem reading connection file: " + connectionFile);

        String contents = new String(Files.readAllBytes(connectionFilePath));

        Path basePath = UnityAnalyticsContext.getWorkspacePath();
        java.util.List<Path> additionalPaths = new LinkedList<Path>();

        if (Files.isDirectory(UnityAnalyticsContext.getPackagesPath())) {
            Files.list(UnityAnalyticsContext.getPackagesPath()).forEach(additionalPath -> {
                additionalPaths.add(additionalPath.toAbsolutePath());
            });
        }

        UnityAnalyticsEngineManager manager = new UnityAnalyticsEngineManager(basePath, additionalPaths.toArray(new Path[0]));

        JupyterSocket.JUPYTER_LOGGER.setLevel(Level.WARNING);

        KernelConnectionProperties connProps = KernelConnectionProperties.parse(contents);
        JupyterConnection connection = new JupyterConnection(connProps);

        UnityAnalyticsEngine _engine = manager.getEngineByName(engine);

        if (_engine == null)
            throw new Exception("analytics engine not found: " + engine);

        //UnityAnalyticsContext context = manager.getContext();

        UnityAnalyticsKernel kernel = new UnityAnalyticsKernel(manager, _engine);

        kernel.becomeHandlerForConnection(connection);

        connection.connect();
        connection.waitUntilClose();
    }
}
