/*
 Copyright (c) 2018, Michael Bredel, H-DA
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

import de.hda.fbi.ds.mbredel.configuration.CliParameters;
import de.hda.fbi.ds.mbredel.configuration.Defaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.*;

/**
 * The actual socket server that creates
 * a UDP socket and waits for incoming
 * datagram.
 *
 * @author Michael Bredel
 */
public class UDPSocketClient {

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(UDPSocketClient.class);

    /** The IP address the client connects to. */
    private InetAddress address;

    /**
     * Default constructor that initializes Internet
     * address the client connects to.
     */
    public UDPSocketClient() {
        // Try to set the destination host address.
        try {
            address = InetAddress.getByName(CliParameters.getInstance().getDestination());
        } catch (UnknownHostException e) {
            LOGGER.error("Can not parse the destination host address.\n{}", e.getMessage());
            System.exit(Defaults.EXIT_CODE_ERROR);
        }
    }

    /**
     * Method that transmits a String message via the UDP socket.
     *
     * This method is used to demonstrate the usage of datagram sockets
     * in Java. To this end, uses a try-with-resources statement that
     * closes the socket in any case of error.
     *
     * @see <a href="https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html">The try-with-resources Statement</a>
     *
     * @param msg The String message to transmit.
     */
    public void sendMsg(String msg) {

        // Create the UDP datagram socket.
        try (DatagramSocket udpSocket = new DatagramSocket()) {
            LOGGER.info("Started the UDP socket that connects to {}.", address.getHostAddress());

            // Convert the message into a byte-array.
            byte[] buf = msg.getBytes();
            // Create a new UDP packet with the byte-array as payload.
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address, CliParameters.getInstance().getPort());

            // Send the data.
            udpSocket.send(packet);
            LOGGER.info("Message sent with payload: {}", msg);
        } catch (SocketException e) {
            LOGGER.error("Could not start the UDP socket server.\n{}", e.getLocalizedMessage());
        } catch (IOException e) {
            LOGGER.error("Could not send data.\n{}", e);
        }
    }

}