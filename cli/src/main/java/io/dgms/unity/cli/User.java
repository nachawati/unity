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
import java.util.stream.Collectors;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;

import io.dgms.unity.client.UnityClient;
import io.dgms.unity.client.UnityUser;
import picocli.CommandLine.Command;
import picocli.CommandLine.Help;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(name = "user", description = "Manage users", subcommands = { User.Add.class, User.Get.class, User.Listing.class, User.Remove.class })
public class User extends Main.Command
{
    @Command(name = "add", description = "Add new user")
    public static class Add extends Main.Command
    {
        @Option(names = { "--email" }, description = "The user email", required = true)
        private String email;

        @Parameters(description = "The user name", arity = "1", showDefaultValue = Help.Visibility.NEVER)
        private String name;

        @Override
        public void exec(UnityClient client) throws IOException
        {
            if (client == null)
                throw new RuntimeException("you are not logged into Unity DGMS");
            System.out.print(Ansi.ansi().fgBright(Color.CYAN).a("Password: ").reset());
            System.out.flush();
            String password = new String(System.console().readPassword());
            UnityUser user = client.addUser(name, email, password);
            System.out.println("User: " + user.getName());
            System.out.println();
            System.out.println(user);
            System.out.println();
        }
    }

    @Command(name = "get", description = "Show user information")
    public static class Get extends Main.Command
    {
        @Parameters(description = "The user identifier", arity = "1", showDefaultValue = Help.Visibility.NEVER)
        private Integer userId;

        @Override
        public void exec(UnityClient client) throws IOException
        {
            if (client == null)
                throw new RuntimeException("you are not logged into Unity DGMS");
            UnityUser user = client.getUser(userId);
            System.out.println("User: " + user.getName());
            System.out.println();
            System.out.println(user);
            System.out.println();
        }
    }

    @Command(name = "list", aliases = "ls", description = "List users")
    public static class Listing extends Main.Command
    {
        @Override
        public void exec(UnityClient client) throws IOException
        {
            if (client == null)
                throw new RuntimeException("you are not logged into Unity DGMS");
            System.out.println("Users:");
            System.out.println();
            printTable(client.listUsers().stream()
                    .map(user -> new Object[] { user.getId(), user.getName(), user.getEmail(), user.getAdministrator() ? "ADMIN" : "USER" })
                    .collect(Collectors.toList()));
            System.out.println();
        }
    }

    @Command(name = "remove", aliases = { "rm" }, description = "Remove user")
    public static class Remove extends Main.Command
    {
        @Parameters(description = "The user identifier", arity = "1", showDefaultValue = Help.Visibility.NEVER)
        private Integer userId;

        @Override
        public void exec(UnityClient client) throws IOException
        {
            if (client == null)
                throw new RuntimeException("you are not logged into Unity DGMS");
            UnityUser user = client.getUser(userId);
            System.out.print("Removing user " + user.getName() + "... ");
            client.removeUser(userId);
            System.out.println("SUCCESS");
            System.out.println();
        }
    }
}
