package de.hda.fbi.ds.mbredel.eventBus;

import akka.actor.ActorRef;

/**
 * @author Michael Bredel
 */
public interface EventBusService {
    /**
     * Returns  a size hint for the number of Classifiers you
     * expect to have (use powers of 2).
     *
     * @return A size hint for the number of Classifiers you expect to have.
     */
    int mapSize();

    /**
     * Provides a total ordering of Subscribers.
     *
     * @param subscriberA The first subscriber.
     * @param subscriberB The second Subscriber.
     * @return
     */
    int compareSubscribers(ActorRef subscriberA, ActorRef subscriberB);

    /**
     * Returns the classifier associated with the given event. The
     * classifier is used to identify the topic of an event.
     *
     * @param event The event to classify.
     * @return The classifier associated with the given event.
     */
    String classify(BasicEvent event);

    /**
     * Publishes the given Event to the message bus.
     *
     * @param event The event to publish.
     */
    void publish(BasicEvent event);

    /**
     * Convenient method that subscribes an actor to the channel/topic
     * identified by the event class simple name, inferred by
     * event.getClass().getSimpleName();
     *
     * @param subscriber The actor reference that is subscribed to the event bus.
     * @param eventClass The event class that identifies the channel/topic.
     */
    void subscribe(ActorRef subscriber, Class<? extends BasicEvent> eventClass);
}
