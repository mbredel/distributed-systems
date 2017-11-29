##
## This image acts as the base image to the son-resolver.
## It contains an OpenJDK Java Runtine Environment and all
## the dependencies needed by son-resolver.
##
## The base image is created by Maven. It automatically
## copies the dependecies as specified in the POM file
## to the /app directory. Thus, whenever the dependencies
## change, the base image (and all child images) needs
## to be re-build.
##
FROM openjdk:8-jre-alpine
LABEL maintainer michael@bredel.at
RUN mkdir -p /app
COPY ./*.jar /app/
