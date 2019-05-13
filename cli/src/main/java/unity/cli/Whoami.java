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

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;

import picocli.CommandLine.Command;
import unity.client.UnityClient;
import unity.client.UnityUser;

@Command(name = "whoami", description = "Show session information")
public class Whoami extends Main.Command
{
    @Override
    public void exec(UnityClient client)
    {
        if (client == null)
            throw new RuntimeException("you are not logged in");
        UnityUser user = client.getCurrentUser();
        Object username = Ansi.ansi().fgBright(Color.GREEN).a(user.getUsername()).reset();
        System.out.println("Welcome to Unity DGMS: " + Ansi.ansi().fgBright(Color.CYAN).a(client.getHost()).reset());
        System.out.println();
        System.out.println("You are logged in as " + username + ": " + user.getName() + " (" + user.getEmail() + ")");
        System.out.println();
    }
}
