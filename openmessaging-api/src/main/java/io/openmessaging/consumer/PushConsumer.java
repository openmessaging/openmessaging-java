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

import io.openmessaging.KeyValue;
import io.openmessaging.Message;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.OMSBuiltinKeys;
import io.openmessaging.ServiceLifecycle;
import io.openmessaging.exception.OMSRuntimeException;
import io.openmessaging.interceptor.PushConsumerInterceptor;

/**
 * A {@code PushConsumer} object to receive messages from multiple queues, these messages are pushed from
 * MOM server to {@code PushConsumer} client.
 *
 * @author vintagewang@apache.org
 * @author yukon@apache.org
 * @version OMS 1.0
 * @see MessagingAccessPoint#createPushConsumer()
 * @since OMS 1.0
 */
public interface PushConsumer extends ServiceLifecycle {
    /**
     * Returns the attributes of this {@code PushConsumer} instance.
     * Changes to the return {@code KeyValue} are not reflected in physical {@code PushConsumer}.
     * <p>
     * There are some standard attributes defined by OMS for {@code PushConsumer}:
     * <ul>
     * <li> {@link OMSBuiltinKeys#CONSUMER_ID}, the unique consumer id for a consumer instance.
     * <li> {@link OMSBuiltinKeys#OPERATION_TIMEOUT}, the default timeout period for operations of {@code PushConsumer}.
     * </ul>
     *
     * @return the attributes
     */
    KeyValue properties();

    /**
     * Resumes the {@code PushConsumer} after a suspend.
     * <p>
     * This method resumes the {@code PushConsumer} instance after it was suspended.
     * The instance will not receive new messages between the suspend and resume calls.
     *
     * @throws OMSRuntimeException if the instance has not been suspended.
     * @see PushConsumer#suspend()
     */
    void resume();

    /**
     * Suspends the {@code PushConsumer} for later resumption.
     * <p>
     * This method suspends the {@code PushConsumer} instance until it is resumed.
     * The instance will not receive new messages between the suspend and resume calls.
     *
     * @throws OMSRuntimeException if the instance is not currently running.
     * @see PushConsumer#resume()
     */
    void suspend();

    /**
     * This method is used to find out whether the {@code PushConsumer} is suspended.
     *
     * @return true if this {@code PushConsumer} is suspended, false otherwise
     */
    boolean isSuspended();

    /**
     * Attaches the {@code PushConsumer} to a specified queue, with a {@code MessageListener}.
     * {@link MessageListener#onReceived(Message, MessageListener.Context)} will be called when new
     * delivered message is coming.
     *
     * @param queueName a specified queue
     * @param listener a specified listener to receive new message
     * @return this {@code PushConsumer} instance
     * @throws OMSRuntimeException if this {@code PushConsumer} fails to attach the specified queue due to some internal
     * error.
     */
    PushConsumer attachQueue(String queueName, MessageListener listener);

    /**
     * Attaches the {@code PushConsumer} to a specified queue, with a {@code MessageListener} and some
     * specified attributes.
     * {@link MessageListener#onReceived(Message, MessageListener.Context)}  will be called when new
     * delivered message is coming.
     *
     * @param queueName a specified queue
     * @param listener a specified listener to receive new message
     * @param properties some specified attributes
     * @return this {@code PushConsumer} instance
     * @throws OMSRuntimeException if this {@code PushConsumer} fails to attach the specified queue due to some internal
     * error.
     */
    PushConsumer attachQueue(String queueName, MessageListener listener, KeyValue properties);

    /**
     * Detaches the {@code PushConsumer} from a specified queue.
     * After the success call, this consumer won't receive new message
     * from the specified queue any more.
     *
     * @param queueName a specified queue
     * @return this {@code PushConsumer} instance
     */
    PushConsumer detachQueue(String queueName);

    /**
     * Adds a {@code PushConsumerInterceptor} to this consumer.
     *
     * @param interceptor an interceptor instance
     */
    void addInterceptor(PushConsumerInterceptor interceptor);

    /**
     * Removes an interceptor from this consumer.
     *
     * @param interceptor an interceptor to be removed
     */
    void removeInterceptor(PushConsumerInterceptor interceptor);
}