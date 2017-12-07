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

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * The main class that contains the
 * main method that starts the client.
 *
 * @author Michael Bredel
 */

public class Main {

    /** The port the client connects to. */
    public static final int PORT = 9090;
    /** The host the client connects to. */
    public static final String HOST = "localhost";

    /**
     * The main method that actually starts the program.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {

        try (TTransport transport = new TSocket(HOST, PORT)){
            transport.open();
            TProtocol protocol = new TBinaryProtocol(transport);
            Calc.Client client = new Calc.Client(protocol);
            System.out.println("add result:" + client.addTwo(100, 200));
        } catch (TException x) {
            x.printStackTrace();
        }
        
    }

}
