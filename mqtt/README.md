# An MQTT example

A quite simple MQTT application comprising a publisher and a subsriber. The _subscriber_ subscribes to a topic at an MQTT message broker and listens to incoming messages on a specific topic. The _publisher_ publishes messages to an MQTT message broker on a specific topic such that it can be received by (all) the subscriber.

## Compile

The application is written in Java leveraging the Maven build tool. Thus, it is easy to compile the application. All you need is a Java 8 JDK and Maven installed on your system. You may compile the _publisher_ and the _subscriber_ independently by calling

```
 $ mvn clean package
```

in the respective directories.

## Usage

Once the system is build, you may start the subscriber by using the bash script, which is located at ./source/src/main/bash/:

```
 $ ./mqtt-subscriber.sh
```

The subscriber runs forever. You may terminate the server by pressing _Ctrl + x_.


You may start the publisher by

```
 $ ./mqtt-publisher.sh
```
