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

#include "UnityZorbaStaticContext.hpp"

#include <io_dgms_unity_kernel_engine_zorba_UnityZorbaStaticContext.h>

JNIEXPORT void JNICALL Java_io_dgms_unity_kernel_engine_zorba_UnityZorbaStaticContext_addPythonPath(JNIEnv* env, jobject obj, jstring path)
{
    const char *pathUTFChars = env->GetStringUTFChars(path, NULL);
    char command[2048];
    strcpy(command, "if (\"");
    strcat(command, pathUTFChars);
    strcat(command, "\") not in sys.path: sys.path.append(\"");
    strcat(command, pathUTFChars);
    strcat(command, "\")");
    PyRun_SimpleString(command);
    env->ReleaseStringUTFChars(path, pathUTFChars);
}

JNIEXPORT void JNICALL Java_io_dgms_unity_kernel_engine_zorba_UnityZorbaStaticContext_configure(JNIEnv* env, jobject obj, jobject xquery)
{
    jclass cls = env->GetObjectClass(obj);
    jfieldID fld = env->GetFieldID(cls, "swigCPtr", "J");
    zorba::StaticContext* sctx = ((zorba::StaticContext_t*) env->GetLongField(obj, fld))->get();
    cls = env->GetObjectClass(xquery);
    fld = env->GetFieldID(cls, "swigCPtr", "J");
    zorba::DynamicContext* dctx = ((zorba::XQuery_t*) env->GetLongField(xquery, fld))->get()->getDynamicContext();
    dctx->addExternalFunctionParam("sctx", sctx);
    dctx->addExternalFunctionParam("dctx", dctx);
}

JNIEXPORT jboolean JNICALL Java_io_dgms_unity_kernel_engine_zorba_UnityZorbaStaticContext_initialize(JNIEnv* env, jobject obj)
{
    try {
        jclass cls = env->GetObjectClass(obj);
        jfieldID fld = env->GetFieldID(cls, "swigCPtr", "J");
        zorba::StaticContext* sctx = *((zorba::StaticContext**) env->GetLongField(obj, fld));
        if (sctx == NULL)
            return JNI_FALSE;
        UnityZorbaResolver* resolver = new UnityZorbaResolver(env, obj);
        cls = env->GetObjectClass(obj);
        fld = env->GetFieldID(cls, "resolverCPtr", "J");
        env->SetLongField(obj, fld, (jlong) resolver);
        sctx->registerURLResolver(resolver);
        sctx->registerURIMapper(resolver);
        return JNI_TRUE;
    } catch (...) {
        return JNI_FALSE;
    }
}

JNIEXPORT jboolean JNICALL Java_io_dgms_unity_kernel_engine_zorba_UnityZorbaStaticContext_registerPythonModule(JNIEnv* env, jobject obj, jstring uri, jstring name)
{
    try {
        const char *uriUTFChars = env->GetStringUTFChars(uri, nullptr);
        const char *nameUTFChars = env->GetStringUTFChars(name, nullptr);
        UnityZorbaPythonModule* module = new UnityZorbaPythonModule(uriUTFChars, nameUTFChars);
        env->ReleaseStringUTFChars(uri, uriUTFChars);
        env->ReleaseStringUTFChars(name, nameUTFChars);
        jclass cls = env->GetObjectClass(obj);
        jfieldID fld = env->GetFieldID(cls, "swigCPtr", "J");
        zorba::StaticContext* sctx = *((zorba::StaticContext**) env->GetLongField(obj, fld));
        if (sctx == NULL)
            return JNI_FALSE;
        if (!PyErr_Occurred()) {
            sctx->registerModule(module);
            return JNI_TRUE;
        } else {
            delete module;
            PyErr_Clear();
            return JNI_FALSE;
        }
    } catch (...) {
        return JNI_FALSE;
    }
}

JNIEXPORT jboolean JNICALL Java_io_dgms_unity_kernel_engine_zorba_UnityZorbaStaticContext_release(JNIEnv* env, jobject obj)
{
    try {
        jclass cls = env->GetObjectClass(obj);
        jfieldID fld = env->GetFieldID(cls, "resolverCPtr", "J");
        jlong resolverCPtr = env->GetLongField(obj, fld);
        env->SetLongField(obj, fld, 0);
        if (resolverCPtr != 0)
            delete (UnityZorbaResolver*) resolverCPtr;
        return JNI_TRUE;
    } catch (...) {
        return JNI_FALSE;
    }
}

UnityZorbaResolver::UnityZorbaResolver(JNIEnv * env, jobject obj)
{
    env->GetJavaVM(&javaVM);
    this->obj = env->NewGlobalRef(obj);
}

UnityZorbaResolver::~UnityZorbaResolver()
{
    JNIEnv* env = NULL;
    try {
        if (obj == NULL)
            return;
        javaVM->AttachCurrentThread((void **) &env, NULL);
        if (env == NULL)
            return;
        env->DeleteGlobalRef(obj);
    } catch (...) {
    }

    javaVM->DetachCurrentThread();
}

zorba::URIMapper::Kind UnityZorbaResolver::mapperKind()
{
    return (zorba::URIMapper::Kind) 1;
}

zorba::Resource* UnityZorbaResolver::resolveURL(const zorba::String& aUrl, zorba::EntityData const* aEntityData)
{
    zorba::Resource* resource = NULL;
    JNIEnv* env = NULL;
    try {
        javaVM->AttachCurrentThread((void **) &env, NULL);
        if (env == NULL)
            return NULL;
        jclass cls = env->GetObjectClass(obj);
        jmethodID resolve = env->GetMethodID(cls, "resolve", "(Ljava/lang/String;I)Ljava/lang/String;");
        jstring result = (jstring) env->CallObjectMethod(obj, resolve, env->NewStringUTF(aUrl.c_str()), aEntityData->getKind());
        if (result != NULL) {
            const char *resultUTFChars = env->GetStringUTFChars(result, NULL);
            std::stringstream* stream = new std::stringstream(resultUTFChars);
            env->ReleaseStringUTFChars(result, resultUTFChars);
            resource = zorba::StreamResource::create(stream, &releaseStream, true);
        }
    } catch (...) {
    }

    javaVM->DetachCurrentThread();
    return resource;
}

void UnityZorbaResolver::mapURI(const zorba::String aUri, zorba::EntityData const* aEntityData, std::vector<zorba::String>& oUris)
{
    JNIEnv* env = NULL;
    try {
        javaVM->AttachCurrentThread((void **) &env, NULL);
        if (env == NULL)
            return;
        jclass cls = env->GetObjectClass(obj);
        jmethodID map = env->GetMethodID(cls, "map", "(Ljava/lang/String;I)Ljava/lang/String;");
        jstring result = (jstring) env->CallObjectMethod(obj, map, env->NewStringUTF(aUri.c_str()), aEntityData->getKind());
        if (result != NULL) {
            const char *resultUTFChars = env->GetStringUTFChars(result, NULL);
            oUris.push_back(resultUTFChars);
            env->ReleaseStringUTFChars(result, resultUTFChars);
        }
    } catch (...) {
    }
    javaVM->DetachCurrentThread();
}
