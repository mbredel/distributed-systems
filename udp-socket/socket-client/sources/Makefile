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

docker:
	@docker build -t mbredel/udp-socket-client:latest -f ../docker/Dockerfile .
	@docker image prune --filter label=stage=build --force

docker-remove:
	@docker rmi mbredel/udp-socket-client:latest -f


.PHONY: all docker docker-remove
