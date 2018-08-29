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

package io.openmessaging.producer;

import io.openmessaging.Future;
import io.openmessaging.FutureListener;
import io.openmessaging.Message;
import io.openmessaging.MessageFactory;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.ServiceLifecycle;
import io.openmessaging.exception.OMSDestinationException;
import io.openmessaging.exception.OMSMessageFormatException;
import io.openmessaging.exception.OMSRuntimeException;
import io.openmessaging.exception.OMSSecurityException;
import io.openmessaging.exception.OMSTimeOutException;
import io.openmessaging.exception.OMSTransactionException;
import io.openmessaging.interceptor.ProducerInterceptor;
import java.util.List;

/**
 * A {@code Producer} is a simple object used to send messages on behalf of a {@code MessagingAccessPoint}. An instance
 * of {@code Producer} is created by calling the {@link MessagingAccessPoint#createProducer()} method.
 * <p>
 * It provides various {@code send} methods to send a message to a specified destination, which is a {@code Queue} in
 * OMS.
 * <p>
 * {@link Producer#send(Message)} means send a message to the destination synchronously, the calling thread will block
 * until the send request complete.
 * <p>
 * {@link Producer#sendAsync(Message)} means send a message to the destination asynchronously, the calling thread won't
 * block and will return immediately. Since the send call is asynchronous it returns a {@link Future} for the send
 * result.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface Producer extends MessageFactory, ServiceLifecycle {

    /**
     * Sends a message to the specified destination synchronously, the destination should be preset to {@link
     * Message#headers()}, other header fields as well.
     *
     * @param message a message will be sent.
     * @return the successful {@code SendResult}.
     * @throws OMSSecurityException when have no authority to send messages to a given destination.
     * @throws OMSMessageFormatException when an invalid message is specified.
     * @throws OMSTimeOutException when the given timeout elapses before the send operation completes.
     * @throws OMSDestinationException when have no given destination in the server.
     * @throws OMSRuntimeException when the {@code Producer} fails to send the message due to some internal error.
     */
    SendResult send(Message message);

    /**
     * Sends a message to the specified destination asynchronously, the destination should be preset to {@link
     * Message#headers()}, other header fields as well.
     * <p>
     * The returned {@code Promise} will have the result once the operation completes, and the registered {@code
     * FutureListener} will be notified, either because the operation was successful or because of an error.
     *
     * @param message a message will be sent.
     * @return the {@code Promise} of an asynchronous message send operation.
     * @see Future
     * @see FutureListener
     */
    Future<SendResult> sendAsync(Message message);

    /**
     * <p>
     * There is no {@code Promise} related or {@code RuntimeException} thrown. The calling thread doesn't care about the
     * send result and also have no context to get the result.
     *
     * @param message a message will be sent.
     */
    void sendOneway(Message message);

    /**
     * <p>
     * Send batch messages to server.
     *
     * @param messages messages to be sent.
     */
    void send(List<Message> messages);

    /**
     * Adds a {@code ProducerInterceptor} to intercept send operations of producer.
     *
     * @param interceptor a producer interceptor.
     */
    void addInterceptor(ProducerInterceptor interceptor);

    /**
     * Removes a {@code ProducerInterceptor}.
     *
     * @param interceptor a producer interceptor will be removed.
     */
    void removeInterceptor(ProducerInterceptor interceptor);

    /**
     * Sends a transactional message to the specified destination synchronously, the destination should be preset to
     * {@link Message#headers()}, other header fields as well.
     * <p>
     * A transactional send result will be exposed to consumer if this prepare message send success, and then, you can
     * execute your local transaction, when local transaction execute success, users can use {@link
     * TransactionalResult#commit()} to commit prepare message,otherwise can use {@link TransactionalResult#rollback()}
     * to roll back this prepare message.
     * </p>
     *
     * @param message a prepare transactional message will be sent.
     * @return the successful {@code SendResult}.
     * @throws OMSSecurityException when have no authority to send messages to a given destination.
     * @throws OMSMessageFormatException when an invalid message is specified.
     * @throws OMSTimeOutException when the given timeout elapses before the send operation completes.
     * @throws OMSDestinationException when have no given destination in the server.
     * @throws OMSRuntimeException when the {@code Producer} fails to send the message due to some internal error.
     * @throws OMSTransactionException when used normal producer which haven't register {@link TransactionStateCheckListener}.
     */
    TransactionalResult prepare(Message message);
}