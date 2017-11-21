#!/bin/bash

##
## Starts the Thrift server that
## listens to incoming messages.
##

CMD="java -jar"
BASE_DIR=`dirname $0`
JAR="${BASE_DIR}/../../../target/thrift-example-server-0.1-SNAPSHOT.jar"

${CMD} ${JAR} $@