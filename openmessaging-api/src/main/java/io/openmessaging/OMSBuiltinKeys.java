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
 * This is the centralized source for keys that are used by {@link KeyValue}.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface OMSBuiltinKeys {
    String DRIVER_IMPL = "DRIVER_IMPL";
    String ACCESS_POINTS = "ACCESS_POINTS";
    String NAMESPACE = "NAMESPACE";
    String ACCOUNT_ID = "ACCOUNT_ID";
    String REGION = "REGION";
    String PRODUCER_ID = "PRODUCER_ID";
    String CONSUMER_ID = "CONSUMER_ID";
    String OPERATION_TIMEOUT = "OPERATION_TIMEOUT";

    String ROUTING_SOURCE = "ROUTING_SOURCE";
    String ROUTING_DESTINATION = "ROUTING_DESTINATION";
    String ROUTING_RULE = "ROUTING_RULE";
}
