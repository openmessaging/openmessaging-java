/*
 * Copyright 2017 OpenMessaging
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.openmessaging.api.producer;

import io.openmessaging.api.Message;
import io.openmessaging.api.MessageBuilder;
import io.openmessaging.api.MessageId;
import io.openmessaging.exception.OMSException;
import java.util.concurrent.CompletableFuture;

/**
 * An OMS Producer that produces messages to a given topic.
 */
public interface Producer extends AutoCloseable {
    /**
     * @return the topic which producer is publishing to
     */
    String getTopic();

    /**
     * @return the producer name which could have been assigned by the system or specified by the client
     */
    String getProducerName();

    /**
     * Send a message
     *
     * @param message
     *            a message
     * @return the message id assigned to the published message
     * @throws OMSException
     */
    MessageId send(Message message) throws OMSException;

    /**
     * Send a message asynchronously
     * <p>
     * When the returned {@link CompletableFuture} is marked as completed successfully, the provided message will
     * contain the {@link MessageId} assigned by the broker to the published message.
     * <p>
     * Example:
     *
     * <pre>
     * <code>Message msg = MessageBuilder.create().setContent(myContent).build();
     * producer.sendAsync(msg).thenRun(v -> {
     *    System.out.println("Published message: " + msg.getMessageId());
     * }).exceptionally(e -> {
     *    // Failed to publish
     * });</code>
     * </pre>
     * <p>
     * @param message
     *            a message
     * @return a future that can be used to track when the message will have been safely persisted
     */
    CompletableFuture<MessageId> sendAsync(Message message);

    /**
     * Get the last sequence id that was published by this producer.
     * <p>
     * This represent either the automatically assigned or custom sequence id (set on the {@link MessageBuilder}) that
     * was published and acknowledged by the broker.
     * <p>
     * After recreating a producer with the same producer name, this will return the last message that was published in
     * the previous producer session, or -1 if there no message was ever published.
     *
     * @return the last sequence id published by this producer
     */
    long getLastSequenceId();

    /**
     * Get statistics for the producer
     *
     * numMsgsSent : Number of messages sent in the current interval numBytesSent : Number of bytes sent in the current
     * interval numSendFailed : Number of messages failed to send in the current interval numAcksReceived : Number of
     * acks received in the current interval totalMsgsSent : Total number of messages sent totalBytesSent : Total number
     * of bytes sent totalSendFailed : Total number of messages failed to send totalAcksReceived: Total number of acks
     * received
     *
     * @return statistic for the producer or null if ProducerStats is disabled.
     */
    ProducerStats getStats();

    /**
     * Close the producer and releases resources allocated.
     *
     * No more writes will be accepted from this producer. Waits until all pending write request are persisted. In case
     * of errors, pending writes will not be retried.
     */
    @Override
    void close();

    /**
     * Close the producer and releases resources allocated.
     *
     * No more writes will be accepted from this producer. Waits until all pending write request are persisted. In case
     * of errors, pending writes will not be retried.
     *
     * @return a future that can used to track when the producer has been closed
     */
    CompletableFuture<Void> closeAsync();

}
