#
# بسم الله الرحمن الرحيم 
#
# In the name of Allah, the Most Compassionate, the Most Merciful
#
# This file is part of Unity DGMS <https://www.dgms.io/>
#
# Unity DGMS is free software; redistribution and use in source and binary
# forms, with or without modification, are permitted provided that the
# following conditions are met:
#
# 1. Redistributions of source code must retain the above notice, this list of
#    conditions and the following disclaimer.
#
# 2. Redistributions in binary form must reproduce the above notice, this list
#    of conditions and the following disclaimer in the documentation and/or
#    other materials provided with the distribution.
#
# THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHORS DISCLAIM ALL WARRANTIES
# WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
# MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHORS BE LIABLE FOR ANY
# SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
# WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN ACTION
# OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR IN
# CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
#

cmake_minimum_required(VERSION 2.8)

project(unity-kernel)

find_package(JNI)

find_package(PythonLibs 3.7 REQUIRED)

add_library(${PROJECT_NAME} SHARED unity-zorba.cpp UnityAnalyticsEngineManager.cpp UnityZorbaDynamicContext.cpp UnityZorbaStaticContext.cpp)
set_target_properties(${PROJECT_NAME} PROPERTIES OUTPUT_NAME "unity-kernel")

if (linux-gcc-amd64)
	include_directories(
		"${CMAKE_SOURCE_DIR}/../include"
		"${CMAKE_SOURCE_DIR}/../../../../target/unity/linux-gcc-amd64/include"
		"${JAVA_INCLUDE_PATH}"
		"${JAVA_INCLUDE_PATH2}"
		"${PYTHON_INCLUDE_DIRS}")
	target_link_libraries(${PROJECT_NAME} PRIVATE
		"${PYTHON_LIBRARIES}"
		"${CMAKE_SOURCE_DIR}/../../../../target/unity/linux-gcc-amd64/lib/libzorba_simplestore.so")
endif()
