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

FROM openjdk:8-jdk

RUN curl -sL https://deb.nodesource.com/setup_10.x | bash -

RUN apt-get update -qq && \
    apt-get install -yqq \
		apt-transport-https \
		build-essential \
		ca-certificates \
		cmake \
		gcc \
		libgmp10 \
		libxerces-c-dev \
		libxml2 \
		libxslt1.1 \
		libz-dev \
		llvm \
		nodejs \
		python3 \
		python3-dev \
		python3-pip \
		python3-wheel \
		swig \
		wget \
		zlib1g && \
	rm -rf /var/lib/apt/lists/*

RUN wget -q https://github.com/oracle/graal/releases/download/vm-1.0.0-rc13/graalvm-ce-1.0.0-rc13-linux-amd64.tar.gz && \
    tar -xvzf graalvm-ce-1.0.0-rc13-linux-amd64.tar.gz && \
    rm graalvm-ce-1.0.0-rc13-linux-amd64.tar.gz

ENV PATH=/graalvm-ce-1.0.0-rc13/bin:$PATH
ENV JAVA_HOME=/graalvm-ce-1.0.0-rc13

RUN pip3 install casadi
RUN pip3 install jupyter
RUN pip3 install jupyterlab
RUN pip3 install matplotlib
RUN pip3 install pygmo
RUN pip3 install pyomo
RUN pip3 install nlopt
RUN pip3 install numpy
RUN pip3 install tensorflow
RUN pip3 install pandas
RUN pip3 install seaborn
RUN pip3 install sympy

RUN apt-get update -qq && \
	apt-get install -yqq \
		apt-transport-https \
		build-essential \
		ca-certificates \
		cmake \
		curl \
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
		python3 \
		python3-dev \
		python3-pip \
		python3-wheel \
		software-properties-common \
		swig \
		uuid \
		uuid-dev \
		wget \
		zlib1g \
		zlib1g-dev --install-recommends && \
	rm -rf /var/lib/apt/lists/*

RUN wget -q https://github.com/madebr/pyOpt/archive/master.zip && \
    unzip master.zip && \
    rm master.zip && \
    cd pyOpt-master && \
    python3 setup.py install

RUN pip3 install plyvel

ENV PATH="/opt/unity/bin:${PATH}:/root/bin"
ENV LD_LIBRARY_PATH="/opt/unity/lib:${LD_LIBRARY_PATH}:/root/lib"

ENV DGMS_HOME="/opt/unity"
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

ENTRYPOINT [ "java", "-Xrs", "-Xms1024m", "-jar", "/opt/unity/lib/unity-cli-${project.version}.jar" ]

ADD . /opt/unity
