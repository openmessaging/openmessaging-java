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
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.openmessaging.api;

import java.util.concurrent.ExecutorService;

public interface OMSProducer<T> extends Admin{

    /**
     * Sends a message to the specified destination synchronously, the destination should be preset to {@link
     * Message#setTopic(String)}, other header fields as well.
     *
     * @param message a message will be sent.
     * @return the successful {@code SendResult}.
     * @throws OMSSecurityException when have no authority to send messages to a given destination.
     * @throws OMSMessageFormatException when an invalid message is specified.
     * @throws OMSTimeOutException when the given timeout elapses before the send operation completes.
     * @throws OMSDestinationException when have no given destination in the server.
     * @throws OMSRuntimeException when the {@code Producer} fails to send the message due to some internal error.
     */
    SendResult send(final Message message);

    /**
     * <p>
     * There is no {@code Promise} related or {@code RuntimeException} thrown. The calling thread doesn't care about the
     * send result and also have no context to get the result.
     *
     * @param message a message will be sent.
     */
    void sendOneway(final Message message);

    /**
     * Sends a message to the specified destination asynchronously, the destination should be preset to {@link
     * Message#setTopic(String)}, other header fields as well.
     * <p>
     * The returned {@code Promise} will have the result once the operation completes, and the registered {@link
     * SendCallback} will be invoked, either because the operation was successful or because of an error.
     *
     * @param message a message will be sent.
     * @param sendCallback {@link SendCallback}
     */
    void sendAsync(final Message message, final SendCallback sendCallback);

    /**
     * Set call back excutor
     * @param callbackExecutor
     */
    void setCallbackExecutor(final ExecutorService callbackExecutor);




    /**
     * Sends a message to the specified destination synchronously, the destination should be preset to {@link
     * Message#setTopic(String)}, other header fields as well.
     *
     * @param message a message will be sent.
     * @return the successful {@code SendResult}.
     * @throws OMSSecurityException when have no authority to send messages to a given destination.
     * @throws OMSMessageFormatException when an invalid message is specified.
     * @throws OMSTimeOutException when the given timeout elapses before the send operation completes.
     * @throws OMSDestinationException when have no given destination in the server.
     * @throws OMSRuntimeException when the {@code Producer} fails to send the message due to some internal error.
     */
    SendResult send(final T t);

    /**
     * <p>
     * There is no {@code Promise} related or {@code RuntimeException} thrown. The calling thread doesn't care about the
     * send result and also have no context to get the result.
     *
     * @param t a message will be sent.
     */
    void sendOneway(final T t);

    /**
     * Sends a message to the specified destination asynchronously, the destination should be preset to {@link
     * Message#setTopic(String)}, other header fields as well.
     * <p>
     * The returned {@code Promise} will have the result once the operation completes, and the registered {@link
     * SendCallback} will be invoked, either because the operation was successful or because of an error.
     *
     * @param t a message will be sent.
     * @param sendCallback {@link SendCallback}
     */
    void sendAsync(final T t, final SendCallback sendCallback);
}
