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

import java.io.InputStream;
import java.util.List;
import java.util.Properties;
import java.util.logging.LogManager;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.IVersionProvider;
import picocli.CommandLine.Model.CommandSpec;
import picocli.CommandLine.Option;
import picocli.CommandLine.Spec;

@Command(
        name = "dgms",
        sortOptions = false,
        description = { "", "Unity DGMS is an open-source JSONiq analytics run-time environment for building model-driven decision guidance applications." },
        optionListHeading = "%nOptions:%n",
        commandListHeading = "%nCommands:%n",
        footer = { "", "Run 'dgms COMMAND --help' for more information on a command.%n" },
        mixinStandardHelpOptions = true,
        abbreviateSynopsis = true,
        versionProvider = Main.class,
        subcommands = { Console.class, Lab.class, Run.class })
public class Main implements IVersionProvider, Runnable
{
    public static synchronized String getVersionString()
    {
        String version = null;

        try (InputStream stream = Main.class.getResourceAsStream("/META-INF/maven/unity/unity-cli/pom.properties")) {
            Properties properties = new Properties();
            properties.load(stream);
            version = properties.getProperty("version");
        } catch (Exception e) {
        }

        return version;
    }

    public String[] getVersion()
    {
        return new String[] { "Unity DGMS version " + Ansi.ansi().bold().a(getVersionString()).reset() };
    }

    public static void printBanner()
    {
        System.out.println(Ansi.ansi().fg(Ansi.Color.RED).a("   __  __      _ __           ____  ________  ________").reset());
        System.out.println(Ansi.ansi().fg(Ansi.Color.RED).a("  / / / /___  (_) /___  __   / __ \\/ ____/  |/  / ___/").reset());
        System.out.println(Ansi.ansi().fg(Ansi.Color.RED).a(" / / / / __ \\/ / __/ / / /  / / / / / __/ /|_/ /\\__ \\").reset());
        System.out.println(Ansi.ansi().fg(Ansi.Color.RED).a("/ /_/ / / / / / /_/ /_/ /  / /_/ / /_/ / /  / /___/ /").reset());
        System.out.println(Ansi.ansi().fg(Ansi.Color.RED).a("\\____/_/ /_/_/\\__/\\__, /  /_____/\\____/_/  /_//____/").reset());
        System.out.println(Ansi.ansi().fg(Ansi.Color.RED).a("                 /____/").reset());
        System.out.println();
    }

    @Override
    public void run()
    {
        printBanner();
        CommandLine.usage(this, System.out);
    }

    public static void main(String... args)
    {
        try {
            LogManager.getLogManager().reset();
            AnsiConsole.systemInstall();
            CommandLine.run(new Main(), System.out, args);
        } finally {
            AnsiConsole.systemUninstall();
        }
    }

    @picocli.CommandLine.Command(
            synopsisHeading = "%nUsage:\t",
            descriptionHeading = "%n",
            optionListHeading = "%nOptions:%n%n",
            parameterListHeading = "%nParameters:%n%n",
            abbreviateSynopsis = true,
            showDefaultValues = true,
            sortOptions = false,
            commandListHeading = "%nCommands:%n%n",
            footer = { "" })
    public static abstract class Command implements Runnable
    {
        @Spec
        protected CommandSpec spec;

        @Option(names = { "-h", "--help" }, usageHelp = true, description = "Show this help message and exit.")
        boolean               help;

        public void exec() throws Exception
        {
            spec.commandLine().usage(System.out);
        }

        protected void printTable(List<Object[]> rows)
        {
            int columns = 0;
            for (Object[] row : rows)
                columns = Math.max(row.length, columns);
            int[] widths = new int[columns];
            for (Object[] row : rows)
                for (int j = 0; j < row.length; j++)
                    widths[j] = Math.max(row[j] != null ? row[j].toString().length() : 4, widths[j]);
            String[] formats = new String[widths.length];
            for (int i = 0; i < widths.length; i++)
                formats[i] = "%1$-" + widths[i] + "s" + (i < widths.length ? " " : "");
            for (Object[] row : rows) {
                for (int j = 0; j < row.length; j++)
                    System.out.printf(formats[j], row[j] != null ? row[j] : "null");
                System.out.println();
            }
            System.out.println();
            System.out.println("Count: " + Ansi.ansi().fgBright(Ansi.Color.GREEN).a(rows.size()).reset());
        }

        @Override
        public final void run()
        {
            try {
                if (help)
                    spec.commandLine().usage(System.out);
                else
                    exec();
            } catch (Exception e) {
                System.out.println(Ansi.ansi().fgBright(Ansi.Color.RED).a("[ERROR]").reset() + " " + e.getMessage());
                System.out.println();
                e.printStackTrace();
            }
        }
    }
}
