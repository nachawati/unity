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

public class UnityJob
{
    private Instant        added;
    private String         commit;
    private Long           id;
    private Instant        modified;
    private String         name;
    private UnityUser      owner;
    private String         script;
    private UnityJobStatus status;

    public UnityJob()
    {
    }

    public Instant getAdded()
    {
        return added;
    }

    public String getCommit()
    {
        return commit;
    }

    public Long getId()
    {
        return id;
    }

    public Instant getModified()
    {
        return modified;
    }

    public String getName()
    {
        return name;
    }

    public UnityUser getOwner()
    {
        return owner;
    }

    public String getScript()
    {
        return script;
    }

    public UnityJobStatus getStatus()
    {
        return status;
    }

    public void setAdded(Instant added)
    {
        this.added = added;
    }

    public void setCommit(String commit)
    {
        this.commit = commit;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public void setModified(Instant modified)
    {
        this.modified = modified;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setOwner(UnityUser owner)
    {
        this.owner = owner;
    }

    public void setScript(String script)
    {
        this.script = script;
    }

    public void setStatus(UnityJobStatus status)
    {
        this.status = status;
    }
}
