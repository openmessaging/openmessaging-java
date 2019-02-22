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

import io.openmessaging.*;
import io.openmessaging.exception.OMSDestinationException;
import io.openmessaging.exception.OMSRuntimeException;
import io.openmessaging.exception.OMSSecurityException;
import io.openmessaging.exception.OMSTimeOutException;
import io.openmessaging.interceptor.ConsumerInterceptor;

import java.util.List;

/**
 * A {@code PushConsumer} receives messages from multiple queues, these messages are pushed from MOM server to {@code
 * PushConsumer} client.
 *
 * @version OMS 1.0.0
 * @see MessagingAccessPoint#createConsumer().
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
     * @throws OMSRuntimeException if the instance is not currently running.
     */
    void suspend(long timeout);

    /**
     * This method is used to find out whether the {@code Consumer} in push model is suspended.
     *
     * @return true if this {@code Consumer} is suspended, false otherwise.
     */
    boolean isSuspended();

    /**
     * Bind the {@code Consumer} to a specified queue in pull model, user can use {@link Consumer#receive(long)} to get
     * message from bind queue.
     * <p>
     * {@link MessageListener#onReceived(Message, MessageListener.Context)} will be called when new delivered message is
     * coming.
     *
     * @param queueName a specified queue.
     * @throws OMSSecurityException when have no authority to bind to this queue.
     * @throws OMSDestinationException when have no given destination in the server.
     * @throws OMSRuntimeException when the {@code Producer} fails to send the message due to some internal error.
     */
    void bindQueue(String queueName);

    /**
     * Bind the {@code Consumer} to a specified queue, with a {@code MessageListener}.
     * <p>
     * {@link MessageListener#onReceived(Message, MessageListener.Context)} will be called when new delivered message is
     * coming.
     *
     * @param queueName a specified queue.
     * @param listener a specified listener to receive new message.
     * @throws OMSSecurityException when have no authority to bind to this queue.
     * @throws OMSDestinationException when have no given destination in the server.
     * @throws OMSRuntimeException when the {@code Producer} fails to send the message due to some internal error.
     */
    void bindQueue(String queueName, MessageListener listener);

    /**
     * Bind the {@code Consumer} to a specified queue, with a {@code BatchMessageListener}.
     * <p>
     * {@link BatchMessageListener#onReceived(BatchConsumeMessage batchMessage, BatchMessageListener.Context context)} will be called when new delivered messages is
     * coming.
     *
     * @param queueName a specified queue.
     * @param listener a specified listener to receive new messages.
     * @throws OMSSecurityException when have no authority to bind to this queue.
     * @throws OMSDestinationException when have no given destination in the server.
     * @throws OMSRuntimeException when the {@code Producer} fails to send the message due to some internal error.
     */
    void bindQueue(String queueName, BatchMessageListener listener);

    /**
     * Unbind the {@code Consumer} from a specified queue.
     * <p>
     * After the success call, this consumer won't receive new message from the specified queue any more.
     *
     * @param queueName a specified queue.
     */
    void unbindQueue(String queueName);

    /**
     * This method is used to find out whether the {@code Consumer} in bind queue.
     *
     * @return true if this {@code Consumer} is bind, false otherwise.
     */
    boolean isBindQueue();

    /**
     * This method is used to find out the queue bind to {@code Consumer}.
     *
     * @return the queue this consumer is bind, or null if the consumer is not bind queue.
     */
    String getBindQueue();

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
     * Receives the next message from the bind queues of this consumer in pull model.
     * <p>
     * This call blocks indefinitely until a message is arrives, the timeout expires, or until this {@code PullConsumer}
     * is shut down.
     *
     * @param timeout receive message will blocked at most <code>timeout</code> milliseconds.
     * @return the next message received from the bind queues, or null if the consumer is concurrently shut down.
     * @throws OMSSecurityException when have no authority to receive messages from this queue.
     * @throws OMSTimeOutException when the given timeout elapses before the send operation completes.
     * @throws OMSRuntimeException when the {@code Producer} fails to send the message due to some internal error.
     */
    ConsumeMessage receive(long timeout);

    /**
     * Receives the next batch messages from the bind queues of this consumer in pull model.
     * <p>
     * This call blocks indefinitely until the messages is arrives, the timeout expires, or until this {@code PullConsumer}
     * is shut down.
     *
     * @param timeout receive messages will blocked at most <code>timeout</code> milliseconds.
     * @return the next batch messages received from the bind queues, or null if the consumer is concurrently shut down.
     * @throws OMSSecurityException when have no authority to receive messages from this queue.
     * @throws OMSTimeOutException when the given timeout elapses before the send operation completes.
     * @throws OMSRuntimeException when the {@code Producer} fails to send the message due to some internal error.
     */
    List<ConsumeMessage> batchReceive(long timeout);

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