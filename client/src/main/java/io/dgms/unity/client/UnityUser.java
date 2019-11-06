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

import java.time.Instant;

import org.gitlab4j.api.models.User;

public class UnityUser
{
    private Instant added;
    private Boolean administrator;
    private String  description;
    private String  email;
    private Integer id;
    private String  imageUrl;
    private String  name;
    private String  username;

    public UnityUser()
    {
    }

    public UnityUser(User user)
    {
        this.added = user.getCreatedAt() != null ? user.getCreatedAt().toInstant() : null;
        this.administrator = user.getIsAdmin();
        this.description = user.getBio();
        this.email = user.getEmail();
        this.id = user.getId();
        this.imageUrl = user.getAvatarUrl();
        this.name = user.getName();
        this.username = user.getUsername();
    }

    public Instant getAdded()
    {
        return added;
    }

    public Boolean getAdministrator()
    {
        return administrator;
    }

    public String getDescription()
    {
        return description;
    }

    public String getEmail()
    {
        return email;
    }

    public Integer getId()
    {
        return id;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public String getName()
    {
        return name;
    }

    public String getUsername()
    {
        return username;
    }

    public void setAdded(Instant added)
    {
        this.added = added;
    }

    public void setAdministrator(Boolean administrator)
    {
        this.administrator = administrator;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
}
