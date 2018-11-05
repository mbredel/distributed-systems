# A TCP Socket Client 

A quite simple client application that sends a _Hello World_ message to a TCP Socket Server.

## Compile

The application is written in Java leveraging the Maven build tool. Thus, it is quite easy to compile the application. All you need is a Java 8 JDK and Maven installed on your system. You can then built the whole system by typing:

```
 $ mvn clean package
```

in the ./sources/ directory.

## Usage

Once the system is build, you may start the client by using the bash script:

```
 $ ./socket-client.sh
```

The client just sends a single message to a local TCP Socket Server and terminates.
