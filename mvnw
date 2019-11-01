#!/usr/bin/env bash

if [ $# == 0 ]; then

	docker build -t nachawati/unity-build:0.1.0 \
		-f ${0%/*}/deploy/src/docker/Dockerfile.build ${0%/*}/deploy/src/docker

else

	if [[ "$(docker images -q unity-build:latest 2> /dev/null)" == "" ]]; then
	
		docker build -t nachawati/unity-build:0.1.0 \
			-f ${0%/*}/deploy/src/docker/Dockerfile.build ${0%/*}/deploy/src/docker
	
	fi

	docker run -it --rm \
		-v /var/run/docker.sock:/var/run/docker.sock \
		-v "$(cd "$(dirname "${0%/*}")"; pwd)/$(basename "${0%/*}"):/usr/src/unity" \
		-v "/home/${SUDO_USER:-$USER}/.m2:/var/maven/.m2" \
		-v "/home/${SUDO_USER:-$USER}/.npmrc:/root/.npmrc" \
		-v "/usr/lib/node_modules:/usr/lib/node_modules" \
		-v "/home/${SUDO_USER:-$USER}/.docker:/root/.docker" \
		-e DOCKER_HOST=unix:///var/run/docker.sock \
		-e MAVEN_CONFIG=/var/maven/.m2 \
		nachawati/unity-build:0.1.0 \
		mvn -Duser.home=/var/maven "$@"

fi

if [[ -v SUDO_UID ]]; then
    chown -R ${SUDO_UID:-$UID}:${SUDO_GID:-$GID} ${0%/*}
	chown -R ${SUDO_UID:-$UID}:${SUDO_GID:-$GID} /home/${SUDO_USER:-$USER}/.m2
fi
