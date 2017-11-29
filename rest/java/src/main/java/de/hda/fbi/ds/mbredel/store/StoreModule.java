package de.hda.fbi.ds.mbredel.store;

import com.google.inject.AbstractModule;
import de.hda.fbi.ds.mbredel.store.simpleStore.InMemoryStore;

/**
 * The Guice module that binds the store service to
 * a default store implementation.
 *
 * @author Michael Bredel
 */

public class StoreModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(StoreService.class)
                .to(InMemoryStore.class)
                .asEagerSingleton();
    }
}
