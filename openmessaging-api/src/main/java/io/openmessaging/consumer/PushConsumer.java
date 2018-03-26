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
 * A {@code PushConsumer} receives messages from multiple queues, these messages are pushed from
 * MOM server to {@code PushConsumer} client.
 *
 * @version OMS 1.0.0
 * @see MessagingAccessPoint#createPushConsumer()
 * @since OMS 1.0.0
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
    KeyValue attributes();

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
     * This method suspends the consumer until it is resumed.
     * The consumer will not receive new messages between the suspend and resume calls.
     * <p>
     * This method behaves exactly as if it simply performs the call {@code suspend(0)}.
     *
     * @throws OMSRuntimeException if the instance is not currently running.
     * @see PushConsumer#resume()
     */
    void suspend();

    /**
     * Suspends the {@code PushConsumer} for later resumption.
     * <p>
     * This method suspends the consumer until it is resumed or a
     * specified amount of time has elapsed.
     * The consumer will not receive new messages during the suspended state.
     * <p>
     * This method is similar to the {@link #suspend()} method, but it allows finer control
     * over the amount of time to suspend, and the consumer will be suspended until it is resumed
     * if the timeout is zero.
     *
     * @param timeout the maximum time to suspend in milliseconds.
     * @throws OMSRuntimeException if the instance is not currently running.
     */
    void suspend(long timeout);

    /**
     * This method is used to find out whether the {@code PushConsumer} is suspended.
     *
     * @return true if this {@code PushConsumer} is suspended, false otherwise
     */
    boolean isSuspended();

    /**
     * Attaches the {@code PushConsumer} to a specified queue, with a {@code MessageListener}.
     * <p>
     * {@link MessageListener#onReceived(Message, MessageListener.Context)} will be called when new
     * delivered message is coming.
     *
     * @param queueName a specified queue
     * @param listener a specified listener to receive new message
     * @return this {@code PushConsumer} instance
     */
    PushConsumer attachQueue(String queueName, MessageListener listener);

    /**
     * Attaches the {@code PushConsumer} to a specified queue, with a {@code MessageListener} and some
     * specified attributes.
     * <p>
     * {@link MessageListener#onReceived(Message, MessageListener.Context)}  will be called when new
     * delivered message is coming.
     *
     * @param queueName a specified queue
     * @param listener a specified listener to receive new message
     * @param attributes some specified attributes
     * @return this {@code PushConsumer} instance
     */
    PushConsumer attachQueue(String queueName, MessageListener listener, KeyValue attributes);

    /**
     * Detaches the {@code PushConsumer} from a specified queue.
     * <p>
     * After the success call, this consumer won't receive new message
     * from the specified queue any more.
     *
     * @param queueName a specified queue
     * @return this {@code PushConsumer} instance
     */
    PushConsumer detachQueue(String queueName);

    /**
     * Adds a {@code PushConsumerInterceptor} instance to this consumer.
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