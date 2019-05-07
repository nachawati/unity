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

package unity.kernel;

import static org.joox.JOOX.$;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import javax.script.ScriptException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.joox.Match;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import unity.kernel.engine.UnityAnalyticsContext;
import unity.kernel.engine.UnityAnalyticsEngine;
import unity.kernel.engine.UnityAnalyticsEngineManager;

public class DocumentationGenerator
{
    private static String format(String text, int indent)
    {
        if (text == null)
            return "";
        String space = "";
        for (int i = 0; i < indent; i++)
            space += "  ";
        String[] lines = text.split(System.getProperty("line.separator"));
        StringBuilder sb = new StringBuilder();
        for (String line : lines)
            sb.append(space + line.trim() + System.getProperty("line.separator"));
        return sb.toString();
    }

    public static void main(String[] args) throws IOException, ScriptException, ParserConfigurationException, SAXException, TransformerException
    {
        try (PrintStream out = new PrintStream(new FileOutputStream(args[0]))) {

            Path basePath = Paths.get(args[2]);
            List<Path> additionalPaths = new LinkedList<Path>();

            if (Files.isDirectory(UnityAnalyticsContext.getPackagesPath()))
                Files.list(UnityAnalyticsContext.getPackagesPath()).forEach(additionalPath -> {
                    additionalPaths.add(additionalPath.toAbsolutePath());
                });

            for (int i = 3; i < args.length; i++)
                additionalPaths.add(Paths.get(args[i]).toAbsolutePath());

            UnityAnalyticsEngineManager manager = new UnityAnalyticsEngineManager(basePath, additionalPaths.toArray(new Path[0]));
            UnityAnalyticsEngine engine = manager.getEngineByName("jsoniq10-zorba31");

            String query = "jsoniq version \"1.0\";" + "import module namespace ns = \"" + args[1] + "\";"
                    + "import module namespace xqd = \"http://zorba.io/modules/xqdoc\";" + "xqd:xqdoc(\"" + args[1] + "\")";

            String result = (String) engine.eval(query, manager.getContext());

            System.out.println(result);

            Match document = $(result);

            Match module = document.find("module");

            out.println(".. role:: xquery(code)");
            out.println("   :language: xquery");
            out.println();
            out.println(module.find("name").text() + " Module");
            out.println("==========");
            out.println();
            out.println("**" + module.find("uri").text() + "**");
            out.println();
            out.println(format(module.find("comment").find("description").text(), 0));
            out.println();
            out.println("----------");
            out.println();
            out.println("Function Summary");
            out.println("**********");

            Match functions = document.find("functions");
            for (Element function : functions.children()) {
                out.println();
                out.println("----------");
                out.println();
                Match f = $(function);
                String name = f.find("name").text();
                String signature = f.find("signature").text();
                out.println(".. raw:: html");
                out.println();
                out.print("  <code class=\"function-summary\"><a href=\"#" + name.substring(name.indexOf(":") + 1) + "-" + f.attr("arity") + "\">"
                        + name.substring(name.indexOf(":") + 1) + "</a>");
                out.print(signature.substring(signature.indexOf("(")));
                out.print("</code>");
                out.println();
                out.println();
                out.println(format(f.find("comment").find("description").text(), 0));
            }

            out.println();
            out.println("----------");
            out.println();

            out.println("Functions");
            out.println("**********");

            for (Element function : functions.children()) {
                Match f = $(function);
                String name = f.find("name").text();
                String signature = f.find("signature").text();
                out.println();
                out.println("----------");
                out.println();
                out.println(".. _" + name.substring(name.indexOf(":") + 1) + "-" + f.attr("arity") + ":");
                out.println();
                out.println(name + "#" + f.attr("arity"));
                out.println("##########");
                out.println(".. raw:: html");
                out.println();
                out.print("  <code class=\"function-summary\">");
                out.print(signature);
                out.print("</code>");
                out.println();
                out.println();
                out.println();
                out.println(format(f.find("comment").find("description").text(), 0));
                out.println();

                Match parameters = f.find("parameters");
                if (parameters.isNotEmpty()) {
                    out.println("Parameters");
                    out.println("^^^^^^^^^^");
                    out.println();
                    for (Element parameter : parameters.children()) {
                        Match p = $(parameter);
                        String pName = p.find("name").text();
                        if (p.find("type").isNotEmpty()) {
                            out.println("- **$" + pName + " as " + p.find("type").text() + "**");
                        } else {
                            out.println("- **$" + pName + "**");
                        }
                        out.println();
                        String comment = f.find("comment").find("param").filter(m -> $(m.element()).text().startsWith("$" + pName)).text();
                        if (comment != null) {
                            out.println(format(comment.substring(pName.length() + 1), 1));
                            out.println();
                        }
                    }
                }

                Match returns = f.find("return");
                if (returns.isNotEmpty()) {
                    out.println("Returns");
                    out.println("^^^^^^^^^^");
                    if (returns.find("type").isNotEmpty()) {
                        out.println();
                        out.println("**" + returns.find("type").text() + "**");
                    }
                    out.println();
                    String comment = f.find("comment").find("return").text();
                    if (comment != null) {
                        out.println(format(comment, 0));
                        out.println();
                    }
                }

            }
        }
    }

}
