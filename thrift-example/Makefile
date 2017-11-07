##
## Makefile for thrift-examples and sub-modules.
##
## The Makefile exists because I am old and
## crotchety and my fingers can't stop from
## running make commands. Obviously, everting
## can be done running maven directly.
##

all: clean shared
	@mvn package 

clean:
	@mvn clean

gen-thrift:
	@mvn thrift:compile -am -pl shared

shared:
	@mvn thrift:compile -am -pl shared
	@mvn package -am -pl shared

server:
	@mvn package -am -pl server

client:
	@mvn package -am -pl client

.PHONY: all shared server client
