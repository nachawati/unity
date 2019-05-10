Namespace resolution
====================

**Unity DGMS** resolves references to external JSONiq modules and JSON
documents within a single package based on the standard `Zorba URI
resolution scheme`_. For example, the following URL:

::

   http://dgms.io/modules/mymodule

is transformed into the package-relative path:

::

   lib/io/dgms/modules/mymodule.jq

If no file exists at that location in the main package, **Unity DGMS**
will systematically look for a file that matches the package-relative
path in the closure of all dependencies of the main package.

.. _Zorba URI resolution scheme: http://www.zorba.io/documentation/3.0/zorba/architecture/uriresolvers
