# A UDP Socket Client 

A quite simple client application that sends a _Hello World_ message to a UDP Socket Server.

## Compile

The application is written in Java leveraging the Maven build tool. Thus, it is quite easy to compile the application. All you need is a Java 8 JDK and Maven installed on your system. You can then built the whole system by typing:

```
 $ mvn clean package
```

## Usage

Once the system is build, you may start the server by using the bash script:

```
 $ ./start-udp-socket-client.sh
```

The client just sends a single message to a local UDP Socket Server and terminates.
