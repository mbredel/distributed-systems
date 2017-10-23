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

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * The actual socket client that creates
 * a UDP socket and sends the data.
 *
 * @author Michael Bredel
 */
public class UDPSocketClient {

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(UDPSocketClient.class);
    /** The UDP port the client connects to. */
    private static int PORT = 3141;

    /** The UDP socket used to send data. */
    private DatagramSocket udpSocket;
    /** The IP address the client connects to. */
    private InetAddress address;
    /** A buffer array to store the datagram information. */
    private byte[] buf = new byte[256];

    /**
     * Default constructor that creates, i.e., opens
     * the socket.
     *
     * @throws IOException In case the socket cannot be created.
     */
    public UDPSocketClient() throws IOException {
        address = InetAddress.getByName("localhost");
        udpSocket = new DatagramSocket();
        LOGGER.info("Started the UDP socket that connects to " + address.getHostAddress());
    }

    /**
     * Method that transmits a String message
     * via the UDP socket.
     *
     * @param msg The String message to transmit.
     */
    public void sendMsg(String msg)  {
        // Convert the message into a byte-array.
        buf = msg.getBytes();
        // Create a new UDP packet with the byte-array as payload.
        DatagramPacket packet  = new DatagramPacket(buf, buf.length, address, PORT);
        // Send the data.
        try {
            udpSocket.send(packet);
            LOGGER.debug("Message sent with payload: " + msg);
        } catch (IOException e) {
            LOGGER.error("Could not send data.\n" + e.getLocalizedMessage());
        }
    }

}
