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

package unity.kernel.engine.zorba;

import java.io.Writer;
import java.util.List;

import unity.kernel.language.UnitySyntaxTreeNode;
import unity.kernel.language.UnitySyntaxTreeNode.Type;
import unity.kernel.language.jsoniq.JSONiq10Parser;
import unity.kernel.language.jsoniq.JSONiqSyntaxTreeBuilder;

public class UnityZorbaJSONiqTransformer extends UnityZorbaXQueryTransformer
{
    protected JSONiq10Parser          jsoniq10;
    protected JSONiqSyntaxTreeBuilder treeBuilder = new JSONiqSyntaxTreeBuilder();

    public UnityZorbaJSONiqTransformer(String operatorsNamespace, String reflectionNamespace, CharSequence string, Writer writer, String language)
    {
        super(operatorsNamespace, reflectionNamespace, string, writer, language);
        jsoniq10 = new JSONiq10Parser(string, treeBuilder);
        prefixes.put("jn", "http://jsoniq.org/functions");
        prefixes.put("libjn", "http://jsoniq.org/function-library");
    }

    @Override
    public void compile() throws Exception
    {
        try {
            jsoniq10.parse_XQuery();
            treeBuilder.getNode().accept(this);
            out.flush();
        } catch (JSONiq10Parser.ParseException e) {
            throw new Exception(jsoniq10.getErrorMessage(e));
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void visit_InstanceofExpr(UnitySyntaxTreeNode node) throws Exception
    {
        if (sourceTransformationDisabled) {
            visit(node);
            return;
        }
        if (node.hasChildTerminal("'instance'"))
            out.print(" " + prefix_operators + ":get-instance(");
        else {
            visit(node);
            return;
        }
        for (UnitySyntaxTreeNode child : node.getChildren()) {
            if (child.getType() == Type.TERMINAL && child.getName().equals("'instance'"))
                out.print(") ");
            child.accept(this);
        }
    }

    public void visit_Program(UnitySyntaxTreeNode node) throws Exception
    {
        for (UnitySyntaxTreeNode child : node.getChildren()) {
            for (UnitySyntaxTreeNode grandChild : child.getChildren()) {
                if ("Expr".equals(grandChild.getName())) {
                    out.print(" " + prefix_operators + ":serialize((");
                    grandChild.accept(this);
                    out.print("), " + " " + prefix_operators + ":serialization-mode())");
                } else {
                    grandChild.accept(this);
                }
            }
        }
    }

    private void PostfixExpr(List<UnitySyntaxTreeNode> children, int i) throws Exception
    {
        UnitySyntaxTreeNode child = children.get(i);
        switch (child.getName()) {
        case "ArgumentList":
            out.print(prefix_reflection + ":invoke-variadic(");
            PostfixExpr(children, i - 1);
            if (child.getChildren().size() > 2)
                out.print(",");
            for (int j = 1; j < child.getChildren().size() - 1; j++)
                child.getChildren().get(j).accept(this);
            out.print(")");
            break;
        case "ObjectLookup":
            out.print(" " + prefix_operators + ":object-lookup(");
            PostfixExpr(children, i - 1);
            out.print(",");
            for (int j = 1; j < child.getChildren().size(); j++)
                if ("NCName".equals(child.getChildren().get(j).getName()))
                    out.print("\"" + child.getChildren().get(j) + "\"");
                else
                    child.getChildren().get(j).accept(this);
            out.print(")");
            break;
        case "ArrayUnboxing":
            out.print(" " + prefix_operators + ":array-unboxing(");
            PostfixExpr(children, i - 1);
            out.print(")");
            break;
        default:
            if (i >= 1)
                PostfixExpr(children, i - 1);
            child.accept(this);
        }
    }

    public void visit_PostfixExpr(UnitySyntaxTreeNode node) throws Exception
    {
        if (sourceTransformationDisabled || node.getChildren().size() < 2) {
            visit(node);
            return;
        }

        switch (node.getParent().getName()) {
        case "JSONDeleteExpr":
        case "JSONInsertExpr":
        case "JSONRenameExpr":
        case "JSONReplaceExpr":
        case "JSONAppendExpr":
            visit(node);
            break;
        default:
            PostfixExpr(node.getChildren(), node.getChildren().size() - 1);
        }
    }

    @Override
    public void visit_QuantifiedExpr(UnitySyntaxTreeNode node) throws Exception
    {
        if (sourceTransformationDisabled) {
            visit(node);
            return;
        }
        if (node.hasChildTerminal("'some'"))
            out.print(" " + prefix_operators + ":some([for");
        else if (node.hasChildTerminal("'every'"))
            out.print(" " + prefix_operators + ":every([for");
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
        out.print("])");
    }
}
