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

import java.time.Instant;

import org.gitlab4j.api.models.Project;

public class UnityRepository
{
    private Instant         added;
    private Boolean         archived;
    private String          defaultBranch;
    private String          description;
    private Integer         forksCount;
    private String          httpUrlToRepo;
    private Integer         id;
    private String          imageUrl;
    private Boolean         isPublic;
    private Instant         lastActivity;
    private String          name;
    private String          nameWithNamespace;
    private String          path;
    private String          pathWithNamespace;
    private String          sshUrlToRepo;
    private Integer         starCount;
    private UnityVisibility visibility;
    private Integer         visibilityLevel;

    public UnityRepository()
    {
    }

    public UnityRepository(Project project)
    {
        this.added = project.getCreatedAt() != null ? project.getCreatedAt().toInstant() : null;
        this.archived = project.getArchived();
        this.defaultBranch = project.getDefaultBranch();
        this.description = project.getDescription();
        this.forksCount = project.getForksCount();
        this.httpUrlToRepo = project.getHttpUrlToRepo();
        this.id = project.getId();
        this.imageUrl = project.getAvatarUrl();
        this.isPublic = project.getPublic();
        this.lastActivity = project.getLastActivityAt() != null ? project.getLastActivityAt().toInstant() : null;
        this.name = project.getName();
        this.nameWithNamespace = project.getNameWithNamespace();
        this.path = project.getPath();
        this.pathWithNamespace = project.getPathWithNamespace();
        this.sshUrlToRepo = project.getSshUrlToRepo();
        this.starCount = project.getStarCount();
        this.visibilityLevel = project.getVisibilityLevel();
        if (project.getVisibility() != null)
            switch (project.getVisibility()) {
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

    public Instant getAdded()
    {
        return added;
    }

    public Boolean getArchived()
    {
        return archived;
    }

    public String getDefaultBranch()
    {
        return defaultBranch;
    }

    public String getDescription()
    {
        return description;
    }

    public Integer getForksCount()
    {
        return forksCount;
    }

    public String getHttpUrlToRepo()
    {
        return httpUrlToRepo;
    }

    public Integer getId()
    {
        return id;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public Instant getLastActivity()
    {
        return lastActivity;
    }

    public String getName()
    {
        return name;
    }

    public String getNameWithNamespace()
    {
        return nameWithNamespace;
    }

    public String getPath()
    {
        return path;
    }

    public String getPathWithNamespace()
    {
        return pathWithNamespace;
    }

    public String getSshUrlToRepo()
    {
        return sshUrlToRepo;
    }

    public Integer getStarCount()
    {
        return starCount;
    }

    public UnityVisibility getVisibility()
    {
        return visibility;
    }

    public Integer getVisibilityLevel()
    {
        return visibilityLevel;
    }

    public Boolean isPublic()
    {
        return isPublic;
    }

    public void setAdded(Instant added)
    {
        this.added = added;
    }

    public void setArchived(Boolean archived)
    {
        this.archived = archived;
    }

    public void setDefaultBranch(String defaultBranch)
    {
        this.defaultBranch = defaultBranch;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setForksCount(Integer forksCount)
    {
        this.forksCount = forksCount;
    }

    public void setHttpUrlToRepo(String httpUrlToRepo)
    {
        this.httpUrlToRepo = httpUrlToRepo;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public void setLastActivity(Instant lastActivity)
    {
        this.lastActivity = lastActivity;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setNameWithNamespace(String nameWithNamespace)
    {
        this.nameWithNamespace = nameWithNamespace;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public void setPathWithNamespace(String pathWithNamespace)
    {
        this.pathWithNamespace = pathWithNamespace;
    }

    public void setPublic(Boolean isPublic)
    {
        this.isPublic = isPublic;
    }

    public void setSshUrlToRepo(String sshUrlToRepo)
    {
        this.sshUrlToRepo = sshUrlToRepo;
    }

    public void setStarCount(Integer starCount)
    {
        this.starCount = starCount;
    }

    public void setVisibility(UnityVisibility visibility)
    {
        this.visibility = visibility;
    }

    public void setVisibilityLevel(Integer visibilityLevel)
    {
        this.visibilityLevel = visibilityLevel;
    }
}
