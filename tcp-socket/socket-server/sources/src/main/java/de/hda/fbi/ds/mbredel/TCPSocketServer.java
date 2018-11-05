/*
 Copyright (c) 2017, Michael Bredel, H-DA
 ALL RIGHTS RESERVED.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

 Neither the name of the H-DA and Michael Bredel
 nor the names of its contributors may be used to endorse or promote
 products derived from this software without specific prior written
 permission.
 */
package de.hda.fbi.ds.mbredel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * The actual socket server that creates
 * a TCP socket and waits for incoming
 * connections.
 *
 * @author Michael Bredel
 */
public class TCPSocketServer {

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TCPSocketServer.class);
    /** The TCP port the server listens to. */
    private static int PORT = 3142;

    /** The TCP server socket used to receive data. */
    private ServerSocket tcpServerSocket;
    /** States the server running. */
    private boolean running = true;

    /**
     * Default constructor that creates, i.e., opens
     * the socket.
     *
     * @throws IOException In case the socket cannot be created.
     */
    public TCPSocketServer() throws IOException {
        tcpServerSocket = new ServerSocket( PORT );
        LOGGER.info("Started the TCP socket server at port " + PORT);
    }

    /**
     * Continuously running method that receives the data
     * from the TCP socket and logs the information.
     */
    public void run()  {
        while(running) {
            Socket connectionSocket = null;
            try {
                // Open a connection socket, once a client connects to the server socket.
                connectionSocket = tcpServerSocket.accept();
                // Get the continuous input stream from the connection socket.
                BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
                // Print the input stream.
                printInputStream(inFromClient);
            } catch (IOException e) {
                LOGGER.error("Could not receive datagram.\n" + e.getLocalizedMessage());
            } finally {
                if (connectionSocket != null) {
                    try {
                        connectionSocket.close();
                        LOGGER.debug("Connection socket closed.");
                    } catch (IOException e) {
                        // Do nothing.
                    }
                }
            }
        }
    }

    /**
     * Extracts some data of a given data stream
     * and prints the result to standard out.
     *
     * @param bufferedReader The buffered input stream from the client.
     */
    private void printInputStream(BufferedReader bufferedReader) {
        try {
            // Read a text-line form the buffer.
            String streamLine = bufferedReader.readLine();
            // Print the packet information.
            System.out.println("Received some information: " + streamLine);
        } catch (IOException e) {
            LOGGER.error("Could not read from buffered reader.");
        }
    }
}