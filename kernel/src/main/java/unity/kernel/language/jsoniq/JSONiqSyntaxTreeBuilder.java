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

package unity.kernel.language.jsoniq;

import unity.kernel.language.UnitySyntaxTreeNode;

public class JSONiqSyntaxTreeBuilder implements JSONiq10Parser.EventHandler
{
    private UnitySyntaxTreeNode currentNode;
    private CharSequence        string;

    public JSONiqSyntaxTreeBuilder()
    {
    }

    @Override
    public void endNonterminal(String name, int end)
    {
        currentNode.setEnd(end);
        if (currentNode.getParent() != null)
            currentNode = currentNode.getParent();
    }

    public UnitySyntaxTreeNode getNode()
    {
        return currentNode;
    }

    @Override
    public void reset(CharSequence string)
    {
        this.string = string;
    }

    @Override
    public void startNonterminal(String name, int start)
    {
        currentNode = new UnitySyntaxTreeNode(currentNode, string, name, UnitySyntaxTreeNode.Type.NONTERMINAL, start);
    }

    @Override
    public void terminal(String name, int begin, int end)
    {
        new UnitySyntaxTreeNode(currentNode, string, name, UnitySyntaxTreeNode.Type.TERMINAL, begin, end);
    }

    @Override
    public void whitespace(int begin, int end)
    {
        new UnitySyntaxTreeNode(currentNode, string, "", UnitySyntaxTreeNode.Type.WHITESPACE, begin, end);
    }
}
