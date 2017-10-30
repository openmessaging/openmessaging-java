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

/**
 * A {@code Operator} is used to handle the flowing messages in {@code Routing}.
 *
 * There are many kinds of {@code Operator}, expression operator, deduplicator operator,
 * joiner operator, filter operator, rpc operator, and so on.
 *
 * @author vintagewang@apache.org
 * @author yukon@apache.org
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface Operator {
    /**
     * Returns the attributes of this {@code Operator} object.
     * Changes to the return {@code KeyValue} are not reflected in physical {@code Operator}.
     * <p>
     * There are some standard attributes defined by OMS for {@code Routing}:
     * <ul>
     * <li> {@link OMSBuiltinKeys#OPERATOR_NAME}, the unique name of this {@code Operator} object.
     * </ul>
     *
     * @return the attributes
     */
    KeyValue properties();

    /**
     * A expression to represent this {@code Operator} object.
     *
     * @return the expression
     */
    String expression();
}
