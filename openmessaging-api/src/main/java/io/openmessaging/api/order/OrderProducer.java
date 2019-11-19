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
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.openmessaging.api.order;

import io.openmessaging.api.Credentials;
import io.openmessaging.api.LifeCycle;
import io.openmessaging.api.Message;
import io.openmessaging.api.SendResult;

/**
 * Sequential message producer interface  
 *
 * @version OMS 1.2.0
 * @since OMS 1.2.0
 */
public interface OrderProducer extends LifeCycle, Credentials {

    /**
     * Send message in order
     *
     * @param message
     * @param shardingKey Order message selection factor, the sending method selects a specific message queue based on
     * shardingKey
     * @return {@link SendResult} Message delivery result, including message Id.
     */
    SendResult send(final Message message, final String shardingKey);
}
