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
import java.net.URI;
import java.nio.file.Files;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonWriter;

import picocli.CommandLine.Command;
import picocli.CommandLine.Help;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import unity.client.UnityClient;
import unity.client.UnityUser;
import unity.kernel.engine.UnityAnalyticsContext;

@Command(name = "login", description = "Log into Unity DGMS")
public class Login extends Main.Command
{
    @Option(names = { "--host" }, description = "Connect to Unity DGMS on given host", required = false)
    private String host = "https://dgms.io/";

    @Parameters(description = "The username to use when connecting to Unity DGMS", arity = "1", showDefaultValue = Help.Visibility.NEVER)
    private String username;

    @Override
    public void exec(UnityClient client) throws Exception
    {
        System.out.print(Ansi.ansi().fgBright(Color.CYAN).a("Password: ").reset());
        System.out.flush();
        String password = new String(System.console().readPassword());
        System.out.print("Logging into Unity DGMS... ");
        client = UnityClient.login(URI.create(host), username, password);
        Gson gson = new Gson();
        try (JsonWriter writer = gson.newJsonWriter(Files.newBufferedWriter(UnityAnalyticsContext.getSettingsPath().resolve("session.json")))) {
            JsonObject sessionProperties = new JsonObject();
            sessionProperties.addProperty("host", host);
            sessionProperties.addProperty("access-token", client.getAccessToken());
            gson.toJson(sessionProperties, writer);
        } catch (final IOException e) {
            throw new Exception("unable to persist session");
        }
        UnityUser user = client.getCurrentUser();
        Object username = Ansi.ansi().fgBright(Color.GREEN).a(user.getUsername()).reset();
        System.out.println(Ansi.ansi().fgBright(Color.GREEN).a("SUCCESS").reset());
        System.out.println();
        System.out.println("Welcome to Unity DGMS: " + Ansi.ansi().fgBright(Color.CYAN).a(host).reset());
        System.out.println();
        System.out.println("You are logged in as " + username + ": " + user.getName() + " (" + user.getEmail() + ")");
        System.out.println();
    }
}
