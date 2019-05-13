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

import picocli.CommandLine.Command;
import picocli.CommandLine.Help;
import picocli.CommandLine.Parameters;
import unity.client.UnityClient;
import unity.client.UnityRepository;

@Command(
        name = "repository",
        aliases = "repo",
        description = "Manage repositories",
        subcommands = { Repository.Add.class, Repository.Get.class, Repository.Listing.class, Repository.Remove.class })
public class Repository extends Main.Command
{
    @Command(name = "add", description = "Add new repository")
    public static class Add extends Main.Command
    {
        @Parameters(description = "The repository name", arity = "1", showDefaultValue = Help.Visibility.NEVER)
        private String name;

        @Override
        public void exec(UnityClient client) throws IOException
        {
            if (client == null)
                throw new RuntimeException("you are not logged into Unity DGMS");
            UnityRepository repository = client.addRepository(name);
            System.out.println("Repository: " + repository.getName());
            System.out.println();
            System.out.println(repository);
            System.out.println();
        }
    }

    @Command(name = "get", description = "Show repository information")
    public static class Get extends Main.Command
    {
        @Parameters(description = "The repository identifier", arity = "1", showDefaultValue = Help.Visibility.NEVER)
        private Integer repositoryId;

        @Override
        public void exec(UnityClient client) throws IOException
        {
            if (client == null)
                throw new RuntimeException("you are not logged into Unity DGMS");
            UnityRepository repository = client.getRepository(repositoryId);
            System.out.println("Repository: " + repository.getName());
            System.out.println();
            System.out.println(repository);
            System.out.println();
        }
    }

    @Command(name = "list", aliases = "ls", description = "List repositories")
    public static class Listing extends Main.Command
    {
        @Override
        public void exec(UnityClient client) throws IOException
        {
            if (client == null)
                throw new RuntimeException("you are not logged into Unity DGMS");
            System.out.println("Repositories:");
            System.out.println();
            printTable(client.listRepositories().stream()
                    .map(repository -> new Object[] { repository.getId(), repository.getName(), repository.getPath(), repository.getVisibility() })
                    .collect(Collectors.toList()));
            System.out.println();
        }
    }

    @Command(name = "remove", aliases = { "rm" }, description = "Remove repository")
    public static class Remove extends Main.Command
    {
        @Parameters(description = "The repository identifier", arity = "1", showDefaultValue = Help.Visibility.NEVER)
        private Integer repositoryId;

        @Override
        public void exec(UnityClient client) throws IOException
        {
            if (client == null)
                throw new RuntimeException("you are not logged into Unity DGMS");
            UnityRepository repository = client.getRepository(repositoryId);
            System.out.print("Removing repository " + repository.getName() + "... ");
            client.removeRepository(repositoryId);
            System.out.println("SUCCESS");
            System.out.println();
        }
    }
}
