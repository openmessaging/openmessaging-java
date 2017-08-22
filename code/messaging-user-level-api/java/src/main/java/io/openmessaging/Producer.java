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
import io.openmessaging.exception.OMSTimeOutException;
import io.openmessaging.interceptor.ProducerInterceptor;

/**
 * A {@code Producer} is a simple object used to send messages on behalf
 * of a {@code MessagingAccessPoint}. An instance of {@code Producer} is
 * created by calling the {@link MessagingAccessPoint#createProducer()} method.
 * It provides various {@code send} methods to send a message to a specified destination.
 * A destination can be a {@link MessageHeaderBuiltinKeys#Topic} or a {@link MessageHeaderBuiltinKeys#Queue}.
 * <p>
 *
 * {@link Producer#send(Message)} means send a message to destination synchronously,
 * the calling thread will block until the send request complete.
 * <p>
 * {@link Producer#sendAsync(Message)} means send a message to destination asynchronously,
 * the calling thread won't block and will return immediately. Since the send call is asynchronous
 * it returns a {@link Promise} for the send result.
 * <p>
 * {@link Producer#sendOneway(Message)} means send a message to destination in one way,
 * the calling thread won't block and will return immediately. The caller won't care about
 * the send result, while the server has no responsibility for returning the result.
 *
 * @author vintagewang@apache.org
 * @author yukon@apache.org
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface Producer extends MessageFactory, ServiceLifecycle {
    /**
     * Returns the attributes of this {@code Producer} instance.
     * Changes to the return {@code KeyValue} are not reflected in physical {@code Producer},
     * and use {@link ResourceManager#setProducerProperties(String, KeyValue)} to modify.
     * <p>
     * There are some standard attributes defined by OMS for {@code Producer}:
     * <ul>
     * <li> {@link OMSBuiltinKeys#PRODUCER_ID}, the unique producer id for a producer instance.
     * <li> {@link OMSBuiltinKeys#OPERATION_TIMEOUT}, the default timeout period for operations of {@code Producer}.
     * </ul>
     *
     * @return the attributes
     */
    KeyValue properties();

    /**
     * Sends a message to the specified destination synchronously, the destination should be preset to
     * {@link MessageHeaderBuiltinKeys}, other header fields as well.
     *
     * @param message a message will be sent
     * @return the successful {@code SendResult}
     * @throws OMSMessageFormatException if an invalid message is specified.
     * @throws OMSTimeOutException if the given timeout elapses before the send operation completes
     * @throws OMSRuntimeException if the {@code Producer} fails to send the message due to some internal error.
     */
    SendResult send(Message message);

    /**
     * Sends a message to the specified destination synchronously, using the specified attributes, the destination
     * should be preset to {@link MessageHeaderBuiltinKeys}, other header fields as well.
     *
     * @param message a message will be sent
     * @param properties the specified attributes
     * @return the successful {@code SendResult}
     * @throws OMSMessageFormatException if an invalid message is specified.
     * @throws OMSTimeOutException if the given timeout elapses before the send operation completes
     * @throws OMSRuntimeException if the {@code Producer} fails to send the message due to some internal error.
     */
    SendResult send(Message message, KeyValue properties);

    SendResult send(Message message, LocalTransactionBranchExecutor branchExecutor, Object arg, KeyValue properties);

    /**
     * Sends a message to the specified destination asynchronously, the destination should be preset to
     * {@link MessageHeaderBuiltinKeys}, other header fields as well.
     * <p>
     * The returned {@code Promise} will have the result once the operation completes, and the registered
     * {@code PromiseListener} will be notified, either because the operation was successful or because of an error.
     *
     * @param message a message will be sent
     * @return the {@code Promise} of an asynchronous message send operation.
     * @see Promise
     * @see PromiseListener
     */
    Promise<SendResult> sendAsync(Message message);

    /**
     * Sends a message to the specified destination asynchronously, using the specified attributes, the destination
     * should be preset to {@link MessageHeaderBuiltinKeys}, other header fields as well.
     * <p>
     * The returned {@code Promise} will have the result once the operation completes, and the registered
     * {@code PromiseListener} will be notified, either because the operation was successful or because of an error.
     *
     * @param message a message will be sent
     * @param properties the specified attributes
     * @return the {@code Promise} of an asynchronous message send operation.
     * @see Promise
     * @see PromiseListener
     */
    Promise<SendResult> sendAsync(Message message, KeyValue properties);

    /**
     * Sends a message to the specified destination in one way, the destination should be preset to
     * {@link MessageHeaderBuiltinKeys}, other header fields as well.
     * <p>
     * There is no {@code Promise} related or {@code RuntimeException} thrown. The calling thread doesn't
     * care about the send result and also have no context to get the result.
     *
     * @param message a message will be sent
     */
    void sendOneway(Message message);

    /**
     * Sends a message to the specified destination in one way, using the specified attributes, the destination
     * should be preset to {@link MessageHeaderBuiltinKeys}, other header fields as well.
     * <p>
     * There is no {@code Promise} related or {@code RuntimeException} thrown. The calling thread doesn't
     * care about the send result and also have no context to get the result.
     *
     * @param message a message will be sent
     * @param properties the specified userHeaders
     */
    void sendOneway(Message message, KeyValue properties);

    BatchMessageSender createSequenceBatchMessageSender();

    void addInterceptor(ProducerInterceptor interceptor);

    void removeInterceptor(ProducerInterceptor interceptor);

}