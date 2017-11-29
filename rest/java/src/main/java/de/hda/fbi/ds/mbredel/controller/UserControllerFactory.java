package de.hda.fbi.ds.mbredel.controller;

/**
 * The factory to create UserController
 * objects. By using the user controller
 * factory, UserController objects are
 * instantiated by Guice.
 *
 * @author Michael Bredel
 */
public interface UserControllerFactory {
    /**
     * Create a user controller object.
     *
     * @return A user controller.
     */
    UserController create();
}
