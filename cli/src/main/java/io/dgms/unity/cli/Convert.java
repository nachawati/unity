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

package io.dgms.unity.cli;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import io.dgms.unity.client.UnityClient;
import io.dgms.unity.kernel.engine.UnityAnalyticsContext;
import net.vatov.ampl.AmplParser;
import net.vatov.ampl.model.ConstraintDeclaration;
import net.vatov.ampl.model.ConstraintDeclaration.RelopType;
import net.vatov.ampl.model.Expression;
import net.vatov.ampl.model.Expression.ExpressionType;
import net.vatov.ampl.model.NodeValue;
import net.vatov.ampl.model.ObjectiveDeclaration;
import net.vatov.ampl.model.ObjectiveDeclaration.Goal;
import net.vatov.ampl.model.OptimModel;
import net.vatov.ampl.model.SymbolDeclaration;
import net.vatov.ampl.model.SymbolDeclaration.SymbolType;
import picocli.CommandLine.Command;
import picocli.CommandLine.Help;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "convert", description = "Transform AMPL model instances to native DGAL format")
public class Convert extends Main.Command
{
    @Option(names = { "-f", "--force" }, description = "Force overwrite existing files", required = false)
    private Boolean      force           = false;

    @Option(names = { "-n", "--namespace" }, description = "Module namespace", required = false)
    private String       moduleNamespace = "http://dgms.io/contrib/models/";

    @Option(names = { "-d", "--dest" }, description = "Destination path", required = false)
    private String       destination     = ".";

    @Option(names = { "-p", "--package" }, description = "Package name", required = false)
    private String       packageName     = "model";

    @Option(names = { "-v", "--version" }, description = "Package version", required = false)
    private String       packageVersion  = "1.0.0";

    @Option(names = { "-s", "--solver" }, description = "Solver", required = false)
    private String       solver          = "bonmin";

    @Parameters(description = "Source files/folders to convert", arity = "1..*", showDefaultValue = Help.Visibility.NEVER)
    private List<String> sources         = new ArrayList<>();

    private void translate(PrintStream out, Expression expression)
    {
        if (expression.getType() == ExpressionType.DOUBLE) {
            out.print(expression.getValue());
        } else if (expression.getType() == ExpressionType.SYMREF) {
            out.print("$" + expression.getSymRef().getName());
        } else {
            NodeValue node = expression.getTreeValue();
            switch (node.getOperation()) {
            case PLUS:
                out.print("(");
                translate(out, node.getOperands()[0]);
                out.print(" + ");
                translate(out, node.getOperands()[1]);
                out.print(")");
                break;
            case MINUS:
                out.print("(");
                translate(out, node.getOperands()[0]);
                out.print(" - ");
                translate(out, node.getOperands()[1]);
                out.print(")");
                break;
            case MULT:
                out.print("(");
                translate(out, node.getOperands()[0]);
                out.print(" * ");
                translate(out, node.getOperands()[1]);
                out.print(")");
                break;
            case DIV_SLASH:
                out.print("(");
                translate(out, node.getOperands()[0]);
                out.print(" idiv ");
                translate(out, node.getOperands()[1]);
                out.print(")");
                break;
            case MOD:
                out.print("(");
                translate(out, node.getOperands()[0]);
                out.print(" mod ");
                translate(out, node.getOperands()[1]);
                out.print(")");
                break;
            case DIV:
                out.print("(");
                translate(out, node.getOperands()[0]);
                out.print(" div ");
                translate(out, node.getOperands()[1]);
                out.print(")");
                break;
            case POW:
                out.print("math:pow(");
                translate(out, node.getOperands()[0]);
                out.print(", ");
                translate(out, node.getOperands()[1]);
                out.print(")");
                break;
            case UNARY_MINUS:
                out.print("-");
                translate(out, node.getOperands()[0]);
                break;
            case UNARY_PLUS:
                out.print("+");
                translate(out, node.getOperands()[0]);
                break;
            case BUILTIN_FUNCTION:
                switch (node.getBuiltinFunction()) {
                case ABS:
                    out.print("math:abs(");
                    translate(out, node.getOperands()[0]);
                    out.print(")");
                    break;
                case ACOS:
                    out.print("math:acos(");
                    translate(out, node.getOperands()[0]);
                    out.print(")");
                    break;
                case ACOSH:
                    out.print("math:acosh(");
                    translate(out, node.getOperands()[0]);
                    out.print(")");
                    break;
                case ASIN:
                    out.print("math:asin(");
                    translate(out, node.getOperands()[0]);
                    out.print(")");
                    break;
                case ASINH:
                    out.print("math:asinh(");
                    translate(out, node.getOperands()[0]);
                    out.print(")");
                    break;
                case ATAN:
                    out.print("math:atan(");
                    translate(out, node.getOperands()[0]);
                    out.print(")");
                    break;
                case ATAN2:
                    out.print("math:atan2(");
                    translate(out, node.getOperands()[0]);
                    out.print(", ");
                    translate(out, node.getOperands()[1]);
                    out.print(")");
                    break;
                case ATANH:
                    out.print("math:atanh(");
                    translate(out, node.getOperands()[0]);
                    out.print(")");
                    break;
                case CEIL:
                    out.print("math:ceil(");
                    translate(out, node.getOperands()[0]);
                    out.print(", ");
                    translate(out, node.getOperands()[1]);
                    out.print(")");
                    break;
                case COS:
                    out.print("math:cos(");
                    translate(out, node.getOperands()[0]);
                    out.print(")");
                    break;
                case EXP:
                    out.print("math:exp(");
                    translate(out, node.getOperands()[0]);
                    out.print(", ");
                    translate(out, node.getOperands()[1]);
                    out.print(")");
                    break;
                case FLOOR:
                    out.print("math:floor(");
                    translate(out, node.getOperands()[0]);
                    out.print(", ");
                    translate(out, node.getOperands()[1]);
                    out.print(")");
                    break;
                case LOG:
                    out.print("math:log(");
                    translate(out, node.getOperands()[0]);
                    out.print(")");
                    break;
                case LOG10:
                    out.print("math:log10(");
                    translate(out, node.getOperands()[0]);
                    out.print(")");
                    break;
                case MAX:
                    out.print("math:max(");
                    translate(out, node.getOperands()[0]);
                    out.print(", ");
                    translate(out, node.getOperands()[1]);
                    out.print(")");
                    break;
                case MIN:
                    out.print("math:min(");
                    translate(out, node.getOperands()[0]);
                    out.print(", ");
                    translate(out, node.getOperands()[1]);
                    out.print(")");
                    break;
                case ROUND:
                    out.print("math:round(");
                    translate(out, node.getOperands()[0]);
                    out.print(")");
                    break;
                case SIN:
                    out.print("math:sin(");
                    translate(out, node.getOperands()[0]);
                    out.print(")");
                    break;
                case SINH:
                    out.print("math:sinh(");
                    translate(out, node.getOperands()[0]);
                    out.print(")");
                    break;
                case SQRT:
                    out.print("math:sqrt(");
                    translate(out, node.getOperands()[0]);
                    out.print(")");
                    break;
                case TAN:
                    out.print("math:tan(");
                    translate(out, node.getOperands()[0]);
                    out.print(")");
                    break;
                case TANH:
                    out.print("math:tanh(");
                    translate(out, node.getOperands()[0]);
                    out.print(")");
                    break;
                default:
                    throw new RuntimeException("Invalid function: " + expression.getTreeValue().getBuiltinFunction());
                }
                break;
            default:
                throw new RuntimeException("Invalid operation: " + expression.getTreeValue().getOperation());
            }
        }
    }

