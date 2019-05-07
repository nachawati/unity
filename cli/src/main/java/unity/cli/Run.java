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

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;

import picocli.CommandLine.Command;
import picocli.CommandLine.Help;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import unity.kernel.engine.UnityAnalyticsContext;
import unity.kernel.engine.UnityAnalyticsEngine;
import unity.kernel.engine.UnityAnalyticsEngineManager;

@Command(name = "run", description = "Execute local script or query")
public class Run extends Main.Command
{
    @Option(names = { "-e", "--engine" }, description = "Specify analytics engine")
    protected String     engine                      = "jsoniq10";

    @Option(names = { "--mode" }, description = "Specify symbolics mode")
    protected String     mode                        = "pyomo";

    @Option(names = { "-r", "--results" }, description = "Write results to file")
    protected String     results                     = null;

    @Option(names = { "-t", "--timing" }, description = "Print timing information")
    protected Boolean    timing                      = false;

    @Option(names = { "-m", "--memory" }, description = "Print memory usage information")
    protected Boolean    memory                      = false;

    @Option(names = { "-q", "--query" }, description = "Execute query expression")
    protected Boolean    query                       = false;

    @Option(names = { "--disable-source-transformation" }, description = "Disable source code transformation")
    protected Boolean    disableSourceTransformation = false;

    @Parameters(description = "Script or query to execute", arity = "1..*", showDefaultValue = Help.Visibility.NEVER)
    private List<String> expression                  = new ArrayList<>();

    @Override
    public void exec() throws Exception
    {
        Path basePath = UnityAnalyticsContext.getWorkspacePath();
        List<Path> additionalPaths = new LinkedList<Path>();

        if (Files.isDirectory(UnityAnalyticsContext.getPackagesPath())) {
            Files.list(UnityAnalyticsContext.getPackagesPath()).forEach(additionalPath -> {
                additionalPaths.add(additionalPath.toAbsolutePath());
            });
        }

        System.setProperty("symbolics-mode", mode);
        UnityAnalyticsEngineManager manager = new UnityAnalyticsEngineManager(basePath, additionalPaths.toArray(new Path[0]));

        Path path = null;
        boolean isPath = false;
        String expressions = "";
        if (expression != null && !expression.isEmpty())
            expressions = expression.stream().collect(Collectors.joining(" "));
        String script = null;

        if (query != null && query) {
            script = expressions;
        } else {
            path = UnityAnalyticsContext.getWorkspacePath().resolve(Paths.get(expressions));
            if (isPath = Files.isRegularFile(path)) {
                try (Reader reader = Files.newBufferedReader(path)) {
                    script = IOUtils.toString(reader);
                } catch (IOException e) {
                }
            }
        }

        if (script == null)
            throw new IOException("invalid file: " + expressions);

        UnityAnalyticsEngine _engine;

        if (isPath) {
            _engine = manager.getEngineByExtension(FilenameUtils.getExtension(path.getFileName().toString()));
            if (_engine == null)
                throw new Exception("analytics engine not found for extension: " + FilenameUtils.getExtension(path.getFileName().toString()));
        } else {
            _engine = manager.getEngineByName(engine);
            if (_engine == null)
                throw new Exception("analytics engine not found: " + engine);
        }

        UnityAnalyticsContext context = manager.getContext();

        if (disableSourceTransformation != null && disableSourceTransformation)
            context.setSourceTransformation(false);

        long startTime = 0;
        long finishTime = 0;

        try {
            startTime = System.currentTimeMillis();
            Object result = _engine.eval(script, context);
            finishTime = System.currentTimeMillis();
            if (results != null)
                FileUtils.writeStringToFile(new File(results), result.toString(), StandardCharsets.UTF_8);
            else
                System.out.println(result);
        } finally {

            if ((timing != null && timing) || (memory != null && memory)) {
                System.out.println();
                System.out.println("----------------------------");

                if (timing != null && timing) {
                    if (finishTime == 0)
                        finishTime = System.currentTimeMillis();
                    System.out.println(
                            "Execution time: " + Ansi.ansi().fgBright(Color.CYAN).a(DurationFormatUtils.formatDurationHMS(finishTime - startTime)).reset());
                }

                if (memory != null && memory) {
                    Runtime runtime = Runtime.getRuntime();
                    System.out.println(
                            "Memory usage: " + Ansi.ansi().fgBright(Color.CYAN).a(readableFileSize(runtime.totalMemory() - runtime.freeMemory())).reset() + " ["
                                    + Ansi.ansi().fgBright(Color.CYAN).a(readableFileSize(runtime.freeMemory())).reset() + "/"
                                    + Ansi.ansi().fgBright(Color.CYAN).a(readableFileSize(runtime.totalMemory())).reset() + "]");
                }
                System.out.println();
            }

            _engine.close();
        }
    }

    public static String readableFileSize(long size)
    {
        if (size <= 0)
            return "0";
        final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
        int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
        return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
    }
}
