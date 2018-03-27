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

package io.openmessaging;

/**
 * This is the centralized source for keys that are used for OMS standard attributes.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface OMSBuiltinKeys {
    /**
     * The {@code DRIVER_IMPL} key represents the vendor implementation
     * entry of {@link MessagingAccessPoint}.
     */
    String DRIVER_IMPL = "DRIVER_IMPL";

    /**
     * The {@code ACCESS_POINTS} key shows the specified access points in OMS driver schema.
     * @see <a href="https://github.com/openmessaging/specification/blob/master/oms_access_point_schema.md">Access Point Schema</a>
     */
    String ACCESS_POINTS = "ACCESS_POINTS";

    /**
     * The {@code NAMESPACE} key defines the isolated space of resources and related operations.
     */
    String NAMESPACE = "NAMESPACE";

    /**
     * The {@code ACCOUNT_ID} key shows the specified account info in OMS driver schema.
     */
    String ACCOUNT_ID = "ACCOUNT_ID";

    /**
     * The {@code REGION} key shows the specified region in OMS driver schema.
     */
    String REGION = "REGION";

    /**
     * The {@code PRODUCER_ID} key represents the the unique producer id of a producer instance.
     */
    String PRODUCER_ID = "PRODUCER_ID";

    /**
     * The {@code CONSUMER_ID} key represents the the unique consumer id of a consumer instance.
     */
    String CONSUMER_ID = "CONSUMER_ID";

    /**
     * The {@code OPERATION_TIMEOUT} key defines the timeout of almost all the method calls in OMS.
     */
    String OPERATION_TIMEOUT = "OPERATION_TIMEOUT";

    /**
     * The {@code ROUTING_SOURCE} key shows the source queue of a {@code Routing} instance.
     * <p>
     * The {@code Routing} consists of a triple, include source queue, destination queue and expression.
     */
    String ROUTING_SOURCE = "ROUTING_SOURCE";

    /**
     * The {@code ROUTING_DESTINATION} key shows the destination queue of a {@code Routing} instance.
     * <p>
     * The {@code Routing} consists of a triple, include source queue, destination queue and expression.
     */
    String ROUTING_DESTINATION = "ROUTING_DESTINATION";

    /**
     * The {@code ROUTING_EXPRESSION} key shows the expression of a {@code Routing} instance.
     * <p>
     * The {@code Routing} consists of a triple, include source queue, destination queue and expression.
     */
    String ROUTING_EXPRESSION = "ROUTING_EXPRESSION";
}