    private void translate(String name, URI namespace, Path source, Path binFile, Path libFile) throws IOException
    {
        AmplParser parser = new AmplParser();

        try (InputStream stream = Files.newInputStream(source)) {
            String string = IOUtils.toString(stream, "UTF-8");
            string = string.replaceAll("\\*\\*", "^");
            string = string.replaceAll("(?m)#.*", "");

            try (InputStream input = IOUtils.toInputStream(string, "UTF-8")) {
                OptimModel model = parser.parse(input);

                try (PrintStream out = new PrintStream(Files.newOutputStream(libFile))) {
                    out.println("jsoniq version \"1.0\";");
                    out.println();
                    out.println("module namespace ns = \"" + namespace + "\";");
                    out.println();
                    out.println("import module namespace math = \"http://www.w3.org/2005/xpath-functions/math\";");
                    out.println();
                    out.println("declare function ns:" + name + "($input)");
                    out.println("{");
                    for (SymbolDeclaration symbol : model.getSymbolDeclarations()) {
                        if (symbol.getType() == SymbolType.VAR) {
                            out.println("    let $" + symbol.getName() + " := $input." + symbol.getName());
                        } else {
                            throw new IOException("invalid symbol type: " + symbol.getType());
                        }
                    }

                    out.println("    return {");
                    for (ObjectiveDeclaration objective : model.getObjectives()) {
                        out.print("        \"" + objective.getName() + "\": ");
                        translate(out, objective.getExpression());
                        out.println(",");
                    }

                    out.println("        \"constraints\": {");

                    
                    List<ConstraintDeclaration> constraints = new LinkedList<ConstraintDeclaration>();
                    for (ConstraintDeclaration constraint : model.getConstraints()) {
                        if (constraint.getName().startsWith("var_") && (constraint.getName().endsWith("_lower") || constraint.getName().endsWith("_upper")))
                            continue;
                        constraints.add(constraint);
                    }
                    int k = 0;
                    int o = constraints.size();

                    for (ConstraintDeclaration constraint : constraints) {
                        out.print("            \"" + constraint.getName() + "\": ");
                        translate(out, constraint.getaExpr());
                        if (constraint.getRelop() == RelopType.GE)
                            out.print(" >= ");
                        else if (constraint.getRelop() == RelopType.LE)
                            out.print(" <= ");
                        else
                            out.print(" = ");
                        translate(out, constraint.getbExpr());
                        if (++k < o)
                            out.println(",");
                    }

                    out.println();
                    out.println("        }");
                    out.println("    }");

                    out.println("};");
                    out.println();
                }

                try (PrintStream out = new PrintStream(Files.newOutputStream(binFile))) {
                    out.println("jsoniq version \"1.0\";");
                    out.println();
                    out.println("import module namespace a = \"http://dgms.io/modules/analytics\";");
                    out.println();
                    out.println("import module namespace ns = \"" + namespace + "\";");
                    out.println();
                    out.println("let $input := {");
                    int i = 0;
                    int n = model.getSymbolDeclarations().size();
                    for (SymbolDeclaration symbol : model.getSymbolDeclarations()) {
                        if (symbol.getType() == SymbolType.VAR) {
                            out.print("    \"" + symbol.getName() + "\": a:variable({ name: \"" + symbol.getName() + "\"");
                            if (symbol.isBinary())
                                out.print(", domain: \"binary\"");
                            else if (symbol.isInteger())
                                out.print(", domain: \"integer\"");
                            if (symbol.getLowerBound() != null || symbol.getUpperBound() != null) {
                                out.print(", bounds: [");
                                if (symbol.getLowerBound() != null) {
                                    translate(out, symbol.getLowerBound());
                                } else {
                                    out.print("null");
                                }
                                out.print(", ");
                                if (symbol.getUpperBound() != null) {
                                    translate(out, symbol.getUpperBound());
                                } else {
                                    out.print("null");
                                }
                                out.print("]");
                            }
                            out.print(" })");
                            if (++i < n)
                                out.println(",");
                        } else {
                            throw new IOException("invalid symbol type: " + symbol.getType());
                        }
                    }

                    out.println();
                    out.println("} return {");

                    int j = 0;
                    int m = model.getObjectives().size();
                    for (ObjectiveDeclaration objective : model.getObjectives()) {
                        out.print("    \"" + objective.getName() + "\": ");
                        if (objective.getGoal() == Goal.MINIMIZE) {
                            out.println("a:minimize({");
                        } else {
                            out.println("a:maximize({");
                        }

                        out.println("        model: ns:" + name + "#1,");
                        out.println("        input: $input,");
                        out.println("        objective: function($output) { $output.\"" + objective.getName() + "\" },");
                        out.println("        options: { solver: \"" + solver + "\" }");
                        out.print("    })");

                        if (++j < m)
                            out.println(",");
                    }

                    out.println();
                    out.println("}");
                }
            }
        }
    }

