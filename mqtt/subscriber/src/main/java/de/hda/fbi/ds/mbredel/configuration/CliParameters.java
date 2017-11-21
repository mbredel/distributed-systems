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
package de.hda.fbi.ds.mbredel.configuration;

/**
 * A container class that contains all the
 * CLI parameters.
 *
 * @author Michael Bredel
 */
public class CliParameters {

    /** The one and only instance of CLI parameters. */
    private static CliParameters instance;

    /** The address of the broker. */
    private String brokerAddress = "iot.eclipse.org";
    /** The port of the broker. */
    private String brokerPort = "1883";
    /** The port of the protocol. */
    private String brokerProtocol = "tcp";
    /** The topic the MQTT client subscribes to. */
    private String topic = "hda/mbredel/ds";

    /**
     * The static getter for the CLI parameters instance.
     *
     * @return The CLI parameters instance.
     */
    public static CliParameters getInstance() {
        if (instance == null)
            instance = new CliParameters();
        return instance;
    }

    //
    // Getter and Setter
    //

    public String getBrokerAddress() {
        return this.brokerAddress;
    }

    public void setBrokerAddress(String brokerAddress) {
        this.brokerAddress = brokerAddress;
    }

    public String getBrokerPort() {
        return this.brokerPort;
    }

    public void setBrokerPort(String brokerPort) {
        this.brokerPort = brokerPort;
    }

    public String getBrokerProtocol() {
        return this.brokerProtocol;
    }

    public void setBrokerProtocol(String brokerProtocol) {
        this.brokerProtocol = brokerProtocol;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * A private constructor to avoid
     * instantiation.
     */
    private CliParameters() {}
}
