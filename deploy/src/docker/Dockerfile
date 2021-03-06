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

FROM maven:3.6-jdk-11

RUN apt-get update -qq && \
	apt-get install -yqq \
		sudo \
		software-properties-common

RUN curl -sL https://deb.nodesource.com/setup_10.x | bash -

RUN useradd -m docker && echo "docker:docker" | chpasswd && adduser docker sudo
RUN curl -fsSL https://download.docker.com/linux/$(. /etc/os-release; echo "$ID")/gpg | apt-key add -
RUN add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/$(. /etc/os-release; echo "$ID") $(lsb_release -cs) stable"

RUN apt-get update -qq  && \
	apt-get purge "flex" && \
	apt-get purge "libboost*" && \
	apt-get purge "libfl-dev" && \
	apt-get purge "libmagick*" && \
	apt-get purge "texlive*" && \
	apt-get install -yqq \
		apt-transport-https \
		build-essential \
		ca-certificates \
		cmake \
		curl \
		docker-ce \
		doxygen \
		gcc \
		gfortran \
		gnupg2 \
		graphviz \
		imagemagick \
		libarchive-dev \
		libatlas3-base \
		libblas-dev \
		libblas3 \
		libboost-filesystem-dev \
		libcurl4-openssl-dev \
		libedit-dev \
		libfontbox-java \
		libfop-java \
		libgeos++-dev \
		libgfortran3 \
		libgmp-dev \
		libgmp10 \
		libjempbox-java \
		liblapack-dev \
		liblapack3 \
		libmagick++-dev \
		libmagickwand-dev \
		libpdfbox-java \
		libslicot0 \
		libsqlite3-dev \
		libtidy-dev \
		libxerces-c-dev \
		libxml2 \
		libxml2-dev \
		libxslt-dev \
		libz-dev \
		llvm \
		llvm-dev \
		nodejs \
		m4 \
		sqlite3 \
		swig \
		uuid \
		uuid-dev \
		wget \
		zlib1g \
		zlib1g-dev --install-recommends && \
	rm -rf /var/lib/apt/lists/*

RUN wget -q http://launchpadlibrarian.net/160037457/bison_3.0.2.dfsg-2_amd64.deb
RUN wget -q http://launchpadlibrarian.net/160037458/libbison-dev_3.0.2.dfsg-2_amd64.deb
RUN dpkg -i libbison-dev_3.0.2.dfsg-2_amd64.deb bison_3.0.2.dfsg-2_amd64.deb
RUN wget -q https://launchpad.net/ubuntu/+source/flex/2.5.39-8/+build/6118519/+files/libfl-dev_2.5.39-8_amd64.deb
RUN wget -q https://launchpad.net/ubuntu/+source/flex/2.5.39-8/+build/6118519/+files/flex_2.5.39-8_amd64.deb
RUN dpkg -i libfl-dev_2.5.39-8_amd64.deb flex_2.5.39-8_amd64.deb

RUN rm -r /usr/include/c++/6/tr1
 
RUN /usr/bin/npm install -g @angular/cli

RUN wget http://www.openssl.org/source/openssl-1.0.2t.tar.gz
RUN tar xvf openssl-1.0.2t.tar.gz
WORKDIR "/openssl-1.0.2t"
RUN ./config --prefix=/usr/local --openssldir=/usr/local shared zlib
RUN make -j8
RUN make install

WORKDIR "/"

RUN wget -q https://www.python.org/ftp/python/3.7.5/Python-3.7.5.tgz
RUN tar xvf Python-3.7.5.tgz
WORKDIR "/Python-3.7.5"
RUN ./configure --enable-optimizations --enable-shared --with-ensurepip=install --prefix=/usr/local LDFLAGS="-Wl,-rpath /usr/local/lib"
RUN make -j8
RUN make install

RUN pip3 install casadi
RUN pip3 install jupyter
RUN pip3 install jupyterlab
RUN pip3 install matplotlib
RUN pip3 install nlopt
RUN pip3 install numpy
RUN pip3 install pandas
RUN pip3 install plyvel
RUN pip3 install pygmo
RUN pip3 install pyomo
RUN pip3 install seaborn
RUN pip3 install sphinx
RUN pip3 install sphinx-tabs
RUN pip3 install sphinx_rtd_theme
RUN pip3 install sympy
RUN pip3 install tensorflow

RUN wget -q https://github.com/madebr/pyOpt/archive/master.zip && \
    unzip master.zip && \
    rm master.zip && \
    cd pyOpt-master && \
    python3 setup.py install

ENV PATH="/opt/dgms/bin:${PATH}:/root/bin"
ENV LD_LIBRARY_PATH="/opt/dgms/lib:${LD_LIBRARY_PATH}:/root/lib"

ENV DGMS_HOME="/opt/dgms"
ENV DGMS_SETTINGS="/root/.dgms"
ENV DGMS_WORKSPACE="/root/workspace"
ENV DGMS_PACKAGES="/root/packages"
ENV DGMS_BIN="/root/bin"
ENV DGMS_LIB="/root/lib"

VOLUME "/root/.dgms"
VOLUME "/root/workspace"
VOLUME "/root/packages"
VOLUME "/root/bin"
VOLUME "/root/lib"
VOLUME "/tmp"

WORKDIR "/root/workspace"

EXPOSE 8888
EXPOSE 8080

ENTRYPOINT [ "java", "--add-opens", "java.base/java.lang=ALL-UNNAMED", "-Xrs", "-Xms1024m", "-jar", "/opt/dgms/lib/unity-cli-${project.version}.jar" ]

ADD . /opt/dgms
