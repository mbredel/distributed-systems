#!/bin/bash

##
## Starts the UDP socket client
## and transmits a message.
##

CMD="java -jar"
BASE_DIR=`dirname $0`
JAR="${BASE_DIR}/../../../target/socket-client-1.0-SNAPSHOT.jar"

${CMD} ${JAR} $@