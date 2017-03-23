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

import io.openmessaging.exception.OMSResourceNotExistException;
import io.openmessaging.exception.OMSRuntimeException;
import java.util.concurrent.TimeUnit;

/**
 * A {@code PullConsumer} object to pull messages from a queue proactively,
 * and is responsible for submit the consume result to a {@code MessagingAccessPoint}.
 * <p>
 * There are two ways to submit the consume result, see {@link PullConsumerAck} and {@link PullConsumerCursor}.
 *
 * @author vintagewang@apache.org
 * @author yukon@apache.org
 *
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface PullConsumer extends ServiceLifecycle{
    /**
     * Returns the properties of this {@code PullConsumer} instance.
     * Changes to the return {@code KeyValue} are not reflected in physical {@code PullConsumer},
     * and use {@link ResourceManager#setConsumerProperties(String, KeyValue)} to modify.
     *
     * @return the properties
     */
    KeyValue properties();

    /**
     * Pulls the next message produced for this {@code PullConsumer}.
     * <P>
     * This call blocks indefinitely until a message is produced or until this {@code PullConsumer} is shut down.
     *
     * @return the next message produced for this {@code PullConsumer}, or null if this {@code PullConsumer} is
     * concurrently shut down
     * @throws OMSRuntimeException if this {@code PullConsumer} fails to pull the next message due to some internal
     * error.
     */
    Message pull();

    /**
     * Pulls the next message produced for this {@code PullConsumer}, using the specified properties.
     * <P>
     * This call blocks indefinitely until a message is produced or until this {@code PullConsumer} is shut down.
     *
     * @param properties the specified properties
     * @return the next message produced for this {@code PullConsumer}, or null if this {@code PullConsumer} is
     * concurrently shut down
     * @throws OMSRuntimeException if this {@code PullConsumer} fails to pull the next message due to some internal
     * error.
     */
    Message pull(final KeyValue properties);

    /**
     * Pulls the next message that arrives within the specified timeout interval.
     * <P>
     * This call blocks until a message arrives, the timeout expires, or this {@code PullConsumer} is shut down.
     * A {@code timeout} of zero never expires, and the call blocks indefinitely.
     *
     * @param timeout how long to wait before giving up, in units of {@code unit}
     * @param unit a {@code TimeUnit} determining how to interpret the {@code timeout} parameter
     * @return the next message produced for this {@code PullConsumer}, or null if the timeout expires or this {@code
     * PullConsumer} is concurrently closed
     * @throws OMSRuntimeException if this {@code PullConsumer} fails to receive the next message due to some internal
     * error.
     */
    Message pull(long timeout, TimeUnit unit);

    /**
     * Pulls the next message that arrives within the specified timeout interval, using the specified properties.
     * <P>
     * This call blocks until a message arrives, the timeout expires, or this {@code PullConsumer} is shut down.
     * A {@code timeout} of zero never expires, and the call blocks indefinitely.
     *
     * @param timeout how long to wait before giving up, in units of {@code unit}
     * @param unit a {@code TimeUnit} determining how to interpret the {@code timeout} parameter
     * @param properties the specified properties
     * @return the next message produced for this {@code PullConsumer}, or null if the timeout expires or this {@code
     * PullConsumer} is concurrently closed
     * @throws OMSRuntimeException if this {@code PullConsumer} fails to receive the next message due to some internal
     * error.
     */
    Message pull(long timeout, TimeUnit unit, final KeyValue properties);

    /**
     * Pulls the next message if one is immediately available.
     *
     * @return the next message produced for this {@code PullConsumer}, or null if one is not available
     * @throws OMSRuntimeException if this {@code PullConsumer} fails to pull the next message due to some internal
     * error.
     */
    Message pullNoWait();

    /**
     * Pulls the next message if one is immediately available, using the specified properties.
     *
     * @return the next message produced for this {@code PullConsumer}, or null if one is not available
     * @param properties the specified properties
     * @throws OMSRuntimeException if this {@code PullConsumer} fails to pull the next message due to some internal
     * error.
     */
    Message pullNoWait(final KeyValue properties);


    /**
     * Attaches the {@code PushConsumer} to a specified queue, with a {@code MessageListener}.
     * {@link MessageListener#onMessage(Message, OnMessageContext)} will be called when new
     * delivered message is coming.
     *
     * @param queueName a specified queue
     *
     * @throws OMSResourceNotExistException if the specified queue is not exists
     */
    PullConsumer attachQueue(final String queueName) throws OMSResourceNotExistException;
}
