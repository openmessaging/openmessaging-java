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

package io.openmessaging.pulsar;


import io.openmessaging.api.Client;
import io.openmessaging.api.MessageId;
import io.openmessaging.api.consumer.Consumer;
import io.openmessaging.api.consumer.ConsumerConfiguration;
import io.openmessaging.api.producer.Producer;
import io.openmessaging.api.producer.ProducerConfiguration;
import io.openmessaging.api.reader.Reader;
import io.openmessaging.api.reader.ReaderConfiguration;
import io.openmessaging.exception.OMSException;
import java.util.concurrent.CompletableFuture;
import org.apache.pulsar.client.api.PulsarClientException;

class PulsarClient implements Client {

    private final org.apache.pulsar.client.api.PulsarClient client;

    PulsarClient(String url) throws PulsarClientException {
        this.client = org.apache.pulsar.client.api.PulsarClient.create(url);
    }

    @Override
    public Producer createProducer(String topic, ProducerConfiguration conf) throws OMSException {
        return null;
    }

    @Override
    public CompletableFuture<Producer> createProducerAsync(String topic, ProducerConfiguration conf) {
        return null;
    }

    @Override
    public Consumer subscribe(String topic, String subscription, ConsumerConfiguration conf) throws OMSException {
        return null;
    }

    @Override
    public CompletableFuture<Consumer> subscribeAsync(String topic, String subscription, ConsumerConfiguration conf) {
        return null;
    }

    @Override
    public Reader createReader(String topic, MessageId startMessageId, ReaderConfiguration conf) throws OMSException {
        return null;
    }

    @Override
    public CompletableFuture<Reader> createReaderAsync(String topic, MessageId startMessageId, ReaderConfiguration conf) {
        return null;
    }

    @Override
    public CompletableFuture<Void> closeAsync() {
        return null;
    }

    @Override
    public void close() throws Exception {

    }
}
