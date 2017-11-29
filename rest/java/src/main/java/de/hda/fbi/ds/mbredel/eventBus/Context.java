package de.hda.fbi.ds.mbredel.eventBus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Michael Bredel
 */
public class Context {
    /** The logger. */
    private static final Logger LOGGER = LoggerFactory.getLogger(Context.class);

    /** The context storage. */
    private Map<String, Object> contextMap;

    /**
     * Default constructor.
     */
    public Context() {
        contextMap = new ConcurrentHashMap<>();
    }

    /**
     * Convenience constructor to directly add a key-value pair.
     *
     * @param key The key string to address the context information.
     * @param value The actual context information.
     */
    public Context(String key, Object value) {
        if (key == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("No context added, the key is null.");
            }
            return;
        }
        if (value == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("No context added, the value is null.");
            }
            return;
        }

        contextMap = new ConcurrentHashMap<>();
        contextMap.put(key, value);
    }

    /**
     * Adds a context information.
     *
     * @param key The key string to address the context information.
     * @param value The actual context information.
     */
    public Context add(String key, Object value) {
        if (key == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("No context added, the key is null.");
            }
            return this;
        }
        if (value == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("No context added, the value is null.");
            }
            return this;
        }
        this.contextMap.put(key, value);
        return this;
    }

    /**
     * Removes a context information.
     *
     * @param key The key string to address the context information.
     */
    public void remove(String key) {
        if (key == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("No context removed, the key is null.");
            }
            return;
        }
        this.contextMap.remove(key);
    }

    /**
     * Gets some context information.
     *
     * @param key The key string to address the context information.
     * @return The context information related to the given key.
     */
    public Object get(String key) {
        if (key == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("No context object found, the key is null.");
            }
            return null;
        }
        return this.contextMap.get(key);
    }

    /**
     * Removes all the context information in the given context object.
     */
    public void clear() {
        this.contextMap.clear();
    }

    @Override
    public String toString() {
        String result = "context=[";
        for (Map.Entry<String, Object> entry : contextMap.entrySet()) {
            result += entry.getKey() + "=" + entry.getValue() + ",";
        }
        if (result.endsWith(","))
            result = result.substring(0, result.length()-1);
        result += "]";
        return result;

    }
}
