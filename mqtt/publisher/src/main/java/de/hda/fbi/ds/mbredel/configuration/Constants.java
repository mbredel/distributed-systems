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
 * A container class that contains all
 * constants used within this application.
 *
 * @author Michael Bredel
 */
public abstract class Constants {

    /** The exit code if the procedure succeeded. */
    public static final int EXIT_CODE_SUCCESS = 0;
    /** The exit code of the procedure failed. */
    public static final int EXIT_CODE_ERROR = 1;
    /** The at-most-once QoS parameter of MQTT: */
    public static final int QOS_AT_MOST_ONCE = 0;
    /** The at-least-once QoS parameter of MQTT: */
    public static final int QOS_AT_LEAST_ONCE = 1;
    /** The exactly-once QoS parameter of MQTT: */
    public static final int QOS_EXACTLY_ONCE = 2;

}
