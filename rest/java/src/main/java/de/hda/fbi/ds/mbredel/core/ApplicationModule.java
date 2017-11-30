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
package de.hda.fbi.ds.mbredel.core;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import de.hda.fbi.ds.mbredel.controller.GeneralControllerFactory;
import de.hda.fbi.ds.mbredel.controller.UserControllerFactory;

/**
 * The basic module that binds the application-related
 * factories and interfaces to their implementations.
 *
 * @author Michael Bredel
 */
public class ApplicationModule extends AbstractModule {
    @Override
    protected void configure() {

        // Create a factory to inject UserController objects whenever needed.
        install(new FactoryModuleBuilder().build(UserControllerFactory.class));

        // Create a factory to inject GeneralController objects whenever needed.
        install(new FactoryModuleBuilder().build(GeneralControllerFactory.class));

    }
}
