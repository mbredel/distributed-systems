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

import de.hda.fbi.ds.mbredel.configuration.CliProcessor;
import de.hda.fbi.ds.mbredel.mqtt.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The main class that contains the
 * main method that starts the client.
 *
 * @author Michael Bredel
 */

public class Main {

    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    /**
     * The main method that starts the
     * whole client. Thus, it creates
     * a MQTT client and publishes
     * a string.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {

        // Parse the command line.
        CliProcessor.getInstance().parseCliOptions(args);

        // Start the MQTT subscriber.
        Publisher publisher = new Publisher();
        publisher.run();
    }

}