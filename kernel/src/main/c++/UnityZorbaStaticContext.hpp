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

#ifndef _Included_UnityZorbaStaticContext
#define _Included_UnityZorbaStaticContext

#include <jni.h>
#include <iostream>
#include <string>
#include <python3.5/Python.h>
#include <unity-zorba.hpp>

class UnityZorbaResolver;

static void releaseStream(std::istream* stream)
{
    try {
        if (stream != nullptr)
            delete stream;
    } catch (const std::exception&) {
    }
}

class UnityZorbaResolver: public zorba::URIMapper, public zorba::URLResolver
{
public:
    UnityZorbaResolver(JNIEnv * env, jobject obj);

    virtual ~UnityZorbaResolver();

    virtual zorba::URIMapper::Kind mapperKind();

    virtual zorba::Resource* resolveURL(const zorba::String& aUrl, zorba::EntityData const* aEntityData);

    virtual void mapURI(const zorba::String aUri, zorba::EntityData const* aEntityData, std::vector<zorba::String>& oUris);
private:
    JavaVM* javaVM;
    jobject obj;
};

#endif
