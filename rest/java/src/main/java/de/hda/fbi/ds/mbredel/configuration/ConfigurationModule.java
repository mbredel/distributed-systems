package de.hda.fbi.ds.mbredel.configuration;

import com.google.inject.AbstractModule;
import de.hda.fbi.ds.mbredel.configuration.cfg4j.Cfg4jConfiguration;

/**
 * The Guice module that binds the configuration service to
 * a default configuration implementation.
 *
 * @author Michael Bredel
 */
public class ConfigurationModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(ConfigurationService.class)
                .to(Cfg4jConfiguration.class)
                .asEagerSingleton();
    }
}
