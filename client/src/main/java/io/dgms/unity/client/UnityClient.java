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

package io.dgms.unity.client;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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

    public UnityGroup addGroup(String name, String path)
    {
        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("name", name);
        requestBody.put("path", path);
        HttpEntity<Object> entity = new HttpEntity<Object>(requestBody, headers);
        ResponseEntity<UnityGroup> response = rest.exchange(host.resolve("/api/v1/groups"), HttpMethod.POST, entity, UnityGroup.class);
        return response.getBody();
    }

    public UnityRepository addRepository(String name)
    {
        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("name", name);
        HttpEntity<Object> entity = new HttpEntity<Object>(requestBody, headers);
        ResponseEntity<UnityRepository> response = rest.exchange(host.resolve("/api/v1/repositories"), HttpMethod.POST, entity, UnityRepository.class);
        return response.getBody();
    }

    public UnityUser addUser(String name, String email, String password)
    {
        Map<String, String> requestBody = new HashMap<String, String>();
        requestBody.put("name", name);
        requestBody.put("email", email);
        requestBody.put("password", password);
        HttpEntity<Object> entity = new HttpEntity<Object>(requestBody, headers);
        ResponseEntity<UnityUser> response = rest.exchange(host.resolve("/api/v1/users"), HttpMethod.POST, entity, UnityUser.class);
        return response.getBody();
    }

    public String getAccessToken()
    {
        return accessToken;
    }

    public UnityUser getCurrentUser()
    {
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);
        ResponseEntity<UnityUser> response = rest.exchange(host.resolve("/api/v1/session"), HttpMethod.GET, entity, UnityUser.class);
        return response.getBody();
    }

    public UnityGroup getGroup(Integer groupId)
    {
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);
        ResponseEntity<UnityGroup> response = rest.exchange(host.resolve("/api/v1/groups/").resolve(groupId.toString()), HttpMethod.GET, entity,
                UnityGroup.class);
        return response.getBody();
    }

    public URI getHost()
    {
        return host;
    }

    public UnityRepository getRepository(Integer repositoryId)
    {
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);
        ResponseEntity<UnityRepository> response = rest.exchange(host.resolve("/api/v1/repositories/").resolve(repositoryId.toString()), HttpMethod.GET, entity,
                UnityRepository.class);
        return response.getBody();
    }

    public UnityUser getUser(Integer userId)
    {
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);
        ResponseEntity<UnityUser> response = rest.exchange(host.resolve("/api/v1/users/").resolve(userId.toString()), HttpMethod.GET, entity, UnityUser.class);
        return response.getBody();
    }

    public List<UnityGroup> listGroups()
    {
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);
        ResponseEntity<List<UnityGroup>> response = rest.exchange(host.resolve("/api/v1/groups"), HttpMethod.GET, entity,
                new ParameterizedTypeReference<List<UnityGroup>>()
                {
                });
        return response.getBody();
    }

    public List<UnityRepository> listRepositories()
    {
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);
        ResponseEntity<List<UnityRepository>> response = rest.exchange(host.resolve("/api/v1/repositories"), HttpMethod.GET, entity,
                new ParameterizedTypeReference<List<UnityRepository>>()
                {
                });
        return response.getBody();
    }

    public List<UnityUser> listUsers()
    {
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);
        ResponseEntity<List<UnityUser>> response = rest.exchange(host.resolve("/api/v1/users"), HttpMethod.GET, entity,
                new ParameterizedTypeReference<List<UnityUser>>()
                {
                });
        return response.getBody();
    }

    public void removeGroup(Integer groupId)
    {
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);
        rest.exchange(host.resolve("/api/v1/groups/").resolve(groupId.toString()), HttpMethod.DELETE, entity, String.class);
    }

    public void removeRepository(Integer repositoryId)
    {
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);
        rest.exchange(host.resolve("/api/v1/repositories/").resolve(repositoryId.toString()), HttpMethod.DELETE, entity, String.class);
    }

    public void removeUser(Integer userId)
    {
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);
        rest.exchange(host.resolve("/api/v1/users/").resolve(userId.toString()), HttpMethod.DELETE, entity, String.class);
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
