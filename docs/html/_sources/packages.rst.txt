Package and dependency management
=================================

**Unity DGMS** adopts the CommonJS package format for the modular
development, configuration, and distribution of DG applications and
libraries, which can then be published to or installed from the `npm
registry`_ or any Git repository.

To initialize a new package for **Unity DGMS**, simply use ``npm init``
or ``yarn init``

1. From the terminal, set the current working directory to the directory
   that will serve as the root directory of the package.

   .. code:: bash

      cd /path/to/package

2. Run the following command:

   .. code:: bash

      npm init

This will generate a ``package.json`` file in the root directory of the
package, similar to the following:

.. code:: json

   {
       "name": "<name>",
       "version": "<version>",
       "description": "<description>",
       "main": "<main>",
       "scripts": {
           "test": "echo \"Error: no test specified\" && exit 1"
       },
       "author": "<author>",
       "license": "<license>"
   }

To install a dependency from the npm registry, in the root directory of
the package, run the following command, replacing ``<dependency>`` with
the name of the dependency from the npm registry to install:

.. code:: bash

   npm install <dependency>

To publish a package to the npm registry, run the following command from
the root directory of the package:

.. code:: bash

   npm publish

.. _npm registry: https://www.npmjs.com/
