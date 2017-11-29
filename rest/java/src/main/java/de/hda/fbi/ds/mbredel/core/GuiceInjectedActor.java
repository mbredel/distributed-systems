package de.hda.fbi.ds.mbredel.core;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import com.google.inject.Injector;

/**
 * @author Michael Bredel
 */
public class GuiceInjectedActor implements IndirectActorProducer {

    /** The Guice injector. */
    private final Injector injector;
    /** The actor class to inject. */
    private final Class<? extends Actor> actorClass;

    /**
     * The default constructor.
     *
     * @param injector The Guice injector.
     * @param actorClass The actor class to inject.
     */
    public GuiceInjectedActor(Injector injector, Class<? extends Actor> actorClass) {
        this.injector = injector;
        this.actorClass = actorClass;
    }

    @Override
    public Class<? extends Actor> actorClass() {
        return actorClass;
    }

    @Override
    public Actor produce() {
        return injector.getInstance(actorClass);
    }
}
