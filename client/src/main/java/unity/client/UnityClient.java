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

package unity.client;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

public class UnityClient
{
    private final String      accessToken;
    private final HttpHeaders headers;
    private final URI         host;

    public UnityClient(URI host, String accessToken)
    {
        this.host = host;
        this.accessToken = accessToken;
        headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
    }

    public String getAccessToken()
    {
        return accessToken;
    }

    public URI getHost()
    {
        return host;
    }

    private static final RestTemplate rest = new RestTemplate();

    public static UnityClient login(URI host, String username, String password)
    {
        Map<String, String> request = new HashMap<String, String>();
        request.put("username", username);
        request.put("password", password);
        return new UnityClient(host, rest.postForObject(host.resolve("/api/v1/session"), request, String.class));
    }
}
