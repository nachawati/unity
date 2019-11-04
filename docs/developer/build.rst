Build from source
=================

A clean build of **Unity DGMS** is performed in two steps:

**Step 1:** Clean project and build dependencies

.. code:: bash

   ./mvnw clean install -P linux-gcc-amd64-dependencies

**Step 2:** Build project

.. code:: bash

   ./mvnw install

This will build a binary distribution of **Unity DGMS** with all core
dependencies inside the ``./target/linux-gcc-amd64`` folder. You
can test the build by running:

.. code:: bash

   cd ./target/linux-gcc-amd64
   java -Xrs -Xms1024m -jar ./lib/unity-cli-<VERSION>.jar run 1+1
