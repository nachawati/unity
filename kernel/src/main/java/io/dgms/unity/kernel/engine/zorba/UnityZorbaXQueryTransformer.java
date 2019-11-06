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

import java.io.PrintWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.Lists;

import io.dgms.unity.kernel.language.UnitySyntaxTreeNode;
import io.dgms.unity.kernel.language.UnitySyntaxTreeNode.Type;
import io.dgms.unity.kernel.language.xquery.XQuery30Parser;
import io.dgms.unity.kernel.language.xquery.XQuerySyntaxTreeBuilder;

public class UnityZorbaXQueryTransformer
{
    private final String              operatorsNamespace;
    private final String              reflectionNamespace;
    protected PrintWriter             out;
    protected String                  prefix_operators;
    protected String                  prefix_reflection;
    protected Map<String, String>     prefixes                     = new HashMap<String, String>();
    protected boolean                 sourceTransformationDisabled = false;
    protected XQuerySyntaxTreeBuilder treeBuilder                  = new XQuerySyntaxTreeBuilder();
    protected XQuery30Parser          xquery30;

    public UnityZorbaXQueryTransformer(String operatorsNamespace, String reflectionNamespace, CharSequence string, Writer writer, String language)
    {
        this.operatorsNamespace = operatorsNamespace;
        this.reflectionNamespace = reflectionNamespace;
        if ("xquery30".equals(language))
            xquery30 = new XQuery30Parser(string, treeBuilder);
        this.out = new PrintWriter(writer, true);
        prefixes.put("fn", "http://www.w3.org/2005/xpath-functions");
        prefixes.put("math", "http://www.w3.org/2005/xpath-functions/math");
    }

    private void addNamespacePrefixes()
    {
        if (prefix_operators == null) {
            prefix_operators = "__";
            for (int i = 0; prefixes.containsKey(prefix_operators); i++)
                prefix_operators = "__" + i;
            out.print("import module namespace " + prefix_operators + " = \"" + operatorsNamespace + "\";");
        }

        if (prefix_reflection == null) {
            prefix_reflection = "___";
            for (int i = 0; prefixes.containsKey(prefix_reflection); i++)
                prefix_reflection = "___" + i;
            out.print("import module namespace " + prefix_reflection + " = \"" + reflectionNamespace + "\";");
        }
    }

