package de.hda.fbi.ds.mbredel.eventBus.akka;

import akka.actor.ActorRef;
import akka.event.japi.LookupEventBus;
import de.hda.fbi.ds.mbredel.eventBus.BasicEvent;
import de.hda.fbi.ds.mbredel.eventBus.EventBusService;

/**
 * @author Michael Bredel
 */
public class AkkaEventBus extends LookupEventBus<BasicEvent, ActorRef, String> implements EventBusService {
    /** Initial size of the index data structure, i.e. the expected number of different classifiers. Use powers of 2. */
    private static final int MAP_SIZE = 4;

    @Override
    public int mapSize() {
        return MAP_SIZE;
    }

    @Override
    public void subscribe(ActorRef subscriber, Class<? extends BasicEvent> eventClass) {
        super.subscribe(subscriber, eventClass.getSimpleName());
    }

    @Override
    public int compareSubscribers(ActorRef subscriberA, ActorRef subscriberB) {
        return subscriberA.compareTo(subscriberB);
    }

    @Override
    public String classify(BasicEvent event) {
        return event.getClass().getSimpleName();
    }

    @Override
    public void publish(BasicEvent event, ActorRef subscriber) {
        subscriber.tell(event, ActorRef.noSender());
    }
}
