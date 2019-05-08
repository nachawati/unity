Installation
============

The primary way to install **Unity DGMS** is through the pre-built
Docker image ``dgms/dgms`` that is hosted on `DockerHub`_. To simplify
the use of the Docker image with local files, it is recommended to
install the ``dgms`` package from the `npm registry`_, which adds the
system command ``dgms`` for running **Unity DGMS** from the terminal.

Prerequisites
-------------

Please make sure that you have Docker and Node.js installed on your
system.

-  Download `Docker`_
-  Download `Node.js`_

   |

Installing Unity DGMS
---------------------

You can install **Unity DGMS** using the Node.js package manager ``npm``
or ``yarn``:

.. code:: bash

   npm install dgms --global

You can test the installation by running:

.. code:: bash

   dgms run --query 1+1

If successful, this should write ``2`` to the standard output.

.. _DockerHub: https://hub.docker.com/r/dgms/dgms
.. _npm registry: https://www.npmjs.com/package/dgms
.. _Docker: https://www.docker.com/get-started
.. _Node.js: https://nodejs.org
