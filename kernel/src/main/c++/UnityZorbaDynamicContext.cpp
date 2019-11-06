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

#include "UnityZorbaDynamicContext.hpp"

#include <io_dgms_unity_kernel_engine_zorba_UnityZorbaDynamicContext.h>

UnityExternalFunctionParameter::UnityExternalFunctionParameter(JNIEnv * env, jobject obj)
{
    env->GetJavaVM(&javaVM);
    this->obj = env->NewGlobalRef(obj);
}

UnityExternalFunctionParameter::~UnityExternalFunctionParameter()
{
    JNIEnv* env = NULL;
    try {
        if (obj == NULL)
            return;
        javaVM->AttachCurrentThread((void **) &env, NULL);
        if (env == NULL)
            return;
        jclass cls = env->GetObjectClass(obj);
        jmethodID mClose = env->GetMethodID(cls, "close", "()V");
        env->CallVoidMethod(obj, mClose);
        env->DeleteGlobalRef(obj);
        obj = NULL;
    } catch (...) {
    }

    javaVM->DetachCurrentThread();
}

void UnityExternalFunctionParameter::destroy() throw ()
{
    delete this;
}

zorba::DynamicContext* getDynamicContext(JNIEnv * env, jobject obj)
{
    jclass cls = env->GetObjectClass(obj);
    jfieldID fldDynamicContext = env->GetFieldID(cls, "dctx", "Lio/zorba/api/DynamicContext;");
    jobject objDynamicContext = env->GetObjectField(obj, fldDynamicContext);
    jclass clsDynamicContext = env->GetObjectClass(objDynamicContext);
    jfieldID fld = env->GetFieldID(clsDynamicContext, "swigCPtr", "J");
    return ((zorba::DynamicContext*) env->GetLongField(objDynamicContext, fld));
}

JNIEXPORT void JNICALL Java_io_dgms_unity_kernel_engine_zorba_UnityZorbaDynamicContext_addExternalFunctionParameter(JNIEnv * env, jobject obj, jstring name,
        jobject value)
{
    zorba::DynamicContext* dctx = getDynamicContext(env, obj);
    UnityExternalFunctionParameter* param = new UnityExternalFunctionParameter(env, value);
    const char *cstr = env->GetStringUTFChars(name, NULL);
    std::string str = std::string(cstr);
    env->ReleaseStringUTFChars(name, cstr);
    dctx->addExternalFunctionParameter(str, param);
}

JNIEXPORT jobject JNICALL Java_io_dgms_unity_kernel_engine_zorba_UnityZorbaDynamicContext_getExternalFunctionParameter(JNIEnv * env, jobject obj, jstring name)
{
    zorba::DynamicContext* dctx = getDynamicContext(env, obj);
    const char *cstr = env->GetStringUTFChars(name, NULL);
    std::string str = std::string(cstr);
    env->ReleaseStringUTFChars(name, cstr);
    UnityExternalFunctionParameter* param = static_cast<UnityExternalFunctionParameter*>(dctx->getExternalFunctionParameter(str));
    if (param == NULL)
        return NULL;
    return param->obj;
}
