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

import java.util.NoSuchElementException;

/**
 * A container class that contains all the
 * CLI parameters.
 *
 * @author Michael Bredel
 */
public class CliParameters {
    /** The one and only instance of CLI parameters. */
    private static CliParameters instance;

    /** Command line option: URI to the configuration. */
    private String uri;
    /** Command line option: Username to access configuration. */
    private String username;
    /** Command line option: Password to access configuration. */
    private String password;

    /**
     * The static getter for the CLI parameter instance.
     *
     * @return The CLI parameter instance.
     */
    public static CliParameters getInstance() {
        if (instance == null)
            instance = new CliParameters();
        return instance;
    }

    public String getUri() throws NoSuchElementException {
        if (uri != null)
            return uri;
        else
            throw new NoSuchElementException("Configuration URI not set.");
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getUsername() throws NoSuchElementException {
        if (username != null)
            return username;
        else
            throw new NoSuchElementException("Username not set.");
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() throws NoSuchElementException {
        if (password != null)
            return password;
        else
            throw new NoSuchElementException("Password not set.");
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("cliParameters=[");
        sb.append("uri=" + uri + ",");
        sb.append("username=" + username + ",");
        sb.append("password=" + password);
        sb.append("]");
        return sb.toString();
    }

    /**
     * A private constructor to avoid
     * instantiation.
     */
    private CliParameters() {}
}
