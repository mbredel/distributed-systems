package de.hda.fbi.ds.mbredel.configuration;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.NoSuchElementException;

/**
 * The configuration service allows to access
 * configuration parameters in a key-value
 * manner.
 *
 * @author Michael Bredel
 */
public interface ConfigurationService {
    /**
     * Get a configuration property of a given basic {@code type}.
     *
     * @param key The configuration key.
     * @param <T> The property type.
     * @throws NoSuchElementException When the provided {@code key} doesn't have a corresponding config value
     * @throws IllegalArgumentException When property can't be converted to {@code type}
     * @throws IllegalStateException When provider is unable to fetch configuration value for the given {@code key}
     */
    @Nullable
    <T> T getProperty(String key, Class<T> type);

    /**
     * Get a configuration property of a given basic {@code type}.
     *
     * @param key The configuration key.
     * @param defaultValue The default value. Has to be the same type as property type.
     * @param <T> The property type.
     * @throws IllegalArgumentException When property can't be converted to {@code type}
     */
    @Nonnull
    <T> T getProperty(String key, Object defaultValue, Class<T> type);
}
