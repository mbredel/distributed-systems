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
package de.hda.fbi.ds.mbredel.controller;

import spark.Request;
import spark.Response;

import javax.inject.Inject;

/**
 * @author Michael Bredel
 */
public class GeneralController {

    /**
     * The default constructor used by Guice. Since
     * the constructor is package-private GeneralController
     * objects can (and should) only be instantiated
     * using the GeneralController factory.
     */
    @Inject
    GeneralController() {
        // Do nothing.
    }

    /**
     * Just return an empty HTML page.
     *
     * @param req The HTTP request information.
     * @param res The HTTP response information.
     * @return The index.html.
     */
    public String get(Request req, Response res) {
        return "<html><body></body></html>";
    }

    /**
     * Just return an empty body.
     *
     * @param req The HTTP request information.
     * @param res The HTTP response information.
     * @return An empty body.
     */
    public String head(Request req, Response res) {
        return "";
    }

    /**
     * Just reflect the request message.
     *
     * @param req The HTTP request information.
     * @param res The HTTP response information.
     * @return The reflected request message.
     */
    public String trace(Request req, Response res) {
        return req.body();
    }

}
