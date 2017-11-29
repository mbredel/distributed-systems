package de.hda.fbi.ds.mbredel.controller;

/**
 * The factory to create GeneralController
 * objects. By using the genera controller
 * factory, GeneralController objects are
 * instantiated by Guice.
 *
 * @author Michael Bredel
 */
public interface GeneralControllerFactory {
    /**
     * Create a general controller object.
     *
     * @return A general controller.
     */
    GeneralController create();
}
