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
 * The actual socket server that creates
 * a UDP socket and waits for incoming
 * connections.
 *
 * @author Michael Bredel
 */
public class UDPSocketServer {

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(UDPSocketServer.class);
    /** The UDP port the server listens to. */
    private static int PORT = 3141;

    /** The UDP socket used to receive data. */
    private DatagramSocket udpSocket;
    /** States the server running. */
    private boolean running = true;
    /** A buffer array to store the datagram information. */
    private byte[] buf = new byte[1];

    /**
     * Default constructor that creates, i.e., opens
     * the socket.
     *
     * @throws IOException In case the socket cannot be created.
     */
    public UDPSocketServer() throws IOException {
        udpSocket = new DatagramSocket( PORT );
        LOGGER.info("Started the UDP socket server at port " + PORT);
    }

    /**
     * Continuously running method that receives the data
     * from the UDP socket and logs the datagram information.
     */
    public void run()  {
        while(running) {
            DatagramPacket udpPacket = new DatagramPacket(buf, buf.length);
            try {
                // Receive message
                udpSocket.receive(udpPacket);
                //Print some packet data.
                printPacketData(udpPacket);
            } catch (IOException e) {
                LOGGER.error("Could not receive datagram.\n" + e.getLocalizedMessage());
            }
        }
    }

    /**
     * Extracts some data of a given datagram packet
     * and prints the result to standard out.
     *
     * @param udpPacket The datagram packet to extract and print.
     */
    private void printPacketData(DatagramPacket udpPacket) {
        // Get IP address and port.
        InetAddress address = udpPacket.getAddress();
        int port = udpPacket.getPort();
        // Get packet length
        int length = udpPacket.getLength();
        // Get the payload.
        String payload = new String(udpPacket.getData());
        // Print the packet information.
        System.out.println("Received a packet: IP:Port: " + address + ":" + port + ", length: " + length + ", payload: " + payload);
    }
}