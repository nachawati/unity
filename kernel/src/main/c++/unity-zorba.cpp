#include <unity-zorba.hpp>
#include <python3.5/Python.h>
#include <vector>
#include <zorba/empty_sequence.h>
#include <zorba/external_module.h>
#include <zorba/singleton_item_sequence.h>
#include <zorba/vector_item_sequence.h>
#include <zorba/zorba_string.h>
#include <zorba/zorba.h>
#include <iostream>

#ifdef WIN32
#  define DLL_EXPORT __declspec(dllexport)
#else
#  define DLL_EXPORT __attribute__ ((visibility("default")))
#endif

extern "C" DLL_EXPORT PyObject* pointer(void* address)
{
    return (PyObject*) address;
}

extern "C" DLL_EXPORT PyObject* invoke(PyObject* py_func, PyObject* py_args)
{
    PyGILState_STATE gstate;
    gstate = PyGILState_Ensure();

    PyZorbaItem* func = (PyZorbaItem*) PyCapsule_GetPointer(py_func, nullptr);
    std::vector<zorba::ItemSequence_t> args = getPyObjectAsItemSequenceVector(py_args, func->getStaticContext(), func->getDynamicContext());
    zorba::ItemSequence_t result = func->getStaticContext()->invoke(func->getItem(), args);
    PyObject* tuple = getItemSequenceAsPyTuple(result, func->getStaticContext(), func->getDynamicContext());

    PyGILState_Release(gstate);
    return tuple;
}

bool ltstr::operator()(const zorba::String& s1, const zorba::String& s2) const
{
    return s1.compare(s2) < 0;
}

zorba::ItemFactory* getItemFactory()
{
    return zorba::Zorba::getInstance(0)->getItemFactory();
}

PyZorbaItem::PyZorbaItem(zorba::Item item, const zorba::StaticContext* sctx, const zorba::DynamicContext* dctx)
{
    this->item = item;
    this->sctx = const_cast<zorba::StaticContext*>(sctx);
    this->dctx = const_cast<zorba::DynamicContext*>(dctx);
}

PyZorbaItem::~PyZorbaItem()
{
    //sctx->removeReference();
}

zorba::Item PyZorbaItem::getItem()
{
    return item;
}

const zorba::StaticContext* PyZorbaItem::getStaticContext()
{
    return sctx;
}

const zorba::DynamicContext* PyZorbaItem::getDynamicContext()
{
    return dctx;
}

PyObjectParameter::PyObjectParameter(PyObject* object, int type) :
        object(object), type(type)
{
    Py_XINCREF(object);
}

PyObjectParameter::~PyObjectParameter()
{
    Py_XDECREF(object);
}

void PyObjectParameter::destroy() throw ()
{
    delete this;
}

PyObject* PyObjectParameter::getObject()
{
    return object;
}

int PyObjectParameter::getType()
{
    return type;
}

PyObjectVectorParameter::PyObjectVectorParameter()
{
}

PyObjectVectorParameter::~PyObjectVectorParameter()
{
    for (std::vector<PyObjectParameter*>::iterator it = vector.begin(); it != vector.end(); ++it)
        delete *it;
    vector.clear();
}

void PyObjectVectorParameter::destroy() throw ()
{
    delete this;
}

void PyObjectVectorParameter::push(PyObjectParameter* object)
{
    vector.push_back(object);
}

PyObjectParameter* PyObjectVectorParameter::pop()
{
    PyObjectParameter* object = vector.back();
    vector.pop_back();
    return object;
}

PyObjectParameter* PyObjectVectorParameter::peek()
{
    return vector.back();
}

