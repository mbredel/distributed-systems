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
