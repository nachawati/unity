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

#include <unity-zorba.hpp>
#include <zorba/external_module.h>

#define IO_DGMS_MODULES_REFLECTION_NAMESPACE "http://dgms.io/modules/reflection"

class ReflectionModule;

class InvokeVariadicFunction: public zorba::ContextualExternalFunction
{
public:

    InvokeVariadicFunction()
    {
    }

    virtual ~InvokeVariadicFunction()
    {
    }

    virtual zorba::String getURI() const
    {
        return IO_DGMS_MODULES_REFLECTION_NAMESPACE;
    }

    virtual zorba::String getLocalName() const
    {
        return "invoke-variadic";
    }

    virtual zorba::ItemSequence_t evaluate(const Arguments_t& args, const zorba::StaticContext* sctx, const zorba::DynamicContext* dctx) const;
};

class InvokeFunction: public zorba::ContextualExternalFunction
{
public:

    InvokeFunction()
    {
    }

    virtual ~InvokeFunction()
    {
    }

    virtual zorba::String getURI() const
    {
        return IO_DGMS_MODULES_REFLECTION_NAMESPACE;
    }

    virtual zorba::String getLocalName() const
    {
        return "invoke";
    }

    virtual zorba::ItemSequence_t evaluate(const Arguments_t& args, const zorba::StaticContext* sctx, const zorba::DynamicContext* dctx) const;
};

class ReflectionModule: public zorba::ExternalModule
{
    zorba::String uri;
    FuncMap_t functions;

public:

    ReflectionModule(std::string uri) :
            uri(uri)
    {
    }

    ~ReflectionModule()
    {
        for (FuncMap_t::const_iterator lIter = functions.begin(); lIter != functions.end(); ++lIter)
            delete lIter->second;
        functions.clear();
    }

    virtual void destroy()
    {
        delete this;
    }

    virtual zorba::String getURI() const
    {
        return uri;
    }

    virtual zorba::ExternalFunction* getExternalFunction(const zorba::String& localName)
    {
        FuncMap_t::iterator iter = functions.find(localName);
        zorba::ExternalFunction*& function = functions[localName];

        if (iter == functions.end()) {
            if (localName == "invoke-variadic")
                function = new InvokeVariadicFunction();
            else if (localName == "invoke")
                function = new InvokeFunction();
            else
                function = NULL;
        }

        return function;
    }
};

