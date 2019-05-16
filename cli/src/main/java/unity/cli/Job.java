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

import picocli.CommandLine.Command;
import picocli.CommandLine.Help;
import picocli.CommandLine.Parameters;
import unity.client.UnityClient;

@Command(name = "job", description = "Manage jobs", subcommands = { Job.Get.class, Job.Kill.class, Job.Listing.class, Job.Remove.class, Job.Submit.class })
public class Job extends Main.Command
{
    @Command(name = "submit", description = "Submit job for remote execution")
    public static class Submit extends Main.Command
    {
        @Override
        public void exec(UnityClient client) throws IOException
        {
            if (client == null)
                throw new RuntimeException("you are not logged into Unity DGMS");
        }
    }

    @Command(name = "get", description = "Get job result")
    public static class Get extends Main.Command
    {
        @Parameters(description = "The job identifier", arity = "1", showDefaultValue = Help.Visibility.NEVER)
        private Integer jobId;

        @Override
        public void exec(UnityClient client) throws IOException
        {
            if (client == null)
                throw new RuntimeException("you are not logged into Unity DGMS");
            // UnityJob job = client.getJob(jobId);
            // System.out.println("Job: " + job.getName());
            // System.out.println(job);
            System.out.println();
        }
    }

    @Command(name = "list", aliases = "ls", description = "List jobs")
    public static class Listing extends Main.Command
    {
        @Override
        public void exec(UnityClient client) throws IOException
        {
            if (client == null)
                throw new RuntimeException("you are not logged into Unity DGMS");
            System.out.println("Jobs:");
            System.out.println();
            // printTable(client.listJobs().stream().map(job -> new Object[] { job.getId(),
            // job.getName(), job.getStatus() }).collect(Collectors.toList()));
            System.out.println();
        }
    }

    @Command(name = "kill", aliases = {}, description = "Kill job")
    public static class Kill extends Main.Command
    {
        @Parameters(description = "The job identifier", arity = "1", showDefaultValue = Help.Visibility.NEVER)
        private Integer jobId;

        @Override
        public void exec(UnityClient client) throws IOException
        {
            if (client == null)
                throw new RuntimeException("you are not logged into Unity DGMS");
            // UnityJob job = client.getJob(jobId);
            // System.out.print("Killing job " + job.getName() + "... ");
            // client.killJob(jobId);
            System.out.println("SUCCESS");
            System.out.println();
        }
    }

    @Command(name = "remove", aliases = { "rm" }, description = "Remove job")
    public static class Remove extends Main.Command
    {
        @Parameters(description = "The job identifier", arity = "1", showDefaultValue = Help.Visibility.NEVER)
        private Integer jobId;

        @Override
        public void exec(UnityClient client) throws IOException
        {
            if (client == null)
                throw new RuntimeException("you are not logged into Unity DGMS");
            // UnityJob job = client.getJob(jobId);
            // System.out.print("Removing job " + job.getName() + "... ");
            // client.removeJob(jobId);
            System.out.println("SUCCESS");
            System.out.println();
        }
    }
}
