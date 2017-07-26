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

import io.openmessaging.exception.OMSMessageFormatException;
import io.openmessaging.exception.OMSRuntimeException;

/**
 * A {@code SequenceProducer} is a simple object used to send messages on behalf
 * of a {@code MessagingAccessPoint}.
 * <p>
 * An instance of {@code SequenceProducer} is created by calling the
 * {@link MessagingAccessPoint#createSequenceProducer()} method.
 * <p>
 * It provides a group way to send batch message to a specified destination.
 * A destination can be a {@link MessageHeaderBuiltinKeys#Topic} or a {@link MessageHeaderBuiltinKeys#Queue}.
 *
 * @author vintagewang@apache.org
 * @author yukon@apache.org
 *
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface SequenceProducer extends MessageFactory, ServiceLifecycle {
    /**
     * Returns the attributes of this {@code SequenceProducer} instance.
     * Changes to the return {@code KeyValue} are not reflected in physical {@code Producer},
     * and use {@link ResourceManager#setProducerProperties(String, KeyValue)} to modify.
     * <p>
     * There are some standard attributes defined by OMS for {@code SequenceProducer}:
     * <ul>
     * <li> {@link OMSBuiltinKeys#PRODUCER_ID}, the unique producer id for a producer instance.
     * <li> {@link OMSBuiltinKeys#OPERATION_TIMEOUT}, the default timeout period for
     * operations of {@code SequenceProducer}.
     * </ul>
     *
     * @return the attributes
     */
    KeyValue properties();

    /**
     * Sends a message to the specified destination, the destination should be preset to
     * {@link MessageHeaderBuiltinKeys}, other header fields as well.
     * <p>
     * There is no {@code Promise} related. The calling thread doesn't
     * care about the send result and also have no context to get the result.
     * <p>
     * This message can't be consumed util it is committed.
     *
     * @param message a message will be sent
     * @throws OMSMessageFormatException if an invalid message is specified.
     */
    void send(Message message);

    /**
     * Sends a message to the specified destination, using the specified attributes, the destination
     * should be preset to {@link MessageHeaderBuiltinKeys}, other header fields as well.
     * <p>
     * There is no {@code Promise} related. The calling thread doesn't
     * care about the send result and also have no context to get the result.
     *
     * @param message a message will be sent
     * @param properties the specified attributes
     * @throws OMSMessageFormatException if an invalid message is specified.
     */
    void send(Message message, KeyValue properties);

    /**
     * Commits all the sent messages since last commit or rollback operation,
     * and all the messages should has the same destination.
     *
     * @throws OMSRuntimeException if the commit operation failed
     */
    void commit();

    /**
     * Discards all the sent messages since last commit or rollback operation,
     * and all the messages should has the same destination.
     *
     * @throws OMSRuntimeException if the rollback operation failed
     */
    void rollback();
}
