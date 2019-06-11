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

#include "UnityAnalyticsEngineManager.hpp"
#include <unity_kernel_engine_UnityAnalyticsEngineManager.h>
#include <iostream>
JNIEXPORT void JNICALL Java_unity_kernel_engine_UnityAnalyticsEngineManager_Py_1Initialize(JNIEnv * env, jobject obj, jstring mode)
{
    dlopen("libpython3.5m.so", RTLD_NOW | RTLD_GLOBAL);
    Py_Initialize();
    PyEval_InitThreads();
    PyRun_SimpleString("import os");
    PyRun_SimpleString("os.environ['TF_CPP_MIN_LOG_LEVEL'] = '3'");

    const char *mode_string = env->GetStringUTFChars(mode, 0);
    char command[2048];
    strcpy(command, "os.environ['SYMBOLICS_MODE'] = '");
    strcat(command, mode_string);
    strcat(command, "'");
    env->ReleaseStringUTFChars(mode, mode_string);

    PyRun_SimpleString(command);
    //PyRun_SimpleString("import tensorflow as tf");
    //PyRun_SimpleString("tf.enable_eager_execution();");
    PyRun_SimpleString("import sys");

    char* home = std::getenv("DGMS_HOME");
    if (home == nullptr)
    	home = "/opt/unity";

    strcpy(command, "if (os.path.abspath(\"");
    strcat(command, home);
    strcat(command, "/lib");
    strcat(command, "\")) not in sys.path: sys.path.append(os.path.abspath(\"");
    strcat(command, home);
    strcat(command, "/lib\"))");
    PyRun_SimpleString(command);
}

JNIEXPORT void JNICALL Java_unity_kernel_engine_UnityAnalyticsEngineManager_Py_1Finalize(JNIEnv * env, jobject obj)
{
    Py_Finalize();
}
