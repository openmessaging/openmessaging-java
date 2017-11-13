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

package io.openmessaging.api;

import io.openmessaging.api.consumer.Consumer;
import io.openmessaging.api.consumer.ConsumerConfiguration;
import io.openmessaging.api.producer.Producer;
import io.openmessaging.api.producer.ProducerConfiguration;
import io.openmessaging.api.reader.Reader;
import io.openmessaging.api.reader.ReaderConfiguration;
import io.openmessaging.exception.OMSException;
import java.util.concurrent.CompletableFuture;

/**
 * An OMS client that talks to OMS messaging services.
 */
public interface Client extends AutoCloseable {

    /**
     * Create a producer with default {@link ProducerConfiguration} for publishing on a specific topic
     *
     * @param topic
     *            The name of the topic where to produce
     * @return The producer object
     * @throws OMSException
     *             if the client encounters an exception
     */
    Producer createProducer(String topic) throws OMSException;

    /**
     * Asynchronously create a producer with default {@link ProducerConfiguration} for publishing on a specific topic
     *
     * @param topic
     *            The name of the topic where to produce
     * @return Future of the asynchronously created producer object
     */
    CompletableFuture<Producer> createProducerAsync(String topic);

    /**
     * Create a producer with given {@code ProducerConfiguration} for publishing on a specific topic
     *
     * @param topic
     *            The name of the topic where to produce
     * @param conf
     *            The {@code ProducerConfiguration} object
     * @return The producer object
     * @throws OMSException
     *             if it was not possible to create the producer
     * @throws InterruptedException
     */
    Producer createProducer(String topic, ProducerConfiguration conf) throws OMSException;

    /**
     * Asynchronously create a producer with given {@code ProducerConfiguration} for publishing on a specific topic
     *
     * @param topic
     *            The name of the topic where to produce
     * @param conf
     *            The {@code ProducerConfiguration} object
     * @return Future of the asynchronously created producer object
     */
    CompletableFuture<Producer> createProducerAsync(String topic, ProducerConfiguration conf);

    /**
     * Subscribe to the given topic and subscription combination with default {@code ConsumerConfiguration}
     *
     * @param topic
     *            The name of the topic
     * @param subscription
     *            The name of the subscription
     * @return The {@code Consumer} object
     * @throws OMSException
     * @throws InterruptedException
     */
    Consumer subscribe(String topic, String subscription) throws OMSException;

    /**
     * Asynchronously subscribe to the given topic and subscription combination using default
     * {@code ConsumerConfiguration}
     *
     * @param topic
     *            The topic name
     * @param subscription
     *            The subscription name
     * @return Future of the {@code Consumer} object
     */
    CompletableFuture<Consumer> subscribeAsync(String topic, String subscription);

    /**
     * Subscribe to the given topic and subscription combination with given {@code ConsumerConfiguration}
     *
     * @param topic
     *            The name of the topic
     * @param subscription
     *            The name of the subscription
     * @param conf
     *            The {@code ConsumerConfiguration} object
     * @return The {@code Consumer} object
     * @throws OMSException
     */
    Consumer subscribe(String topic, String subscription, ConsumerConfiguration conf) throws OMSException;

    /**
     * Asynchronously subscribe to the given topic and subscription combination using given
     * {@code ConsumerConfiguration}
     *
     * @param topic
     *            The name of the topic
     * @param subscription
     *            The name of the subscription
     * @param conf
     *            The {@code ConsumerConfiguration} object
     * @return Future of the {@code Consumer} object
     */
    CompletableFuture<Consumer> subscribeAsync(String topic, String subscription, ConsumerConfiguration conf);

    /**
     * Create a topic reader with given {@code ReaderConfiguration} for reading messages from the specified topic.
     * <p>
     * The Reader provides a low-level abstraction that allows for manual positioning in the topic, without using a
     * subscription. Reader can only work on non-partitioned topics.
     * <p>
     * The initial reader positioning is done by specifying a message id. The options are:
     * <ul>
     * <li><code>MessageId.earliest</code> : Start reading from the earliest message available in the topic
     * <li><code>MessageId.latest</code> : Start reading from the end topic, only getting messages published after the
     * reader was created
     * <li><code>MessageId</code> : When passing a particular message id, the reader will position itself on that
     * specific position. The first message to be read will be the message next to the specified messageId.
     * </ul>
     *
     * @param topic
     *            The name of the topic where to read
     * @param startMessageId
     *            The message id where the reader will position itself. The first message returned will be the one after
     *            the specified startMessageId
     * @param conf
     *            The {@code ReaderConfiguration} object
     * @return The {@code Reader} object
     */
    Reader createReader(String topic, MessageId startMessageId, ReaderConfiguration conf) throws OMSException;

    /**
     * Asynchronously create a topic reader with given {@code ReaderConfiguration} for reading messages from the
     * specified topic.
     * <p>
     * The Reader provides a low-level abstraction that allows for manual positioning in the topic, without using a
     * subscription. Reader can only work on non-partitioned topics.
     * <p>
     * The initial reader positioning is done by specifying a message id. The options are:
     * <ul>
     * <li><code>MessageId.earliest</code> : Start reading from the earliest message available in the topic
     * <li><code>MessageId.latest</code> : Start reading from the end topic, only getting messages published after the
     * reader was created
     * <li><code>MessageId</code> : When passing a particular message id, the reader will position itself on that
     * specific position. The first message to be read will be the message next to the specified messageId.
     * </ul>
     *
     * @param topic
     *            The name of the topic where to read
     * @param startMessageId
     *            The message id where the reader will position itself. The first message returned will be the one after
     *            the specified startMessageId
     * @param conf
     *            The {@code ReaderConfiguration} object
     * @return Future of the asynchronously created producer object
     */
    CompletableFuture<Reader> createReaderAsync(String topic, MessageId startMessageId, ReaderConfiguration conf);

    /**
     * Asynchronously close the PulsarClient and release all the resources.
     *
     * All the producers and consumers will be orderly closed. Waits until all pending write request are persisted.
     */
    CompletableFuture<Void> closeAsync();

}
