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

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;

/**
 * The actual socket client that creates
 * a UDP socket and sends the data.
 *
 * @author Michael Bredel
 */
public class TCPSocketClient {

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(TCPSocketClient.class);
    /** The UDP port the client connects to. */
    private static int PORT = 3142;

    /** The TCP client socket used to send data. */
    private Socket clientSocket;
    /** The IP address the client connects to. */
    private InetAddress address;

    /**
     * Default constructor that creates, i.e., opens
     * the socket.
     *
     * @throws IOException In case the socket cannot be created.
     */
    public TCPSocketClient() throws IOException {
        address = InetAddress.getByName("localhost");
        clientSocket = new Socket(address, PORT);
        LOGGER.info("Started the TCP socket that connects to " + address.getHostAddress());
    }

    /**
     * Method that transmits a String message
     * via the UDP socket.
     *
     * @param msg The String message to transmit.
     */
    public void sendMsg(String msg)  {
        // Send the data.
        try {
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
            outToServer.writeChars(msg + "\n");
            LOGGER.debug("Message sent with payload: " + msg);
        } catch (IOException e) {
            LOGGER.error("Could not send data.\n" + e.getLocalizedMessage());
        }
    }

}