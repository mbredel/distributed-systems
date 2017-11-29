package de.hda.fbi.ds.mbredel.core;

import de.hda.fbi.ds.mbredel.controller.GeneralController;
import de.hda.fbi.ds.mbredel.controller.GeneralControllerFactory;
import de.hda.fbi.ds.mbredel.controller.UserController;
import de.hda.fbi.ds.mbredel.controller.UserControllerFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

import static spark.Spark.*;

/**
 * The main application that starts the Spark services,
 * listens to routes, and handles incoming HTTP requests.
 *
 * @author Michael Bredel
 */
@Singleton
public class Application {

    /** The user controller that handles requests related to users. */
    private UserController userController;
    /** The general controller. */
    private GeneralController generalController;

    @Inject
    public Application(
            GeneralControllerFactory generalControllerFactory,
            UserControllerFactory userControllerFactory
    ) {
        this.generalController = generalControllerFactory.create();
        this.userController = userControllerFactory.create();
    }

    /**
     * Start listening to routes.
     */
    public void start() {

        // Set the HTTP port to listen to.
        port(Constants.HTTP_PORT);

        // Standard GET.
        get(Routes.ROOT, generalController::get);

        head(Routes.ROOT, generalController::head);

        trace(Routes.ROOT, generalController::trace);

        // User handling.
        path(Routes.USERS, () -> {

            // Post a new user.
            post("", userController::postUsers);

            // Get a list of available users.
            get("", userController::getUsers);

            // Get a single user.
            get("/:username", userController::getUser);

            // Delete a single user.
            delete("/:username", userController::deleteUser);

        });
    }
}
