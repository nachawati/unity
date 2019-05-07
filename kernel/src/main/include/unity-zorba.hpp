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

#ifndef _Included_unity_zorba
#define _Included_unity_zorba

#include <algorithm>
#include <cstdlib>
#include <cstring>
#include <iostream>
#include <map>
#include <python3.5/Python.h>
#include <string>
#include <vector>
#include <zorba/empty_sequence.h>
#include <zorba/external_module.h>
#include <zorba/singleton_item_sequence.h>
#include <zorba/user_exception.h>
#include <zorba/vector_item_sequence.h>
#include <zorba/zorba_string.h>
#include <zorba/zorba.h>

class ltstr
{
public:
    bool operator()(const zorba::String& s1, const zorba::String& s2) const;
};

typedef std::map<zorba::String, zorba::ExternalFunction*, ltstr> FuncMap_t;

zorba::ItemFactory* getItemFactory();

class PyZorbaItem
{
    zorba::StaticContext* sctx;
    zorba::DynamicContext* dctx;
    zorba::Item item;
public:
    PyZorbaItem(zorba::Item item, const zorba::StaticContext* sctx, const zorba::DynamicContext* dctx);

    ~PyZorbaItem();

    zorba::Item getItem();

    const zorba::StaticContext* getStaticContext();

    const zorba::DynamicContext* getDynamicContext();
};

class PyObjectParameter: public zorba::ExternalFunctionParameter
{
    PyObject* object;
    int type;

public:

    PyObjectParameter(PyObject* object, int type);

    virtual ~PyObjectParameter();

    virtual void destroy() throw ();

    PyObject* getObject();

    int getType();
};

class PyObjectVectorParameter: public zorba::ExternalFunctionParameter
{
    std::vector<PyObjectParameter*> vector;

public:

    PyObjectVectorParameter();

    virtual ~PyObjectVectorParameter();

    virtual void destroy() throw ();

    void push(PyObjectParameter* object);

    PyObjectParameter* pop();

    PyObjectParameter* peek();
};

PyObject* getItemAsPyObject(zorba::Item item, const zorba::StaticContext* sctx, const zorba::DynamicContext* dctx);

PyObject* getItemSequenceAsPyObject(zorba::ItemSequence* sequence, const zorba::StaticContext* sctx, const zorba::DynamicContext* dctx);

PyObject* getItemSequenceAsPyTuple(zorba::ItemSequence* sequence, const zorba::StaticContext* sctx, const zorba::DynamicContext* dctx);

zorba::Item getPyObjectAsItem(PyObject* object, const zorba::StaticContext* sctx, const zorba::DynamicContext* dctx);

zorba::ItemSequence* getPyObjectAsItemSequence(PyObject* object, const zorba::StaticContext* sctx, const zorba::DynamicContext* dctx);

std::vector<zorba::ItemSequence_t> getPyObjectAsItemSequenceVector(PyObject* object, const zorba::StaticContext* sctx, const zorba::DynamicContext* dctx);

class UnityZorbaPythonFunction: public zorba::ContextualExternalFunction
{
    zorba::String uri;
    zorba::String localName;
    PyObject* callable;

public:

    UnityZorbaPythonFunction(zorba::String uri, zorba::String localName, PyObject* callable);

    virtual ~UnityZorbaPythonFunction();

    virtual zorba::String getURI() const;

    virtual zorba::String getLocalName() const;

    virtual zorba::ItemSequence_t evaluate(const Arguments_t& args, const zorba::StaticContext* sctx, const zorba::DynamicContext* dctx) const;
};

class UnityZorbaPythonModule: public zorba::ExternalModule
{
protected:

    zorba::String uri;
    FuncMap_t functions;
    PyObject* module;

public:

    UnityZorbaPythonModule(std::string uri, std::string name);

    virtual ~UnityZorbaPythonModule();

    virtual void destroy();

    virtual zorba::String getURI() const;

    virtual zorba::ExternalFunction* getExternalFunction(const zorba::String& localName);
};

#endif