PyObject* getItemAsPyObject(zorba::Item item, const zorba::StaticContext* sctx, const zorba::DynamicContext* dctx)
{
    if (item.isNull())
        return Py_None;
    if (item.isJSONItem()) {
        switch (item.getJSONItemKind()) {
            case zorba::store::StoreConsts::JSONItemKind::jsonArray: {
                int size = item.getArraySize();
                PyObject* object = PyTuple_New(item.getArraySize());
                for (int i = 0; i < size; i++)
                    PyTuple_SET_ITEM(object, i, getItemAsPyObject(item.getArrayValue(i + 1), sctx, dctx));
                return object;
            }
            case zorba::store::StoreConsts::JSONItemKind::jsonObject: {
                PyObject* object = PyDict_New();
                zorba::Item key;
                zorba::Iterator_t keys = item.getObjectKeys();
                keys->open();
                while (keys->next(key)) {
                    zorba::String keyValue = key.getStringValue();
                    PyDict_SetItemString(object, keyValue.str().c_str(), getItemAsPyObject(item.getObjectValue(keyValue), sctx, dctx));
                }
                keys->close();
                return object;
            }
            default:
                return Py_None;
        }
    }
    else if (item.isAtomic()) {
        switch (item.getTypeCode()) {
            case zorba::store::SchemaTypeCode::XS_ANY_URI: {
                PyObjectParameter* parameter = dynamic_cast<PyObjectParameter*>(dctx->getExternalFunctionParameter(item.getStringValue()));
                if (parameter == nullptr)
                    return Py_None;
                return parameter->getObject();
            }
            case zorba::store::SchemaTypeCode::XS_STRING:
                return PyUnicode_FromString(item.getStringValue().str().c_str());
            case zorba::store::SchemaTypeCode::XS_BOOLEAN:
                return item.getBooleanValue() ? Py_True : Py_False;
            case zorba::store::SchemaTypeCode::XS_FLOAT:
            case zorba::store::SchemaTypeCode::XS_DOUBLE:
                return PyFloat_FromDouble(item.getDoubleValue());
            case zorba::store::SchemaTypeCode::XS_DECIMAL:
                return PyFloat_FromDouble(std::stod(item.getStringValue().str()));
            case zorba::store::SchemaTypeCode::XS_INTEGER:
            case zorba::store::SchemaTypeCode::XS_NON_POSITIVE_INTEGER:
            case zorba::store::SchemaTypeCode::XS_NEGATIVE_INTEGER:
            case zorba::store::SchemaTypeCode::XS_LONG:
            case zorba::store::SchemaTypeCode::XS_INT:
            case zorba::store::SchemaTypeCode::XS_SHORT:
            case zorba::store::SchemaTypeCode::XS_BYTE:
                return PyLong_FromLongLong(item.getLongValue());
            case zorba::store::SchemaTypeCode::XS_NON_NEGATIVE_INTEGER:
            case zorba::store::SchemaTypeCode::XS_UNSIGNED_LONG:
            case zorba::store::SchemaTypeCode::XS_UNSIGNED_INT:
            case zorba::store::SchemaTypeCode::XS_UNSIGNED_SHORT:
            case zorba::store::SchemaTypeCode::XS_UNSIGNED_BYTE:
            case zorba::store::SchemaTypeCode::XS_POSITIVE_INTEGER:
                return PyLong_FromUnsignedLongLong(item.getLongValue());
            default:
                return Py_None;
        }
    }
    else if (item.isNode()) {
        return Py_None;
    }
    else {
        void* d;
        dctx->getExternalFunctionParam("dctx", d);
        void* s;
        dctx->getExternalFunctionParam("sctx", s);
        PyZorbaItem* zorbaItem = new PyZorbaItem(item, (zorba::StaticContext*) s, (zorba::DynamicContext*) d);
        return PyCapsule_New(zorbaItem, nullptr, [](PyObject* object) {});
    }
}

PyObject* getItemSequenceAsPyObject(zorba::ItemSequence* sequence, const zorba::StaticContext* sctx, const zorba::DynamicContext* dctx)
{
    if (sequence == nullptr)
        return Py_None;
    zorba::Item item;
    std::vector<PyObject*> vector;
    zorba::Iterator_t iter = sequence->getIterator();
    iter->open();
    while (iter->next(item))
        vector.push_back(getItemAsPyObject(item, sctx, dctx));
    iter->close();
    if (vector.size() == 0)
        return Py_None;
    if (vector.size() == 1)
        return vector.at(0);
    PyObject* tuple = PyTuple_New((int) vector.size());
    int i = 0;
    for (std::vector<PyObject*>::iterator it = vector.begin(); it != vector.end(); it++, i++)
        PyTuple_SET_ITEM(tuple, i, *it);
    return tuple;
}

