[![Quality Status](https://sonarcloud.io/api/badges/gate?key=de.hda.fbi.ds.mbredel:socket-server)](https://sonarcloud.io/dashboard?id=de.hda.fbi.ds.mbredel%3Asocket-server)

# A UDP Socket Server

A quite simple sever application that listens to a UDP socket and prints out received messages.

## Compile

The application is written in Java leveraging the Maven build tool. Thus, it is quite easy to compile the application. All you need is a Java 8 JDK and Maven installed on your system. You can then built the whole system by typing

```
 $ mvn clean package
```

in the ./sources/ directory.

## Usage

Once the system is build, you may start the server by using the bash script, which is located at ./source/src/main/bash/:

```
 $ ./socket-server.sh
```

The server runs forever. You may terminate the server by pressing _Ctrl + x_. For more details call

```
 $ ./socket-server.sh --help
```
