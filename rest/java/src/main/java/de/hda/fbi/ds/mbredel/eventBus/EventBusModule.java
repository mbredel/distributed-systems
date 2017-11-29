package de.hda.fbi.ds.mbredel.eventBus;


import com.google.inject.AbstractModule;
import de.hda.fbi.ds.mbredel.eventBus.akka.AkkaEventBus;

/**
 * The Guice module that binds the event bus service to
 * a default Akka event bus implementation.
 *
 * @author Michael Bredel
 */
public class EventBusModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(EventBusService.class)
                .to(AkkaEventBus.class)
                .asEagerSingleton();
    }
}