    private void convert(Path source, URI namespace, Path binFile, Path libFile)
    {
        try {
            System.out.println("Processing: " + source);
            if (force || !Files.exists(libFile) || Files.size(libFile) == 0) {
                String name = source.getFileName().toString();
                name = name.substring(0, name.lastIndexOf("."));
                translate(name, namespace, source, binFile, libFile);
            } else {
                System.out.println("Skipping (already exists, use --force to overwrite)");
            }
        } catch (Throwable e) {
            System.err.println("Error processing " + source.getFileName() + ": " + e.getMessage());
            try {
                Files.deleteIfExists(binFile);
                Files.deleteIfExists(libFile);
            } catch (Throwable t) {
            }
        }
    }

    @Override
    public void exec(UnityClient client) throws Exception
    {
        Path destinationPath = Paths.get(destination).resolve(packageName);
        Files.createDirectories(destinationPath);

        try (PrintStream out = new PrintStream(Files.newOutputStream(destinationPath.resolve("package.json")))) {
            out.println("{");
            out.println("    \"name\": \"" + packageName + "\",");
            out.println("    \"version\": \"" + packageVersion + "\"");
            out.println("}");
        }

        Path binPath = destinationPath.resolve("bin");
        Files.createDirectories(binPath);

        URI namespaceUri = URI.create(moduleNamespace);
        Path libPath = destinationPath.resolve("lib").resolve(UnityAnalyticsContext.toPathString(namespaceUri));
        Files.createDirectories(libPath);

        for (String source : sources) {
            Path sourcePath = Paths.get(source);

            if (Files.isDirectory(sourcePath)) {
                Files.list(sourcePath).parallel().forEach(path -> {
                    if (path.toString().endsWith(".mod")) {
                        String name = path.getFileName().toString();
                        Path binFile = binPath.resolve(name.substring(0, name.indexOf(".mod")) + ".jq");
                        Path libFile = libPath.resolve(name.substring(0, name.indexOf(".mod")) + ".jq");
                        convert(path, namespaceUri.resolve(name.substring(0, name.indexOf(".mod"))), binFile, libFile);
                    }
                });
            } else {
                if (sourcePath.toString().endsWith(".mod")) {
                    String name = sourcePath.getFileName().toString();
                    Path binFile = binPath.resolve(name.substring(0, name.indexOf(".mod")) + ".jq");
                    Path libFile = libPath.resolve(name.substring(0, name.indexOf(".mod")) + ".jq");
                    convert(sourcePath, namespaceUri.resolve(name.substring(0, name.indexOf(".mod"))), binFile, libFile);
                } else {
                    System.err.println("Skipping (unsupported format): " + sourcePath);
                }
            }
        }
    }
}
