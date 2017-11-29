package de.hda.fbi.ds.mbredel.eventBus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Michael Bredel
 */
public abstract class BasicEvent {
    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(BasicEvent.class);

    /** A message string that describes the event. */
    protected String message;
    /** An arbitrary context object. */
    protected Context context;

    /**
     * Default constructor.
     */
    protected BasicEvent() {
        this.message = "";
    }

    /**
     * Constructor.
     *
     * @param message The message that should be transported by the event.
     */
    protected BasicEvent(String message) {
        this.message = message;
    }

    /**
     * Gets the raw message.
     *
     * @return The message.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Returns the arbitrary context object.
     *
     * @return The arbitrary context object.
     */
    public Context getContext() {
        return this.context;
    }

    @Override
    public String toString() {
        String result = "event=[";
        if (message != null && !message.equals(""))
            result += "message=\"" + message + "\",";
        if (context != null)
            result += context;
        if (result.endsWith(","))
            result = result.substring(0, result.length()-1);
        result += "]";
        return result;
    }

}
