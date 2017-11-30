/*
 Copyright (c) 2017, Michael Bredel, H-DA
 ALL RIGHTS RESERVED.

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

 Neither the name of the H-DA and Michael Bredel
 nor the names of its contributors may be used to endorse or promote
 products derived from this software without specific prior written
 permission.
 */
package de.hda.fbi.ds.mbredel.configuration.cfg4j;

import de.hda.fbi.ds.mbredel.configuration.CliParameters;
import de.hda.fbi.ds.mbredel.configuration.ConfigurationService;
import de.hda.fbi.ds.mbredel.core.Constants;
import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.context.environment.Environment;
import org.cfg4j.source.context.environment.ImmutableEnvironment;
import org.cfg4j.source.context.filesprovider.ConfigFilesProvider;
import org.cfg4j.source.files.FilesConfigurationSource;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.inject.Singleton;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.NoSuchElementException;

/**
 * An implementation of the configuration service interface
 * based on cfg4j.
 *
 * @author Michael Bredel
 */
@Singleton
public class Cfg4jConfiguration implements ConfigurationService {

    /** The path to the default configuration file. */
    private static final String DEFAULT_CONFIG_FILE = "./src/main/resources/config.yaml";

    /** The configuration provider. */
    private ConfigurationProvider configurationProvider;

    /**
     * Default constructor used by Guice.
     */
    public Cfg4jConfiguration() {
        URI uri;
        try {
            uri = URI.create(CliParameters.getInstance().getUri());
        } catch (NoSuchElementException e) {
            uri = URI.create(DEFAULT_CONFIG_FILE);
        }

        if (uri.getScheme() != null && (uri.getScheme().equalsIgnoreCase("http") || uri.getScheme().equalsIgnoreCase("https"))) {
            // Process a remote configuration.
            this.setupRemoteConfig(uri);
        } else {
            // Process a local file configuration.
            this.setupFileConfig(uri);
        }
    }

    /**
     * Sets up the a remote configuration.
     *
     * @param uri The URI to the configuration file.
     */
    private void setupRemoteConfig(URI uri) {
        throw new UnsupportedOperationException();
    }

    /**
     * Sets up the file-based configuration.
     *
     * @param uri The URI to the configuration file.
     */
    private void setupFileConfig(URI uri) {
        Path path = Paths.get(uri.getPath());
        if (path.toFile().exists() && path.toFile().isFile()) {
            String configFile = path.getFileName().toString();
            String configPath = path.getParent().toString();
            Environment environment = new ImmutableEnvironment(configPath);
            ConfigFilesProvider configFilesProvider = () -> Collections.singletonList(Paths.get(configFile));
            ConfigurationSource configurationSource = new FilesConfigurationSource(configFilesProvider);
            this.configurationProvider = new ConfigurationProviderBuilder()
                    .withEnvironment(environment)
                    .withConfigurationSource(configurationSource)
                    .build();
        } else {
            System.err.println("Could not find configuration file: " + path);
            System.exit(Constants.EXIT_CODE_ERROR);
        }
    }

    @Override
    @Nullable
    public <T> T getProperty(String key, Class<T> type) {
        return this.configurationProvider.getProperty(key, type);
    }

    @Override
    @Nonnull
    public <T> T getProperty(String key, Object defaultValue, Class<T> type) {
        try {
            return this.configurationProvider.getProperty(key, type);
        } catch (Exception e) {
            if (defaultValue != null && type.isInstance(defaultValue))
                return (T) defaultValue;
            else
                throw e;
        }
    }
}