zorba::ItemSequence_t InvokeVariadicFunction::evaluate(const Arguments_t& args, const zorba::StaticContext* sctx, const zorba::DynamicContext* dctx) const
{
    zorba::Item aQName;

    zorba::Iterator_t iter = args[0]->getIterator();
    iter->open();
    iter->next(aQName);
    iter->close();

    if (aQName.isAtomic()) {
        PyObject* callable = getItemAsPyObject(aQName, sctx, dctx);
        PyObject* result;
        if (PyCallable_Check(callable)) {
            PyObject* py_args;
            PyObject* py_kwargs = nullptr;

            if (args.size() == 1) {
                py_args = PyTuple_New(0);
            }
            else {
                Py_ssize_t i = 0;
                py_args = PyTuple_New(args.size() - 1);
                int last = args.size() - 1;
                for (Arguments_t::const_iterator it = args.begin(); it != args.end(); it++, i++) {
                    if (i == 0)
                        continue;
                    PyObject* py_arg = getItemSequenceAsPyObject(*it, sctx, dctx);
                    if (i == last && PyDict_Check(py_arg)) {
                        _PyTuple_Resize(&py_args, last - 1);
                        py_kwargs = py_arg;
                    }
                    else {
                        PyTuple_SET_ITEM(py_args, i - 1, py_arg);
                    }
                }
            }

            result = PyObject_Call(callable, py_args, py_kwargs);
            Py_XDECREF(py_args);
        } else {
            if (args.size() - 1 == 0) {
                return new zorba::SingletonItemSequence(aQName);
            } else if (args.size() - 1 == 1) {
                PyObject* argument;
                Py_ssize_t i = 0;
                for (Arguments_t::const_iterator it = args.begin(); it != args.end(); it++, i++)
                    if (i == 0)
                        continue;
                    else
                        argument = getItemSequenceAsPyObject(*it, sctx, dctx);
                result = PyObject_CallFunctionObjArgs(PyObject_GetAttrString(callable, "__getitem__"), argument, nullptr);
                Py_XDECREF(argument);
            } else {
                PyObject* arguments = PyTuple_New(args.size() - 1);
                Py_ssize_t i = 0;
                for (Arguments_t::const_iterator it = args.begin(); it != args.end(); it++, i++)
                    if (i == 0)
                        continue;
                    else
                        PyTuple_SetItem(arguments, i - 1, getItemSequenceAsPyObject(*it, sctx, dctx));
                result = PyObject_CallFunctionObjArgs(PyObject_GetAttrString(callable, "__getitem__"), arguments, nullptr);
                Py_XDECREF(arguments);
            }
        }

        if (PyErr_Occurred()) {
            zorba::Item errQName = getItemFactory()->createQName("http://dgms.io/errors", "error");
            PyObject *type, *value, *traceback;
            PyErr_Fetch(&type, &value, &traceback);
            zorba::String errDescription = "";
            if (value) {
                PyObject *str = PyObject_Str(value);
                if (str && PyUnicode_READY(str) == 0)
                    errDescription = (char*) PyUnicode_DATA(str);
            }
            throw USER_EXCEPTION(errQName, errDescription);
        }

        zorba::ItemSequence* res = getPyObjectAsItemSequence(result, sctx, dctx);
        //Py_XDECREF(result);
        return zorba::ItemSequence_t(res);
    }
    else if (aQName.isJSONItem()) {
        if (args.size() > 2)
            throw USER_EXCEPTION(getItemFactory()->createQName("http://zorba.io/errors", "jerr", "JNTY0018"),
                    "object or array selection needs zero or one parameter");
        switch (aQName.getJSONItemKind()) {

            case zorba::store::StoreConsts::JSONItemKind::jsonArray: {
                if (args.size() == 1) {
                    std::vector<zorba::Item> items;
                    for (int i = 1; i < aQName.getArraySize(); i++)
                        items.push_back(aQName.getArrayValue(i));
                    return new zorba::VectorItemSequence(items);
                }
                else {
                    zorba::Item index;
                    zorba::Iterator_t iter_index = args[1]->getIterator();
                    iter_index->open();
                    iter_index->next(index);
                    iter_index->close();
                    return new zorba::SingletonItemSequence(aQName.getArrayValue(index.getLongValue()));
                }
            }
            case zorba::store::StoreConsts::JSONItemKind::jsonObject: {
                if (args.size() == 1) {
                    std::vector<zorba::Item> items;
                    zorba::Iterator_t keys = aQName.getObjectKeys();
                    zorba::Item item;
                    keys->open();
                    while (keys->next(item)) {
                        items.push_back(item);
                    }
                    keys->close();
                    return new zorba::VectorItemSequence(items);
                }
                else {
                    zorba::Item index;
                    zorba::Iterator_t iter_index = args[1]->getIterator();
                    iter_index->open();
                    iter_index->next(index);
                    iter_index->close();
                    return new zorba::SingletonItemSequence(aQName.getObjectValue(index.getStringValue()));
                }
            }

            default:
                return new zorba::EmptySequence();
        }
    }
    else {
        std::vector<zorba::ItemSequence_t> aArgs;
        for (int i = 1; i < args.size(); i++)
            aArgs.push_back(zorba::ItemSequence_t(args[i]));
        return sctx->invoke(aQName, aArgs);
    }
}

zorba::ItemSequence_t InvokeFunction::evaluate(const Arguments_t& args, const zorba::StaticContext* sctx, const zorba::DynamicContext* dctx) const
{
    zorba::Item aQName;

    zorba::Iterator_t iter = args[0]->getIterator();
    iter->open();
    iter->next(aQName);
    iter->close();

    if (aQName.isAtomic()) {

        zorba::Item item;
        zorba::Iterator_t iter = args[1]->getIterator();
        iter->open();
        iter->next(item);
        iter->close();

        PyObject* arguments = PyTuple_New(item.getArraySize());
        for (Py_ssize_t i = 0; i < item.getArraySize(); i++)
            PyTuple_SetItem(arguments, i, getItemAsPyObject(item.getArrayValue(i), sctx, dctx));
        PyObject* result = PyObject_CallObject(getItemAsPyObject(aQName, sctx, dctx), arguments);
        Py_XDECREF(arguments);
        zorba::ItemSequence* res = getPyObjectAsItemSequence(result, sctx, dctx);
        //Py_XDECREF(result);
        return zorba::ItemSequence_t(res);
    }
    else {
        zorba::Item item;
        zorba::Iterator_t iter = args[1]->getIterator();
        iter->open();
        std::vector<zorba::ItemSequence_t> aArgs;
        while (iter->next(item))
            aArgs.push_back(zorba::ItemSequence_t(new zorba::SingletonItemSequence(item)));
        iter->close();
        return sctx->invoke(aQName, aArgs);
    }
}

#ifdef WIN32
#  define DLL_EXPORT __declspec(dllexport)
#else
#  define DLL_EXPORT __attribute__ ((visibility("default")))
#endif

extern "C" DLL_EXPORT zorba::ExternalModule* createModule()
{
    return new ReflectionModule(IO_DGMS_MODULES_REFLECTION_NAMESPACE);
}
