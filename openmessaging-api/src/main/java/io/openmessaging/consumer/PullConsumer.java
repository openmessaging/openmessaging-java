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

import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.annotation.Optional;
import io.openmessaging.exception.OMSDestinationException;
import io.openmessaging.exception.OMSRuntimeException;
import io.openmessaging.exception.OMSSecurityException;
import io.openmessaging.exception.OMSTimeOutException;
import io.openmessaging.extension.QueueMetaData;
import io.openmessaging.message.Message;
import java.util.Collection;
import java.util.List;

/**
 * A {@code PullConsumer} pulls messages from the specified queue, and supports submit the consume result by
 * acknowledgement.
 *
 * @version OMS 1.0.0
 * @see MessagingAccessPoint#createPullConsumer()
 * @since OMS 1.0.0
 */
public interface PullConsumer extends Consumer {

    /**
     * Bind the {@code Consumer} to a collection of queue in pull model, user can use {@link PullConsumer#receive(long)}
     * to get messages from a collection of queue.
     * <p>
     *
     * @param queueNames a collection of queues.
     * @throws OMSSecurityException when have no authority to bind to this queue.
     * @throws OMSDestinationException when have no given destination in the server.
     * @throws OMSRuntimeException when the {@code Producer} fails to send the message due to some internal error.
     */
    void bindQueue(Collection<String> queueNames);

    /**
     * Unbind the {@code Consumer} from a collection of queues.
     * <p>
     * After the success call, this consumer won't receive new message from the specified queue any more.
     *
     * @param queueNames a collection of queues.
     */
    void unbindQueue(Collection<String> queueNames);

    /**
     * Receives the next message from the attached queues of this consumer.
     * <p>
     * This call blocks indefinitely until a message is arrives, the timeout expires, or until this {@code PullConsumer}
     * is shut down.
     *
     * @return the next message received from the attached queues, or null if the consumer is concurrently shut down or
     * the timeout expires
     * @throws OMSRuntimeException if the consumer fails to pull the next message due to some internal error.
     */
    Message receive();

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
    Message receive(long timeout);

    /**
     * Receives the next message from the which bind queue,partition and receiptId of this consumer in pull model.
     * <p>
     * This call blocks indefinitely until a message is arrives, the timeout expires, or until this {@code PullConsumer}
     * is shut down.
     *
     * @param queueName receive message from which queueName in Message Queue.
     * @param queueMetaData receive message from which partition in Message Queue.
     * @param messageReceipt receive message from which receipt position in Message Queue.
     * @param timeout receive message will blocked at most <code>timeout</code> milliseconds.
     * @return the next message received from the bind queues, or null if the consumer is concurrently shut down.
     * @throws OMSSecurityException when have no authority to receive messages from this queue.
     * @throws OMSTimeOutException when the given timeout elapses before the send operation completes.
     * @throws OMSRuntimeException when the {@code Producer} fails to send the message due to some internal error.
     */
    @Optional
    Message receive(String queueName, QueueMetaData queueMetaData, MessageReceipt messageReceipt, long timeout);

    /**
     * Receive message in asynchronous way. This call doesn't block user's thread, and user's message resolve logic
     * should implement in the {@link MessageListener}.
     * <p>
     *
     * @param timeout receive messages will blocked at most <code>timeout</code> milliseconds.
     * @return the next batch messages received from the bind queues, or null if the consumer is concurrently shut down.
     * @throws OMSSecurityException when have no authority to receive messages from this queue.
     * @throws OMSTimeOutException when the given timeout elapses before the send operation completes.
     * @throws OMSRuntimeException when the {@code Producer} fails to send the message due to some internal error.
     */
    List<Message> batchReceive(long timeout);

    /**
     * Receive message in asynchronous way. This call doesn't block user's thread, and user's message resolve logic
     * should implement in the {@link MessageListener}.
     * <p>
     *
     * @param queueName receive message from which queueName in Message Queue.
     * @param queueMetaData receive message from which partition in Message Queue.
     * @param messageReceipt receive message from which receipt position in Message Queue.
     * @param timeout receive messages will blocked at most <code>timeout</code> milliseconds.
     * @return the next batch messages received from the bind queues, or null if the consumer is concurrently shut down.
     * @throws OMSSecurityException when have no authority to receive messages from this queue.
     * @throws OMSTimeOutException when the given timeout elapses before the send operation completes.
     * @throws OMSRuntimeException when the {@code Producer} fails to send the message due to some internal error.
     */
    @Optional
    List<Message> batchReceive(String queueName, QueueMetaData queueMetaData, MessageReceipt messageReceipt,
        long timeout);

    /**
     * Acknowledges the specified and consumed message with the unique message receipt handle.
     * <p>
     * Messages that have been received but not acknowledged may be redelivered.
     *
     * @param receiptHandle the receipt handle associated with the consumed message
     * @throws OMSRuntimeException if the consumer fails to acknowledge the messages due to some internal error.
     */
    void ack(MessageReceipt receiptHandle);

}