/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.openmessaging.routing;

import io.openmessaging.KeyValue;
import io.openmessaging.ResourceManager;

/**
 * A {@code Routing} object is responsible for routing the messages from {@code Topic} to {@code Queue}, with
 * an expression to filter or compute the source messages.
 *
 * @author vintagewang@apache.org
 * @author yukon@apache.org
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface Routing {
    /**
     * Returns the attributes of this {@code Routing} instance.
     * Changes to the return {@code KeyValue} are not reflected in physical {@code Routing},
     * and use {@link ResourceManager#updateRouting(String, Routing)} to modify.
     *
     * @return the attributes
     */
    KeyValue properties();

    /**
     * The routing source, naming scheme is {@literal TOPIC::<TOPIC_NAME>}.
     * Messages sent to the source topic will be routing to the destination Queue.
     *
     * @return the source topic
     */
    String source();

    /**
     * The routing destination, naming scheme is {@literal QUEUE::<QUEUE_NAME>}
     * Messages sent to the source topic will be routing to this Queue.
     *
     * @return the destination queue
     */
    String destination();

    /**
     * The routing rule, expression scheme is {@literal SQL::<SQL92_STRING>}
     *
     * @return the routing expression
     */
    String expression();

    /**
     * Returns the unique routing name of current namespace
     *
     * @return the routing name
     */
    String routingName();
}
