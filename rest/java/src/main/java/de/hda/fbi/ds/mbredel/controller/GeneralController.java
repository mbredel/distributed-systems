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
