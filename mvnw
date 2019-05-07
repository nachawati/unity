#!/usr/bin/env bash

if [[ "$(docker images -q unity-build:latest 2> /dev/null)" == "" ]]; then

	docker build -t unity-build:latest \
		-f ${0%/*}/deploy/src/docker/Dockerfile.build ${0%/*}/deploy/src/docker

fi

if [ $# == 0 ]; then

	docker build -t unity-build:latest \
		-f ${0%/*}/deploy/src/docker/Dockerfile.build ${0%/*}/deploy/src/docker

else

	docker run -it --rm \
		-v /var/run/docker.sock:/var/run/docker.sock \
		-v "$(cd "$(dirname "${0%/*}")"; pwd)/$(basename "${0%/*}"):/usr/src/unity" \
		-v "/home/${SUDO_USER:-$USER}/.m2:/var/maven/.m2" \
		-e DOCKER_HOST=unix:///var/run/docker.sock \
		-e MAVEN_CONFIG=/var/maven/.m2 \
		unity-build:latest \
		mvn -Duser.home=/var/maven "$@"

fi

chown -R ${SUDO_UID:-$UID}:${SUDO_GID:-$GID} ${0%/*}
chown -R ${SUDO_UID:-$UID}:${SUDO_GID:-$GID} /home/${SUDO_USER:-$USER}/.m2
