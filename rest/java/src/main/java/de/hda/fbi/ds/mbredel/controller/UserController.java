package de.hda.fbi.ds.mbredel.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.hda.fbi.ds.mbredel.configuration.ConfigurationService;
import de.hda.fbi.ds.mbredel.model.User;
import de.hda.fbi.ds.mbredel.store.StoreService;
import org.apache.http.HttpStatus;
import spark.Request;
import spark.Response;

import javax.inject.Inject;
import java.io.IOException;

import static spark.Spark.halt;

/**
 * @author Michael Bredel
 */
public class UserController {

    /** The configuration service. */
    @SuppressWarnings("unused")
    private ConfigurationService configurationService;
    /** The store service to store and retrieve users. */
    private StoreService storeService;

    /**
     * The default constructor used by Guice. Since
     * the constructor is package-private UserController
     * objects can (and should) only be instantiated
     * using the UserController factory.
     *
     * @param configurationService The configuration service injected by Guice.
     * @param storeService The store service injected by Guice.
     */
    @Inject
    UserController(
            ConfigurationService configurationService,
            StoreService storeService
            ) {
        this.configurationService = configurationService;
        this.storeService = storeService;
    }

    /**
     * Gets a list of user stored in the system.
     *
     * @param req The HTTP request information.
     * @param res The HTTP response information.
     * @return The HTTP status and, in case of success, the list of users.
     */
    public String getUsers(Request req, Response res) {
        String result = "[]";
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.writeValueAsString(this.storeService.readAll());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Gets a single user stored in the system.
     *
     * @param req The HTTP request information.
     * @param res The HTTP response information.
     * @return The HTTP status and, in case of success, the user.
     */
    public String getUser(Request req, Response res) {

        String username = req.params(":username");
        User user = this.storeService.read(username);
        if (user == null) {
            halt(HttpStatus.SC_NOT_FOUND);
        }

        String result = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.writeValueAsString(this.storeService.read(username));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Create a new user.
     *
     * @param req The HTTP request information.
     * @param res The HTTP response information.
     * @return The HTTP status and, in case of success, the image or a list of images.
     */
    public String postUsers(Request req, Response res) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            User user = mapper.readValue(req.body(), User.class);
            this.storeService.create(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return req.body();
    }

    /**
     * Remove a single user from the system.
     *
     * @param req The HTTP request information.
     * @param res The HTTP response information.
     * @return The HTTP status and, in case of success, the user.
     */
    public String deleteUser(Request req, Response res) {
        String username = req.params(":username");
        try {
            this.storeService.delete(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }

}