    public void compile() throws Exception
    {
        try {
            if (xquery30 != null)
                xquery30.parse_XQuery();
            treeBuilder.getNode().accept(this);
            out.flush();
        } catch (XQuery30Parser.ParseException e) {
            throw new Exception(xquery30.getErrorMessage(e));
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    protected String getName(String name)
    {
        if (name.contains(":")) {
            String[] parts = name.split(":");
            return parts[1];
        }
        return name;
    }

    protected String getNamespace(String name)
    {
        if (name.contains(":")) {
            String[] parts = name.split(":");
            return prefixes.get(parts[0]);
        }
        return "http://www.w3.org/2005/xpath-functions";
    }

    public void visit(UnitySyntaxTreeNode node) throws Exception
    {
        for (UnitySyntaxTreeNode child : node.getChildren())
            child.accept(this);
    }

    public void visit_AdditiveExpr(UnitySyntaxTreeNode node) throws Exception
    {
        if (sourceTransformationDisabled) {
            visit(node);
            return;
        }

        if (!node.hasChildTerminal("'+'") && !node.hasChildTerminal("'-'")) {
            visit(node);
            return;
        }

        int c = 0;

        for (UnitySyntaxTreeNode child : Lists.reverse(node.getChildren())) {
            if (child.getType() == Type.TERMINAL) {
                if ("'+'".equals(child.getName()))
                    out.print(prefix_operators + ":add(");
                else if ("'-'".equals(child.getName()))
                    out.print(prefix_operators + ":subtract(");
                else
                    throw new Exception("Invalid operator: " + child.getName());
                c++;
            }
        }

        int i = 0;

        for (UnitySyntaxTreeNode child : node.getChildren()) {
            if (child.getType() == Type.TERMINAL) {
                ;
            } else if (child.getType() == Type.NONTERMINAL) {
                child.accept(this);
                if (i != 0)
                    out.print(")");
                if (i < c)
                    out.print(",");
                i++;
            } else {
                child.accept(this);
            }
        }
    }

    public void visit_AndExpr(UnitySyntaxTreeNode node) throws Exception
    {
        if (sourceTransformationDisabled) {
            visit(node);
            return;
        }

        if (!node.hasChildTerminal("'and'")) {
            visit(node);
            return;
        }

        int c = 0;

        for (UnitySyntaxTreeNode child : Lists.reverse(node.getChildren())) {
            if (child.getType() == Type.TERMINAL) {
                if ("'and'".equals(child.getName()))
                    out.print(prefix_operators + ":logical-and(");
                else
                    throw new Exception("Invalid operator: " + child.getName());
                c++;
            }
        }

        int i = 0;

        for (UnitySyntaxTreeNode child : node.getChildren()) {
            if (child.getType() == Type.TERMINAL) {
                ;
            } else if (child.getType() == Type.NONTERMINAL) {
                child.accept(this);
                if (i != 0)
                    out.print(")");
                if (i < c)
                    out.print(",");
                i++;
            } else {
                child.accept(this);
            }
        }
    }

    public void visit_ComparisonExpr(UnitySyntaxTreeNode node) throws Exception
    {
        if (sourceTransformationDisabled) {
            visit(node);
            return;
        }

        if (!node.hasChildNonterminal("GeneralComp") && !node.hasChildNonterminal("NodeComp") && !node.hasChildNonterminal("ValueComp")) {
            visit(node);
            return;
        }

        int c = 0;

        for (UnitySyntaxTreeNode child : Lists.reverse(node.getChildren())) {
            if ("GeneralComp".equals(child.getName()) || "NodeComp".equals(child.getName()) || "ValueComp".equals(child.getName())) {
                if ("eq".equals(child.toString()))
                    out.print(prefix_operators + ":value-equal(");
                else if ("=".equals(child.toString()))
                    out.print(prefix_operators + ":general-equal(");
                else if ("ne".equals(child.toString()))
                    out.print(prefix_operators + ":value-not-equal(");
                else if ("!=".equals(child.toString()))
                    out.print(prefix_operators + ":general-not-equal(");
                else if ("lt".equals(child.toString()))
                    out.print(prefix_operators + ":value-less(");
                else if ("<".equals(child.toString()))
                    out.print(prefix_operators + ":general-less(");
                else if ("gt".equals(child.toString()))
                    out.print(prefix_operators + ":value-greater(");
                else if (">".equals(child.toString()))
                    out.print(prefix_operators + ":general-greater(");
                else if ("le".equals(child.toString()))
                    out.print(prefix_operators + ":value-less-equal(");
                else if ("<=".equals(child.toString()))
                    out.print(prefix_operators + ":general-less-equal(");
                else if ("ge".equals(child.toString()))
                    out.print(prefix_operators + ":value-greater-equal(");
                else if (">=".equals(child.toString()))
                    out.print(prefix_operators + ":general-greater-equal(");
                else
                    throw new Exception("Invalid operator: " + child.toString());
                c++;
            }
        }

        int i = 0;

        for (UnitySyntaxTreeNode child : node.getChildren()) {
            if (child.getType() == Type.TERMINAL) {
                ;
            } else if ("GeneralComp".equals(child.getName()) || "NodeComp".equals(child.getName()) || "ValueComp".equals(child.getName())) {
                ;
            } else if (child.getType() == Type.NONTERMINAL) {
                child.accept(this);
                if (i != 0)
                    out.print(")");
                if (i < c)
                    out.print(",");
                i++;
            } else {
                child.accept(this);
            }
        }
    }

    public void visit_FunctionDecl(UnitySyntaxTreeNode node) throws Exception
    {
        if (sourceTransformationDisabled) {
            visit(node);
            return;
        }

        for (UnitySyntaxTreeNode child : node.getChildren()) {
            if ("ReturnType".equals(child.getName()))
                continue;
            child.accept(this);
        }
    }

    public void visit_FunctionName(UnitySyntaxTreeNode node) throws Exception
    {
        if (sourceTransformationDisabled || !(node.getParent().getName().equals("FunctionCall") || node.getParent().getName().equals("FunctionEQName"))) {
            visit(node);
            return;
        }

        for (UnitySyntaxTreeNode child : node.getChildren()) {
            if (child.getType() == Type.TERMINAL) {
                String name = getName(child.toString());
                String namespace = getNamespace(child.toString());
                if ("http://www.w3.org/2005/xpath-functions".equals(namespace)) {
                    if ("abs".equals(name))
                        out.print(prefix_operators + ":abs");
                    else if ("ceiling".equals(name))
                        out.print(prefix_operators + ":ceiling");
                    else if ("floor".equals(name))
                        out.print(prefix_operators + ":floor");
                    else if ("max".equals(name))
                        out.print(prefix_operators + ":max");
                    else if ("min".equals(name))
                        out.print(prefix_operators + ":min");
                    else if ("round".equals(name))
                        out.print(prefix_operators + ":round");
                    else if ("sum".equals(name))
                        out.print(prefix_operators + ":sum");
                    else if ("acos".equals(name))
                        out.print(prefix_operators + ":acos");
                    else if ("asin".equals(name))
                        out.print(prefix_operators + ":asin");
                    else if ("atan".equals(name))
                        out.print(prefix_operators + ":atan");
                    else if ("atan2".equals(name))
                        out.print(prefix_operators + ":atan2");
                    else if ("cos".equals(name))
                        out.print(prefix_operators + ":cos");
                    else if ("exp".equals(name))
                        out.print(prefix_operators + ":exp");
                    else if ("exp10".equals(name))
                        out.print(prefix_operators + ":exp10");
                    else if ("log".equals(name))
                        out.print(prefix_operators + ":log");
                    else if ("log10".equals(name))
                        out.print(prefix_operators + ":log10");
                    else if ("pow".equals(name))
                        out.print(prefix_operators + ":pow");
                    else if ("sin".equals(name))
                        out.print(prefix_operators + ":sin");
                    else if ("sqrt".equals(name))
                        out.print(prefix_operators + ":sqrt");
                    else if ("tan".equals(name))
                        out.print(prefix_operators + ":tan");
                    else
                        child.accept(this);
                } else if ("http://www.w3.org/2005/xpath-functions/math".equals(namespace)) {
                    if ("acos".equals(name))
                        out.print(prefix_operators + ":acos");
                    else if ("asin".equals(name))
                        out.print(prefix_operators + ":asin");
                    else if ("atan".equals(name))
                        out.print(prefix_operators + ":atan");
                    else if ("atan2".equals(name))
                        out.print(prefix_operators + ":atan2");
                    else if ("cos".equals(name))
                        out.print(prefix_operators + ":cos");
                    else if ("exp".equals(name))
                        out.print(prefix_operators + ":exp");
                    else if ("exp10".equals(name))
                        out.print(prefix_operators + ":exp10");
                    else if ("log".equals(name))
                        out.print(prefix_operators + ":log");
                    else if ("log10".equals(name))
                        out.print(prefix_operators + ":log10");
                    else if ("pow".equals(name))
                        out.print(prefix_operators + ":pow");
                    else if ("sin".equals(name))
                        out.print(prefix_operators + ":sin");
                    else if ("sqrt".equals(name))
                        out.print(prefix_operators + ":sqrt");
                    else if ("tan".equals(name))
                        out.print(prefix_operators + ":tan");
                    else
                        child.accept(this);
                } else {
                    child.accept(this);
                }
            } else
                child.accept(this);
        }
    }

    public void visit_IfExpr(UnitySyntaxTreeNode node) throws Exception
    {
        if (sourceTransformationDisabled) {
            visit(node);
            return;
        }
        if (node.hasChildTerminal("'if'"))
            out.print(prefix_operators + ":if-else(");
        else {
            visit(node);
            return;
        }
        for (UnitySyntaxTreeNode child : node.getChildren()) {
            if (child.getType() == Type.TERMINAL) {
                if (child.getName().equals("'then'") || child.getName().equals("'else'"))
                    out.print(",");
            } else if (child.getType() != Type.WHITESPACE) {
                child.accept(this);
            }
        }
        out.print(")");
    }

    public void visit_ModuleDecl(UnitySyntaxTreeNode node) throws Exception
    {
        String prefix = null;
        String namespace = null;
        for (UnitySyntaxTreeNode child : node.getChildren()) {
            if (child.getType() == Type.NONTERMINAL && "NCName".equals(child.getName()))
                prefix = child.toString();
            if (child.getType() == Type.TERMINAL && "URILiteral".equals(child.getName()))
                namespace = child.toString().substring(1, child.toString().length() - 1);
            child.accept(this);
        }

        if (operatorsNamespace.equals(namespace))
            this.prefix_operators = prefix;
        if (reflectionNamespace.equals(namespace))
            this.prefix_reflection = prefix;
    }

    public void visit_ModuleImport(UnitySyntaxTreeNode node) throws Exception
    {
        String prefix = null;
        String namespace = null;
        for (UnitySyntaxTreeNode child : node.getChildren()) {
            if ("NCName".equals(child.getName()))
                prefix = child.toString();
            else if ("URILiteral".equals(child.getName()))
                namespace = child.toString().substring(1, child.getEnd() - child.getStart() - 1);
            child.accept(this);
        }

        prefixes.put(prefix, namespace);
        if (operatorsNamespace.equals(namespace))
            this.prefix_operators = prefix;
        if (reflectionNamespace.equals(namespace))
            this.prefix_reflection = prefix;
    }

    public void visit_MultiplicativeExpr(UnitySyntaxTreeNode node) throws Exception
    {
        if (sourceTransformationDisabled) {
            visit(node);
            return;
        }

        if (!node.hasChildTerminal("'*'") && !node.hasChildTerminal("'div'") && !node.hasChildTerminal("'idiv'") && !node.hasChildTerminal("'mod'")) {
            visit(node);
            return;
        }

        int c = 0;

        for (UnitySyntaxTreeNode child : Lists.reverse(node.getChildren())) {
            if (child.getType() == Type.TERMINAL) {
                if ("'*'".equals(child.getName()))
                    out.print(prefix_operators + ":multiply(");
                else if ("'div'".equals(child.getName()))
                    out.print(prefix_operators + ":divide(");
                else if ("'idiv'".equals(child.getName()))
                    out.print(prefix_operators + ":integer-divide(");
                else if ("'mod'".equals(child.getName()))
                    out.print(prefix_operators + ":mod(");
                else
                    throw new Exception("Invalid operator: " + child.getName());
                c++;
            }
        }

        int i = 0;

        for (UnitySyntaxTreeNode child : node.getChildren()) {
            if (child.getType() == Type.TERMINAL) {
                ;
            } else if (child.getType() == Type.NONTERMINAL) {
                child.accept(this);
                if (i != 0)
                    out.print(")");
                if (i < c)
                    out.print(",");
                i++;
            } else {
                child.accept(this);
            }
        }
    }

    public void visit_NamespaceDecl(UnitySyntaxTreeNode node) throws Exception
    {
        String prefix = null;
        String namespace = null;
        for (UnitySyntaxTreeNode child : node.getChildren()) {
            if (child.getType() == Type.TERMINAL) {
                if (!child.getName().equals("'declare'") && !child.getName().equals("'namespace'") && !child.getName().equals("'='"))
                    namespace = child.toString().substring(1, child.getEnd() - child.getStart() - 1);
                child.accept(this);
            } else {
                if (child.getType() == Type.NONTERMINAL)
                    prefix = child.toString();
                child.accept(this);
            }
        }

        prefixes.put(prefix, namespace);
    }

    public void visit_NotExpr(UnitySyntaxTreeNode node) throws Exception
    {
        if (sourceTransformationDisabled) {
            visit(node);
            return;
        }
        if (node.hasChildTerminal("'not'"))
            out.print(prefix_operators + ":logical-not(");
        else {
            visit(node);
            return;
        }
        for (UnitySyntaxTreeNode child : node.getChildren()) {
            if (child.getType() != Type.TERMINAL)
                child.accept(this);
        }
        out.print(")");
    }

    public void visit_OptionDecl(UnitySyntaxTreeNode node) throws Exception
    {
        if (node.toString().contains("source-transformation") && node.toString().contains("disabled"))
            sourceTransformationDisabled = true;
        visit(node);
        return;
    }

    public void visit_OrExpr(UnitySyntaxTreeNode node) throws Exception
    {
        if (sourceTransformationDisabled) {
            visit(node);
            return;
        }

        if (!node.hasChildTerminal("'or'")) {
            visit(node);
            return;
        }

        int c = 0;

        for (UnitySyntaxTreeNode child : Lists.reverse(node.getChildren())) {
            if (child.getType() == Type.TERMINAL) {
                if ("'or'".equals(child.getName()))
                    out.print(prefix_operators + ":logical-or(");
                else
                    throw new Exception("Invalid operator: " + child.getName());
                c++;
            }
        }

        int i = 0;

        for (UnitySyntaxTreeNode child : node.getChildren()) {
            if (child.getType() == Type.TERMINAL) {
                ;
            } else if (child.getType() == Type.NONTERMINAL) {
                child.accept(this);
                if (i != 0)
                    out.print(")");
                if (i < c)
                    out.print(",");
                i++;
            } else {
                child.accept(this);
            }
        }
    }

    public void visit_Prolog(UnitySyntaxTreeNode node) throws Exception
    {
        boolean directives = true;
        for (UnitySyntaxTreeNode child : node.getChildren()) {
            switch (child.getName()) {
            case "AnnotatedDecl":
            case "ContextItemDecl":
            case "FunctionDecl":
            case "OptionDecl":
            case "VarDecl":
                if (directives) {
                    directives = false;
                    addNamespacePrefixes();
                }
            }

            child.accept(this);
        }

        if (directives)
            addNamespacePrefixes();
    }

    public void visit_QuantifiedExpr(UnitySyntaxTreeNode node) throws Exception
    {
        if (sourceTransformationDisabled) {
            visit(node);
            return;
        }
        if (node.hasChildTerminal("'some'"))
            out.print(prefix_operators + ":some(array{for");
        else if (node.hasChildTerminal("'every'"))
            out.print(prefix_operators + ":every(array{for");
        else {
            visit(node);
            return;
        }
        for (UnitySyntaxTreeNode child : node.getChildren()) {
            if (child.getType() == Type.TERMINAL) {
                if (child.getName().equals("'every'"))
                    ;
                else if (child.getName().equals("'some'"))
                    ;
                else if (child.getName().equals("'satisfies'"))
                    out.print("return");
                else
                    child.accept(this);
            } else
                child.accept(this);
        }
        out.print("})");
    }

    public void visit_QueryBody(UnitySyntaxTreeNode node) throws Exception
    {
        out.print(prefix_operators + ":serialize((");
        for (UnitySyntaxTreeNode child : node.getChildren())
            child.accept(this);
        out.print("), " + prefix_operators + ":serialization-mode())");

    }

    public void visit_UnaryExpr(UnitySyntaxTreeNode node) throws Exception
    {
        if (sourceTransformationDisabled) {
            visit(node);
            return;
        }

        if (!node.hasChildTerminal("'-'") && !node.hasChildTerminal("'+'")) {
            visit(node);
            return;
        }

        for (UnitySyntaxTreeNode child : node.getChildren()) {
            if (child.getType() == Type.TERMINAL) {
                if ("'+'".equals(child.getName()))
                    out.print(prefix_operators + ":unary-plus(");
                else if ("'-'".equals(child.getName()))
                    out.print(prefix_operators + ":unary-minus(");
                else
                    throw new Exception("Invalid operator: " + child.getName());
            }
        }

        for (UnitySyntaxTreeNode child : node.getChildren()) {
            if (child.getType() == Type.TERMINAL) {
                ;
            } else if (child.getType() == Type.NONTERMINAL) {
                child.accept(this);
                out.print(")");
            } else {
                child.accept(this);
            }
        }
    }

    public void visitTerminal(UnitySyntaxTreeNode node) throws Exception
    {
        out.print(node.toString());
    }

    public void visitWhiteSpace(UnitySyntaxTreeNode node) throws Exception
    {
        out.print(node.toString());
    }
}
