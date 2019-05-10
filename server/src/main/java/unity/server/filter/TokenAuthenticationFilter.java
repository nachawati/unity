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

package unity.server.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.gitlab4j.api.Constants.TokenType;
import org.gitlab4j.api.GitLabApi;
import org.gitlab4j.api.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class TokenAuthenticationFilter extends GenericFilterBean
{
    private String gitLabUrl;

    public TokenAuthenticationFilter(String gitLabUrl)
    {
        this.gitLabUrl = gitLabUrl;
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException
    {
        SecurityContextHolder.getContext().setAuthentication(null);

        try {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            String accessToken = httpRequest.getHeader("Authorization");
            if (accessToken == null || !accessToken.startsWith("Bearer ")) {
                accessToken = httpRequest.getParameter("access-token");
                if (accessToken == null)
                    return;
            } else {
                accessToken = accessToken.substring(7);
            }
            GitLabApi gitLabApi = new GitLabApi(gitLabUrl, TokenType.ACCESS, accessToken);
            User user = gitLabApi.getUserApi().getCurrentUser();

            Collection<GrantedAuthority> authorities = null;
            Boolean admin = user.getIsAdmin();
            if (admin != null && admin)
                authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN"));
            else
                authorities = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
        } catch (Exception e) {
        } finally {
            chain.doFilter(request, response);
        }
    }
}
