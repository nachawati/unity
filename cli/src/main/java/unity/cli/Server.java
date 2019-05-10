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

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import unity.kernel.engine.UnityAnalyticsEngineManager;

@Command(name = "start", description = "Start server")
public class Server extends Main.Command
{
    @Option(names = { "--gitlab-url" }, description = "The GitLab URL to use for Unity DGMS", required = false)
    private String gitLabUrl = "https://git.dgms.io/";

    @Override
    public void exec() throws Exception
    {
        Main.printBanner();
        System.out.print("Starting Unity DGMS... ");
        String version = Main.getVersionString();
        ProcessBuilder processBuilder = new ProcessBuilder("java", "-jar",
                UnityAnalyticsEngineManager.getInstallPath().resolve("lib/unity-server-" + version + ".jar").toAbsolutePath().toString());
        if (gitLabUrl != null)
            processBuilder.environment().put("DGMS_GITLAB_URL", gitLabUrl);
        processBuilder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectError(ProcessBuilder.Redirect.INHERIT);
        processBuilder.redirectInput(ProcessBuilder.Redirect.INHERIT);
        Process p = processBuilder.start();
        try {
            p.waitFor();
        } catch (final InterruptedException e) {
        }
    }
}
