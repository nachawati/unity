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

package unity.kernel.language;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

public final class UnitySyntaxTreeNode
{
    private final List<UnitySyntaxTreeNode> children     = new LinkedList<UnitySyntaxTreeNode>();
    private int                             end;
    private final String                    name;
    private final Set<String>               nonterminals = new HashSet<String>();
    private final UnitySyntaxTreeNode       parent;
    private int                             start;
    private final CharSequence              string;
    private final Set<String>               terminals    = new HashSet<String>();
    private final Type                      type;

    public UnitySyntaxTreeNode(UnitySyntaxTreeNode parent, CharSequence string, String name, Type type, int start)
    {
        this(parent, string, name, type, start, 0);
    }

    public UnitySyntaxTreeNode(UnitySyntaxTreeNode parent, CharSequence string, String name, Type type, int start, int end)
    {
        this.parent = parent;
        this.string = string;
        this.name = name;
        this.type = type;
        this.start = start;
        this.end = end;
        if (parent != null) {
            parent.children.add(this);
            if (type == Type.TERMINAL)
                parent.terminals.add(name);
            if (type == Type.NONTERMINAL)
                parent.nonterminals.add(name);
        }
    }

    public void accept(Object visitor) throws Exception
    {
        try {
            if (type == Type.TERMINAL)
                visitor.getClass().getMethod("visitTerminal", UnitySyntaxTreeNode.class).invoke(visitor, this);
            else if (type == Type.WHITESPACE)
                visitor.getClass().getMethod("visitWhiteSpace", UnitySyntaxTreeNode.class).invoke(visitor, this);
            else
                visitor.getClass().getMethod("visit_" + name, UnitySyntaxTreeNode.class).invoke(visitor, this);
        } catch (NoSuchMethodException e) {
            visitor.getClass().getMethod("visit", UnitySyntaxTreeNode.class).invoke(visitor, this);
        }
    }

    public String dump()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(name + "\n");
        for (UnitySyntaxTreeNode node : children)
            sb.append(node.dump() + "\n");
        return sb.toString();
    }

    public List<UnitySyntaxTreeNode> getChildren()
    {
        return children;
    }

    public int getEnd()
    {
        return end;
    }

    public String getName()
    {
        return name;
    }

    public UnitySyntaxTreeNode getParent()
    {
        return parent;
    }

    public int getStart()
    {
        return start;
    }

    public Stream<UnitySyntaxTreeNode> getTerminals()
    {
        return children.stream().flatMap(n -> n.getType() == Type.TERMINAL ? Stream.of(n) : Stream.empty());
    }

    public Type getType()
    {
        return type;
    }

    public boolean hasChildNonterminal(String nonterminal)
    {
        return nonterminals.contains(nonterminal);
    }

    public boolean hasChildTerminal(String terminal)
    {
        return terminals.contains(terminal);
    }

    public void setEnd(int end)
    {
        this.end = end;
    }

    public void setStart(int start)
    {
        this.start = start;
    }

    @Override
    public String toString()
    {
        return string.subSequence(start, end).toString();
    }

    public static enum Type
    {
        NONTERMINAL, TERMINAL, WHITESPACE
    }
}
