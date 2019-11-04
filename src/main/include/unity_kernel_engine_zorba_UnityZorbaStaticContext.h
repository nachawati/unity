/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class unity_kernel_engine_zorba_UnityZorbaStaticContext */

#ifndef _Included_unity_kernel_engine_zorba_UnityZorbaStaticContext
#define _Included_unity_kernel_engine_zorba_UnityZorbaStaticContext
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     unity_kernel_engine_zorba_UnityZorbaStaticContext
 * Method:    addPythonPath
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_unity_kernel_engine_zorba_UnityZorbaStaticContext_addPythonPath
  (JNIEnv *, jobject, jstring);

/*
 * Class:     unity_kernel_engine_zorba_UnityZorbaStaticContext
 * Method:    configure
 * Signature: (Lio/zorba/api/XQuery;)V
 */
JNIEXPORT void JNICALL Java_unity_kernel_engine_zorba_UnityZorbaStaticContext_configure
  (JNIEnv *, jobject, jobject);

/*
 * Class:     unity_kernel_engine_zorba_UnityZorbaStaticContext
 * Method:    initialize
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_unity_kernel_engine_zorba_UnityZorbaStaticContext_initialize
  (JNIEnv *, jobject);

/*
 * Class:     unity_kernel_engine_zorba_UnityZorbaStaticContext
 * Method:    registerPythonModule
 * Signature: (Ljava/lang/String;Ljava/lang/String;)Z
 */
JNIEXPORT jboolean JNICALL Java_unity_kernel_engine_zorba_UnityZorbaStaticContext_registerPythonModule
  (JNIEnv *, jobject, jstring, jstring);

/*
 * Class:     unity_kernel_engine_zorba_UnityZorbaStaticContext
 * Method:    release
 * Signature: ()Z
 */
JNIEXPORT jboolean JNICALL Java_unity_kernel_engine_zorba_UnityZorbaStaticContext_release
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif
