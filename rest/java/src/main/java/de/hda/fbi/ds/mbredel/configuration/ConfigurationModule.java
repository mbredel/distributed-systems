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
