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
package de.hda.fbi.ds.mbredel.configuration;

/**
 * A container class that contains all
 * default values used within this application.
 *
 * @author Michael Bredel
 */
public abstract class Defaults {

    /** The default port of the UDP socket server. */
    public static final int PORT = 6543;
    /** The exit code if the procedure succeeded. */
    public static final int EXIT_CODE_SUCCESS = 0;
    /** The exit code of the procedure failed. */
    public static final int EXIT_CODE_ERROR = 1;

    /** The default message sent to the server. */
    public static final String MESSAGE = "Hello World";
    /** The default host to connect to. */
    public static final String DST_HOST = "localhost";

    /**
     * Private constructor to hide the implicit public one
     * and to void any instantiation.
     */
    private Defaults() {
        throw new IllegalStateException("Utility class");
    }

}
