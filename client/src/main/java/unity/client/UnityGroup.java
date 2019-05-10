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

import org.gitlab4j.api.models.Group;

public class UnityGroup
{
    private String          description;
    private String          fullName;
    private String          fullPath;
    private Integer         id;
    private String          imageUrl;
    private String          name;
    private Integer         parentId;
    private String          path;
    private UnityVisibility visibility;

    public UnityGroup()
    {
    }

    public UnityGroup(Group group)
    {
        this.description = group.getDescription();
        this.fullName = group.getFullName();
        this.fullPath = group.getFullPath();
        this.id = group.getId();
        this.imageUrl = group.getAvatarUrl();
        this.name = group.getName();
        this.parentId = group.getParentId();
        this.path = group.getPath();
        if (group.getVisibility() != null)
            switch (group.getVisibility()) {
            case INTERNAL:
                this.visibility = UnityVisibility.INTERNAL;
                break;
            case PRIVATE:
                this.visibility = UnityVisibility.INTERNAL;
                break;
            case PUBLIC:
                this.visibility = UnityVisibility.INTERNAL;
                break;

            }
    }

    public String getDescription()
    {
        return description;
    }

    public String getFullName()
    {
        return fullName;
    }

    public String getFullPath()
    {
        return fullPath;
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

    public Integer getParentId()
    {
        return parentId;
    }

    public String getPath()
    {
        return path;
    }

    public UnityVisibility getVisibility()
    {
        return visibility;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public void setFullPath(String fullPath)
    {
        this.fullPath = fullPath;
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

    public void setParentId(Integer parentId)
    {
        this.parentId = parentId;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public void setVisibility(UnityVisibility visibility)
    {
        this.visibility = visibility;
    }
}
