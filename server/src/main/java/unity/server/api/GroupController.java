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

package unity.server.api;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.GitLabApiException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import unity.client.UnityGroup;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupController
{
    @PostMapping
    @Secured("ROLE_USER")
    public UnityGroup addGroup(@RequestBody Map<String, String> requestBody) throws GitLabApiException
    {
        GitLabApi gitLabApi = (GitLabApi) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        return new UnityGroup(gitLabApi.getGroupApi().addGroup(requestBody.get("name"), requestBody.get("path")));
    }

    @GetMapping("{groupId}")
    @Secured("ROLE_USER")
    public UnityGroup getGroup(@PathVariable Integer groupId) throws GitLabApiException
    {
        GitLabApi gitLabApi = (GitLabApi) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        return new UnityGroup(gitLabApi.getGroupApi().getGroup(groupId));
    }

    @GetMapping
    @Secured("ROLE_USER")
    public List<UnityGroup> listGroups() throws GitLabApiException
    {
        GitLabApi gitLabApi = (GitLabApi) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        return gitLabApi.getGroupApi().getGroups().stream().map(group -> new UnityGroup(group)).collect(Collectors.toList());
    }

    @DeleteMapping("{groupId}")
    @Secured("ROLE_USER")
    public void removeGroup(@PathVariable Integer groupId) throws GitLabApiException
    {
        GitLabApi gitLabApi = (GitLabApi) SecurityContextHolder.getContext().getAuthentication().getCredentials();
        gitLabApi.getGroupApi().deleteGroup(groupId);
    }
}
