package de.hda.fbi.ds.mbredel.core;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import de.hda.fbi.ds.mbredel.controller.GeneralControllerFactory;
import de.hda.fbi.ds.mbredel.controller.UserControllerFactory;

/**
 * The basic module that binds the application-related
 * factories and interfaces to their implementations.
 *
 * @author Michael Bredel
 */
public class ApplicationModule extends AbstractModule {
    @Override
    protected void configure() {

        // Create a factory to inject UserController objects whenever needed.
        install(new FactoryModuleBuilder().build(UserControllerFactory.class));

        // Create a factory to inject GeneralController objects whenever needed.
        install(new FactoryModuleBuilder().build(GeneralControllerFactory.class));

    }
}
