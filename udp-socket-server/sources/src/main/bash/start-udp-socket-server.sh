#!/bin/bash

##
## Starts the UDP socket server
## and waits for incoming connections.
##

CMD="java -jar"
BASE_DIR=`dirname $0`
JAR="${BASE_DIR}/../../../target/udp-socket-server-1.0-SNAPSHOT.jar"

${CMD} ${JAR} $@