PyObject* getItemSequenceAsPyTuple(zorba::ItemSequence* sequence, const zorba::StaticContext* sctx, const zorba::DynamicContext* dctx)
{
    if (sequence == nullptr)
        return Py_None;
    zorba::Item item;
    std::vector<PyObject*> vector;
    zorba::Iterator_t iter = sequence->getIterator();
    iter->open();
    while (iter->next(item))
        vector.push_back(getItemAsPyObject(item, sctx, dctx));
    iter->close();
    PyObject* tuple = PyTuple_New((int) vector.size());
    int i = 0;
    for (std::vector<PyObject*>::iterator it = vector.begin(); it != vector.end(); it++, i++)
        PyTuple_SET_ITEM(tuple, i, *it);
    return tuple;
}

zorba::Item getPyObjectAsItem(PyObject* object, const zorba::StaticContext* sctx, const zorba::DynamicContext* dctx)
{
    if (object == nullptr || object == Py_None)
        return getItemFactory()->createJSONNull();
    if (PyUnicode_Check(object))
        if (PyUnicode_READY(object) == 0)
            return getItemFactory()->createString(zorba::String((char*) PyUnicode_DATA(object)));
        else
            return getItemFactory()->createString("");
    if (PyBool_Check(object))
        return getItemFactory()->createBoolean(PyObject_IsTrue(object));
    if (PyLong_Check(object))
        return getItemFactory()->createLong(PyLong_AsLongLong(object));
    if (PyFloat_Check(object))
        return getItemFactory()->createDouble(PyFloat_AsDouble(object));
    if (PyDict_Check(object)) {
        std::vector<std::pair<zorba::Item, zorba::Item>> pairs;
        PyObject *key, *value;
        Py_ssize_t pos = 0;
        while (PyDict_Next(object, &pos, &key, &value))
            pairs.push_back(std::pair<zorba::Item, zorba::Item>(getPyObjectAsItem(key, sctx, dctx), getPyObjectAsItem(value, sctx, dctx)));
        return getItemFactory()->createJSONObject(pairs);
    }
    if (PyTuple_Check(object)) {
        std::vector<zorba::Item> items;
        int size = PyTuple_Size(object);
        for (int i = 0; i < size; i++)
            items.push_back(getPyObjectAsItem(PyTuple_GetItem(object, i), sctx, dctx));
        return getItemFactory()->createJSONArray(items);
    }
    if (PyList_Check(object)) {
        std::vector<zorba::Item> items;
        int size = PyList_Size(object);
        for (int i = 0; i < size; i++)
            items.push_back(getPyObjectAsItem(PyList_GetItem(object, i), sctx, dctx));
        return getItemFactory()->createJSONArray(items);
    }

    std::string name = std::to_string((intptr_t) object);
    dctx->addExternalFunctionParameter(name, new PyObjectParameter(object, 0));
    return getItemFactory()->createAnyURI(name);
}

zorba::ItemSequence* getPyObjectAsItemSequence(PyObject* object, const zorba::StaticContext* sctx, const zorba::DynamicContext* dctx)
{
    if (object == nullptr || object == Py_None)
        return new zorba::EmptySequence();
    if (PyTuple_Check(object)) {
        std::vector<zorba::Item> items;
        int size = PyTuple_Size(object);
        for (int i = 0; i < size; i++)
            items.push_back(getPyObjectAsItem(PyTuple_GetItem(object, i), sctx, dctx));
        return new zorba::VectorItemSequence(items);
    }
    if (PyList_Check(object)) {
        std::vector<zorba::Item> items;
        int size = PyList_Size(object);
        for (int i = 0; i < size; i++)
            items.push_back(getPyObjectAsItem(PyList_GetItem(object, i), sctx, dctx));
        return new zorba::VectorItemSequence(items);
    }

    return new zorba::SingletonItemSequence(getPyObjectAsItem(object, sctx, dctx));
}

