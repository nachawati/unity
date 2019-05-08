<div align="center"><b>&#1576;&#1587;&#1605; &#1575;&#1604;&#1604;&#1607; &#1575;&#1604;&#1585;&#1581;&#1605;&#1606; &#1575;&#1604;&#1585;&#1581;&#1610;&#1605;</b></div>
<div align="center">In the name of Allah, the Most Compassionate, the Most Merciful</div>

# Unity DGMS

[![Docker 0.1.0](https://img.shields.io/badge/docker-0.1.0-blue.svg?logo=docker)](https://hub.docker.com/r/dgms/dgms)
[![npm 0.1.0](https://img.shields.io/badge/npm-0.1.0-red.svg?logo=npm)](https://www.npmjs.com/package/dgms)

**Unity DGMS** is an open-source JSONiq analytics run-time environment for building model-driven decision guidance applications.

* **Website (and documentation):** <https://www.dgms.io/>
* **Source:** <https://github.com/nachawati/unity>
* **Bug reports:** <https://github.com/nachawati/unity/issues>

## Installation

The primary way to install **Unity DGMS** is through the pre-built Docker image ``dgms/dgms`` that is hosted on [DockerHub](https://hub.docker.com/r/dgms/dgms). To simplify the use of the Docker image with local files, it is recommended to install the ``dgms`` package from the [npm registry](https://www.npmjs.com/package/dgms), which adds the system command ``dgms`` for running **Unity DGMS** from the terminal.

### Prerequisites

Please make sure that you have Docker and Node.js installed on your system.

* Download [Docker](https://www.docker.com/get-started)
* Download [Node.js](https://nodejs.org)

### Installing **Unity DGMS**

You can install **Unity DGMS** using the Node.js package manager ``npm`` or ``yarn``:

```bash
npm install dgms --global
```

You can test the installation by running:

```bash
dgms run --query 1+1
```

If successful, this should write ``2`` to the standard output.
