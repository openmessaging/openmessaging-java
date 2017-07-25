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
import io.openmessaging.OMSBuiltinKeys;
import io.openmessaging.ResourceManager;
import java.util.List;

/**
 * A {@code Routing} object is responsible for routing the messages from {@code Topic} to {@code Queue}, with
 * some useful operators to filter or compute the source messages.
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
     * and use {@link ResourceManager#createAndUpdateRouting(String, KeyValue)} to modify.
     * <p>
     * There are some standard attributes defined by OMS for {@code Routing}:
     * <ul>
     * <li> {@link OMSBuiltinKeys#SRC_TOPIC}, the source topic of this {@code Routing} object.
     * <li> {@link OMSBuiltinKeys#DST_QUEUE}, the destination queue of this {@code Routing} object.
     * <li> {@link OMSBuiltinKeys#ROUTING_NAME}, the unique name of this {@code Routing} object.
     * </ul>
     *
     * @return the attributes
     */
    KeyValue properties();

    /**
     * Adds a {@code Operator} to the tail of operator pipeline in this {@code Routing} object.
     *
     * @param op a specified operator
     * @return this {@code Routing} object
     */
    Routing addOperator(Operator op);

    /**
     * Removes a specified {@code Operator} from the operator pipeline in this {@code Routing} object.
     *
     * @param op a specified operator
     * @return this {@code Routing} object
     */
    Routing deleteOperator(Operator op);

    /**
     * Returns the operator pipeline in this {@code Routing} object.
     *
     * @return the operator pipeline
     */
    List<Operator> operators();
}
