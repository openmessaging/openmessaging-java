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

/**
 * A {@code Routing} object is responsible for routing the messages from a queue to another queue, with
 * an expression to filter or compute the source messages.
 *
 * @version OMS 1.0
 * @since OMS 1.0
 */
public final class Routing {
    /**
     * The routing source, naming scheme is {@literal QUEUE::<QUEUE_NAME>}.
     * <p>
     * Messages sent to the source queue will be routing to the destination Queue.
     */
    private String source;

    /**
     * The routing destination, naming scheme is {@literal QUEUE::<QUEUE_NAME>}.
     * <p>
     * Messages sent to the source queue will be routing to this Queue.
     */
    private String destination;

    /**
     * The routing rule, expression scheme is {@literal SQL::<SQL92_STRING>}.
     * <p>
     * Messages sent to the source queue will be handled by the rule expression,
     * and then routing to destination queue.
     */
    private String rule;

    /**
     * Constructs a routing instance contains the source, destination, and rule expression,
     * and used by {@code ResourceManager}.
     *
     * @param source the source queue
     * @param destination the destination queue
     * @param rule the rule expression
     */
    public Routing(final String source, final String destination, final String rule) {
        this.source = source;
        this.destination = destination;
        this.rule = rule;
    }

    /**
     * Returns the routing source queue.
     *
     * @return the source queue
     */
    String source() {
        return source;
    }

    /**
     * Returns the routing destination queue.
     *
     * @return the destination queue
     */
    String destination() {
        return destination;
    }

    /**
     * Returns the rule expression
     *
     * @return the routing expression
     */
    String rule() {
        return rule;
    }
}