std::vector<zorba::ItemSequence_t> getPyObjectAsItemSequenceVector(PyObject* object, const zorba::StaticContext* sctx, const zorba::DynamicContext* dctx)
{
    std::vector<zorba::ItemSequence_t> result;
    if (object == nullptr || object == Py_None)
        return result;
    if (PyTuple_Check(object)) {
        int size = PyTuple_Size(object);
        for (int i = 0; i < size; i++)
            result.push_back(getPyObjectAsItemSequence(PyTuple_GetItem(object, i), sctx, dctx));
        return result;
    }
    else if (PyList_Check(object)) {
        int size = PyList_Size(object);
        for (int i = 0; i < size; i++)
            result.push_back(getPyObjectAsItemSequence(PyList_GetItem(object, i), sctx, dctx));
        return result;
    }
    else
        result.push_back(getPyObjectAsItemSequence(object, sctx, dctx));
    return result;
}

UnityZorbaPythonFunction::UnityZorbaPythonFunction(zorba::String uri, zorba::String localName, PyObject* callable) :
        uri(uri), localName(localName), callable(callable)
{
    Py_XINCREF(this->callable);
}

UnityZorbaPythonFunction::~UnityZorbaPythonFunction()
{
    Py_XDECREF(callable);
}

zorba::String UnityZorbaPythonFunction::getURI() const
{
    return uri;
}

zorba::String UnityZorbaPythonFunction::getLocalName() const
{
    return localName;
}

zorba::ItemSequence_t UnityZorbaPythonFunction::evaluate(const Arguments_t& args, const zorba::StaticContext* sctx,
        const zorba::DynamicContext* dctx) const
{

    //if (!PyCallable_Check(callable))
        //return nullptr;

    PyObject* py_args;
    PyObject* py_kwargs = nullptr;

    if (args.size() == 0) {
        py_args = PyTuple_New(0);
    }
    else {
        Py_ssize_t i = 0;
        py_args = PyTuple_New(args.size());
        int last = args.size() - 1;
        for (Arguments_t::const_iterator it = args.begin(); it != args.end(); it++, i++) {
            PyObject* py_arg = getItemSequenceAsPyObject(*it, sctx, dctx);
            if (i == last && PyDict_Check(py_arg)) {
                _PyTuple_Resize(&py_args, last);
                py_kwargs = py_arg;
            }
            else {
                PyTuple_SET_ITEM(py_args, i, py_arg);
            }
        }
    }

    PyObject* result = PyObject_Call(callable, py_args, py_kwargs);
    zorba::ItemSequence* res = getPyObjectAsItemSequence(result, sctx, dctx);
    //Py_XDECREF(py_args);
    //Py_XDECREF(result);

    if (PyErr_Occurred()) {
        zorba::String errNS(uri);
        zorba::String errName(localName);
        zorba::Item errQName = getItemFactory()->createQName(errNS, errName);
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

    return zorba::ItemSequence_t(res);
}

UnityZorbaPythonModule::UnityZorbaPythonModule(std::string uri, std::string name) :
        uri(uri)
{
    PyRun_SimpleString(("import " + name).c_str());
    module = PyImport_ImportModule(name.c_str());
}

UnityZorbaPythonModule::~UnityZorbaPythonModule()
{
    for (FuncMap_t::const_iterator lIter = functions.begin(); lIter != functions.end(); ++lIter)
        delete lIter->second;
    functions.clear();
    Py_XDECREF(module);
}

void UnityZorbaPythonModule::destroy()
{
    delete this;
}

zorba::String UnityZorbaPythonModule::getURI() const
{
    return uri;
}

zorba::ExternalFunction* UnityZorbaPythonModule::getExternalFunction(const zorba::String& localName)
{
    FuncMap_t::iterator iter = functions.find(localName);
    zorba::ExternalFunction*& function = functions[localName];

    if (iter == functions.end()) {
        std::string s = localName.str();
        std::replace(s.begin(), s.end(), '-', '_');
        PyObject* callable = PyObject_GetAttrString(module, s.c_str());
        if (callable)
            function = new UnityZorbaPythonFunction(uri, localName, callable);
        else
            function = NULL;
    }

    return function;
}

