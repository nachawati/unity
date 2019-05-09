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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.fusesource.jansi.Ansi;

import picocli.CommandLine.Command;
import picocli.CommandLine.Help;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import unity.kernel.engine.UnityAnalyticsContext;

@Command(name = "generate", aliases = "g", description = "Generate analytical artifacts", subcommands = { Generate.Library.class, Generate.Query.class })
public class Generate extends Main.Command
{
    public static enum ModuleLanguage
    {
        JSONiq
    }

    @Command(name = "library", aliases = "l", description = "Generate library module")
    public static class Library extends Main.Command
    {
        @Option(names = { "--language", "-l" }, description = "Module language", required = false)
        private ModuleLanguage lang = ModuleLanguage.JSONiq;

        @Parameters(description = "Module namespace", arity = "1", showDefaultValue = Help.Visibility.NEVER)
        private URI            namespace;

        @Override
        public void exec() throws IOException
        {
            File path;
            if (namespace.getPath().endsWith(".jq")) {
                lang = ModuleLanguage.JSONiq;
                path = new File("lib", UnityAnalyticsContext.toPathString(namespace));
            } else {
                switch (lang) {
                case JSONiq:
                    path = new File("lib", UnityAnalyticsContext.toPathString(namespace) + ".jq");
                    break;
                default:
                    throw new RuntimeException("invalid module type");
                }
            }

            if (!Files.isRegularFile(Paths.get("package.json")))
                throw new RuntimeException("package.json not found; this command needs to be run at the base directory of a package");
            if (path.exists())
                throw new RuntimeException("file already exists");
            if (path.getParentFile() != null && !path.getParentFile().exists())
                path.getParentFile().mkdirs();
            System.out.print("Generating library module " + Ansi.ansi().bold().a(path).reset() + "... ");
            try (PrintStream out = new PrintStream(new FileOutputStream(path))) {
                switch (lang) {
                case JSONiq:
                    out.println("jsoniq version \"1.0\";");
                    out.println();
                    out.println("module namespace ns = \"" + namespace + "\";");
                    out.println();
                    out.println("import module namespace n = \"http://dgms.io/modules/numerics\";");
                    out.println();
                    out.println("declare function ns:model($input)");
                    out.println("{");
                    out.println("    let $x := $input.x");
                    out.println("    let $y := $input.y");
                    out.println("    return $x + $y");
                    out.println("};");
                    out.println();
                    break;
                }
            }

            System.out.println(Ansi.ansi().fgBright(Ansi.Color.GREEN).a("SUCCESS").reset());
            System.out.println();
        }
    }

    @Command(name = "query", aliases = "q", description = "Generate query module")
    public static class Query extends Main.Command
    {
        @Option(names = { "--language", "-l" }, description = "Module language", required = false)
        private ModuleLanguage lang = ModuleLanguage.JSONiq;

        @Parameters(description = "Module path", arity = "1", showDefaultValue = Help.Visibility.NEVER)
        private File           path;

        @Override
        public void exec() throws IOException
        {
            if (path.getName().endsWith(".jq"))
                lang = ModuleLanguage.JSONiq;
            else
                switch (lang) {
                case JSONiq:
                    path = new File(path.getParentFile(), path.getName() + ".jq");
                    break;
                default:
                    throw new RuntimeException("invalid module type");
                }

            if (path.exists())
                throw new RuntimeException("file already exists");
            if (path.getParentFile() != null && !path.getParentFile().exists())
                path.getParentFile().mkdirs();
            System.out.print("Generating query module " + Ansi.ansi().bold().a(path).reset() + "... ");
            try (PrintStream out = new PrintStream(new FileOutputStream(path))) {
                switch (lang) {
                case JSONiq:
                    out.println("jsoniq version \"1.0\";");
                    out.println();
                    out.println("import module namespace a = \"http://dgms.io/modules/analytics\";");
                    out.println("import module namespace n = \"http://dgms.io/modules/numerics\";");
                    out.println();
                    out.println("declare function local:model($input)");
                    out.println("{");
                    out.println("    let $x := $input.x");
                    out.println("    let $y := $input.y");
                    out.println("    return $x + $y");
                    out.println("};");
                    out.println();
                    out.println("local:model({x: 1, y: 1})");
                    out.println();
                    break;
                }
            }

            System.out.println(Ansi.ansi().fgBright(Ansi.Color.GREEN).a("SUCCESS").reset());
            System.out.println();
        }
    }
}
