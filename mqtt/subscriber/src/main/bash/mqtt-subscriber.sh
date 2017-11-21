#!/bin/bash

##
## Starts the Thrift client that
## connects to the Thrift server.
##

CMD="java -jar"
BASE_DIR=`dirname $0`
JAR="${BASE_DIR}/../../../target/mqtt-subscriber-1.0-SNAPSHOT.jar"

${CMD} ${JAR} $@