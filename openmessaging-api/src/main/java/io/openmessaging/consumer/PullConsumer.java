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

/**
 * A {@code PullConsumer} pulls messages from the specified queue,
 * and supports submit the consume result by acknowledgement.
 *
 * @version OMS 1.0.0
 * @see MessagingAccessPoint#createPullConsumer()
 * @since OMS 1.0.0
 */
public interface PullConsumer extends ServiceLifecycle {
    /**
     * Returns the attributes of this {@code PullConsumer} instance.
     * Changes to the return {@code KeyValue} are not reflected in physical {@code PullConsumer}.
     * <p>
     * There are some standard attributes defined by OMS for {@code PullConsumer}:
     * <ul>
     * <li> {@link OMSBuiltinKeys#CONSUMER_ID}, the unique consumer id for a consumer instance.
     * <li> {@link OMSBuiltinKeys#OPERATION_TIMEOUT}, the default timeout period for operations of {@code PullConsumer}.
     * </ul>
     *
     * @return the attributes
     */
    KeyValue attributes();

    /**
     * Attaches the {@code PullConsumer} to a specified queue.
     *
     * @param queueName a specified queue
     * @return this {@code PullConsumer} instance
     * @throws OMSRuntimeException if this {@code PullConsumer} fails to attach the specified queue due to some internal
     * error.
     */
    PullConsumer attachQueue(String queueName);

    /**
     * Attaches the {@code PullConsumer} to a specified queue.
     *
     * @param queueName a specified queue
     * @param attributes some specified attributes
     * @return this {@code PullConsumer} instance
     * @throws OMSRuntimeException if this {@code PullConsumer} fails to attach the specified queue due to some internal
     * error.
     */
    PushConsumer attachQueue(String queueName, KeyValue attributes);

    /**
     * Detaches the {@code PullConsumer} from a specified queue.
     * After the success call, this consumer won't receive new message
     * from the specified queue any more.
     *
     * @param queueName a specified queue
     * @return this {@code PushConsumer} instance
     */
    void detachQueue(String queueName);

    /**
     * Pulls the next message produced for this {@code PullConsumer}.
     * <p>
     * This call blocks indefinitely until a message is arrives, the timeout expires,
     * or until this {@code PullConsumer} is shut down.
     *
     * @return the next message produced for this {@code PullConsumer}, or null if this {@code PullConsumer} is
     * concurrently shut down
     * @throws OMSRuntimeException if this {@code PullConsumer} fails to pull the next message due to some internal
     * error.
     */
    Message receive();

    /**
     * Pulls the next message produced for this {@code PullConsumer}, using the specified attributes.
     * <p>
     * This call blocks indefinitely until a message is arrives, the timeout expires,
     * or until this {@code PullConsumer} is shut down.
     *
     * @param attributes the specified attributes
     * @return the next message produced for this {@code PullConsumer}, or null if this {@code PullConsumer} is
     * concurrently shut down
     * @throws OMSRuntimeException if this {@code PullConsumer} fails to pull the next message due to some internal
     * error.
     */
    Message receive(KeyValue attributes);

    /**
     * Acknowledges the specified and consumed message with unique message id.
     * <p>
     * Messages that have been received but not acknowledged may be redelivered.
     *
     * @throws OMSRuntimeException if the consumer fails to acknowledge the messages due to some internal error.
     */
    void ack(String messageId);

    /**
     * Acknowledges the specified and consumed message with the specified attributes.
     * <p>
     * Messages that have been received but not acknowledged may be redelivered.
     *
     * @throws OMSRuntimeException if the consumer fails to acknowledge the messages due to some internal error.
     */
    void ack(String messageId, KeyValue attributes);
}