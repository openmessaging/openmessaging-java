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

package io.openmessaging.consumer;

import io.openmessaging.Client;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.ServiceLifecycle;
import io.openmessaging.exception.OMSDestinationException;
import io.openmessaging.exception.OMSRuntimeException;
import io.openmessaging.exception.OMSSecurityException;
import io.openmessaging.interceptor.ConsumerInterceptor;
import io.openmessaging.message.Message;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * A {@code PushConsumer} receives messages from multiple queues, these messages are pushed from MOM server to {@code
 * Consumer} client.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface Consumer extends ServiceLifecycle, Client {

    /**
     * This method is used to find out the collection of queues bind to {@code Consumer}.
     *
     * @return the queues this consumer is bind, or null if the consumer is not bind queue.
     */
    Set<String> getBindQueues();

    /**
     * Adds a {@code ConsumerInterceptor} instance to this consumer.
     *
     * @param interceptor an interceptor instance.
     */
    void addInterceptor(ConsumerInterceptor interceptor);

    /**
     * Removes an interceptor from this consumer.
     *
     * @param interceptor an interceptor to be removed.
     */
    void removeInterceptor(ConsumerInterceptor interceptor);

    /**
     * Acknowledges the specified and consumed message with the unique message receipt handle, in the scenario of using
     * manual commit.
     * <p>
     * Messages that have been received but not acknowledged may be redelivered.
     *
     * @param receipt the receipt handle associated with the consumed message.
     */
    void ack(MessageReceipt receipt);

}