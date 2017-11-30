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
