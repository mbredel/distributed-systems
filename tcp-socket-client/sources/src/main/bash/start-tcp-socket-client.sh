#!/bin/bash

##
## Starts the TCP socket client
## and transmits a message.
##

CMD="java -jar"
BASE_DIR=`dirname $0`
JAR="${BASE_DIR}/../../../target/tcp-socket-client-1.0-SNAPSHOT.jar"

${CMD} ${JAR} $@