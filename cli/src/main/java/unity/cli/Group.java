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

import java.io.IOException;
import java.util.stream.Collectors;

import unity.client.UnityClient;
import unity.client.UnityGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Help;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "group", description = "Manage groups", subcommands = { Group.Add.class, Group.Get.class, Group.Listing.class, Group.Remove.class })
public class Group extends Main.Command
{
    @Command(name = "add", description = "Add new group")
    public static class Add extends Main.Command
    {
        @Parameters(description = "The group name", arity = "1", showDefaultValue = Help.Visibility.NEVER)
        private String name;

        @Option(names = { "--path" }, description = "The group path", required = true)
        private String path;

        @Override
        public void exec(UnityClient client) throws IOException
        {
            if (client == null)
                throw new RuntimeException("you are not logged into Unity DGMS");
            UnityGroup group = client.addGroup(name, path);
            System.out.println("Group: " + group.getName());
            System.out.println();
            System.out.println(group);
            System.out.println();
        }
    }

    @Command(name = "get", description = "Show group information")
    public static class Get extends Main.Command
    {
        @Parameters(description = "The group identifier", arity = "1", showDefaultValue = Help.Visibility.NEVER)
        private Integer groupId;

        @Override
        public void exec(UnityClient client) throws IOException
        {
            if (client == null)
                throw new RuntimeException("you are not logged into Unity DGMS");
            UnityGroup group = client.getGroup(groupId);
            System.out.println("Group: " + group.getName());
            System.out.println();
            System.out.println(group);
            System.out.println();
        }
    }

    @Command(name = "list", aliases = "ls", description = "List groups")
    public static class Listing extends Main.Command
    {
        @Override
        public void exec(UnityClient client) throws IOException
        {
            if (client == null)
                throw new RuntimeException("you are not logged into Unity DGMS");
            System.out.println("Groups:");
            System.out.println();
            printTable(client.listGroups().stream().map(group -> new Object[] { group.getId(), group.getName(), group.getFullPath(), group.getVisibility() })
                    .collect(Collectors.toList()));
            System.out.println();
        }
    }

    @Command(name = "remove", aliases = { "rm" }, description = "Remove group")
    public static class Remove extends Main.Command
    {
        @Parameters(description = "The group identifier", arity = "1", showDefaultValue = Help.Visibility.NEVER)
        private Integer groupId;

        @Override
        public void exec(UnityClient client) throws IOException
        {
            if (client == null)
                throw new RuntimeException("you are not logged into Unity DGMS");
            UnityGroup group = client.getGroup(groupId);
            System.out.print("Removing group " + group.getName() + "... ");
            client.removeGroup(groupId);
            System.out.println("SUCCESS");
            System.out.println();
        }
    }
}
