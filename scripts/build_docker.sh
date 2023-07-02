#!/bin/bash

# Run this script to build docker image locally
# ./build_docker.sh

scriptdir=$(dirname "$0")

# Load common code
source "$scriptdir"/common.sh

fn_say_wrn "**********************"
fn_say_wrn "Building docker images"
fn_say_wrn "**********************"
echo

docker_file="$scriptdir/../Dockerfile"

docker build -t "sample/javaspringboot-api" -f "$docker_file" "$scriptdir/.." \
	--build-arg BUILD_IMAGE="eclipse-temurin:20-jdk-alpine"\
	--build-arg RUNTIME_IMAGE="eclipse-temurin:20-jre-alpine"

fn_say_success "*******"
fn_say_success "Success"
fn_say_success "*******"