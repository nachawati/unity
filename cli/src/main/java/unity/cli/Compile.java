package unity.cli;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

import picocli.CommandLine.Command;
import picocli.CommandLine.Help;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import unity.kernel.engine.UnityAnalyticsContext;
import unity.kernel.engine.zorba.UnityZorbaStaticContext;

@Command(name = "compile", description = "Compile local script or query")
public class Compile extends Main.Command
{
    @Option(names = { "-q", "--query" }, description = "Compile query expression")
    protected Boolean    query      = false;

    @Parameters(description = "Script or query to execute", arity = "1..*", showDefaultValue = Help.Visibility.NEVER)
    private List<String> expression = new ArrayList<>();

    @Override
    public void exec() throws Exception
    {
        Path path = null;
        String expressions = "";
        if (expression != null && !expression.isEmpty())
            expressions = expression.stream().collect(Collectors.joining(" "));
        String script = null;

        if (query != null && query) {
            script = expressions;
        } else {
            path = UnityAnalyticsContext.getWorkspacePath().resolve(Paths.get(expressions));
            if (Files.isRegularFile(path)) {
                try (Reader reader = Files.newBufferedReader(path)) {
                    script = IOUtils.toString(reader);
                } catch (IOException e) {
                }
            }
        }

        if (script == null)
            throw new IOException("invalid file: " + expressions);

        System.out.println(UnityZorbaStaticContext.transform(script, true));
        System.out.println();
    }
}
