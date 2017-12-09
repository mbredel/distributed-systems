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

import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

/**
 * The main class that contains the
 * main method that starts the server.
 *
 * @author Michael Bredel
 */
public class Main {

    /** The port the server listens to. */
    public static final int PORT = 9090;

    /**
     * Start a simple Thrift server.
     *
     * @param processor The handler that handles incoming messages.
     */
    public static void startSimpleServer(Calc.Processor<ServerHandler> processor) {
        try {
            TServerTransport serverTransport = new TServerSocket(PORT);
            TServer server = new TSimpleServer(new TServer.Args(serverTransport).processor(processor));
            System.out.println("Starting the simple server...");
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * The main method that actually starts the program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        startSimpleServer(new Calc.Processor<>(new ServerHandler()));
    }
}
