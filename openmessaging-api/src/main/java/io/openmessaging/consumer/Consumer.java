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
import io.openmessaging.common.ErrorCode;
import io.openmessaging.exception.OMSRuntimeException;
import io.openmessaging.interceptor.ConsumerInterceptor;

/**
 * A {@code PushConsumer} receives messages from multiple queues, these messages are pushed from MOM server to {@code
 * PushConsumer} client.
 *
 * @version OMS 1.0.0
 * @see MessagingAccessPoint#createConsumer()
 * @since OMS 1.0.0
 */
public interface Consumer extends ServiceLifecycle {

    /**
     * Resumes the {@code Consumer} in push model after a suspend.
     * <p>
     * This method resumes the {@code Consumer} instance after it was suspended. The instance will not receive new
     * messages between the suspend and resume calls.
     *
     * @throws OMSRuntimeException if the instance has not been suspended.
     * @see Consumer#suspend()
     */
    void resume();

    /**
     * Suspends the {@code Consumer} in push model for later resumption.
     * <p>
     * This method suspends the consumer until it is resumed. The consumer will not receive new messages between the
     * suspend and resume calls.
     * <p>
     * This method behaves exactly as if it simply performs the call {@code suspend(0)}.
     *
     * @throws OMSRuntimeException if the instance is not currently running.
     * @see Consumer#resume()
     */
    void suspend();

    /**
     * Suspends the {@code Consumer} in push model for later resumption.
     * <p>
     * This method suspends the consumer until it is resumed or a specified amount of time has elapsed. The consumer
     * will not receive new messages during the suspended state.
     * <p>
     * This method is similar to the {@link #suspend()} method, but it allows finer control over the amount of time to
     * suspend, and the consumer will be suspended until it is resumed if the timeout is zero.
     *
     * @param timeout the maximum time to suspend in milliseconds.
     */
    void suspend(long timeout);

    /**
     * This method is used to find out whether the {@code Consumer} in push model is suspended.
     *
     * @return true if this {@code Consumer} is suspended, false otherwise
     */
    boolean isSuspended();

    /**
     * Bind the {@code Consumer} to a specified queue in pull model, user can use {@link Consumer#receive(long)} to get
     * message from bind queue
     * <p>
     * {@link MessageListener#onReceived(Message, MessageListener.Context)} will be called when new delivered message is
     * coming.
     *
     * @param queueName a specified queue
     * @return this {@code Consumer} instance
     */
    BindQueueResult bindQueue(String queueName);

    /**
     * Bind the {@code Consumer} to a specified queue, with a {@code MessageListener}.
     * <p>
     * {@link MessageListener#onReceived(Message, MessageListener.Context)} will be called when new delivered message is
     * coming.
     *
     * @param queueName a specified queue
     * @param listener a specified listener to receive new message
     * @return this {@code Consumer} instance
     */
    BindQueueResult bindQueue(String queueName, MessageListener listener);

    /**
     * Unbind the {@code Consumer} from a specified queue.
     * <p>
     * After the success call, this consumer won't receive new message from the specified queue any more.
     *
     * @param queueName a specified queue
     * @return this {@code Consumer} instance
     */
    BindQueueResult unbindQueue(String queueName);

    /**
     * Adds a {@code ConsumerInterceptor} instance to this consumer.
     *
     * @param interceptor an interceptor instance
     */
    void addInterceptor(ConsumerInterceptor interceptor);

    /**
     * Removes an interceptor from this consumer.
     *
     * @param interceptor an interceptor to be removed
     */
    void removeInterceptor(ConsumerInterceptor interceptor);

    /**
     * Receives the next message from the bind queues of this consumer in pull model.
     * <p>
     * This call blocks indefinitely until a message is arrives, the timeout expires, or until this {@code PullConsumer}
     * is shut down.
     *
     * @param timeout receive message will blocked at most <code>timeout</code> milliseconds
     * @return the next message received from the bind queues, or null if the consumer is concurrently shut down,if this
     * operation is expire, {@link ReceiveResult#getErrorCode()} will return {@link ErrorCode#REQUEST_TIMEOUT} error
     * code
     */
    ReceiveResult receive(long timeout);

    /**
     * Acknowledges the specified and consumed message with the unique message receipt handle, in the scenario of using
     * manual commit.
     * <p>
     * Messages that have been received but not acknowledged may be redelivered.
     *
     * @param receiptHandle the receipt handle associated with the consumed message
     */
    void ack(String receiptHandle);
}