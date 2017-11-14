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

package io.openmessaging.examples.consumer;

import io.openmessaging.ClientDriverManager;
import io.openmessaging.api.Client;
import io.openmessaging.api.Message;
import io.openmessaging.api.MessageId;
import io.openmessaging.api.consumer.Consumer;
import io.openmessaging.api.consumer.ConsumerConfiguration;
import io.openmessaging.api.consumer.MessageListener;
import io.openmessaging.api.reader.Reader;
import io.openmessaging.api.reader.ReaderConfiguration;

public class ConsumerExample {

    public static void main(String[] args) throws Exception {
        final Client client = ClientDriverManager.createClient(
            "oms:rocketmq://localhost:10911/us-east:namespace");

        ConsumerConfiguration conf = new ConsumerConfiguration();
        String topic = "path/to/topic";
        String subscriberId = "poll_consumer";
        final Consumer pollConsumer = client.subscribe(topic, subscriberId, conf);

        // poll messages
        Message msg;
        while ((msg = pollConsumer.receive()) != null) {
            System.out.println("Received message : " + msg.getMessageId());

            // process the messages and ack

            pollConsumer.ack(msg);
        }

        // message listener
        final String pushSubId = "push_consumer";
        final Consumer pushConsumer = client.subscribe(topic, pushSubId, conf);
        pushConsumer.receive((consumer, message) -> {
            System.out.println("Received message : " + message.getMessageId());
            // process the messages and ack
            consumer.ackAsync(message);
        });

        // streaming consumer
        final String streamingSubId = "streaming_consumer";
        final Consumer streamingConsumer = client.subscribe(topic, streamingSubId, conf);
        int idx = 0;
        while ((msg = pollConsumer.receive()) != null) {
            System.out.println("Received message : " + msg);
            // persist the consumer offset every 100 messages
            ++idx;
            if (idx % 100 == 0) {
                streamingConsumer.ackCumulative(msg.getMessageId());
            }
        }

        // reader: streaming consumer without cursors
        final ReaderConfiguration readerConf = new ReaderConfiguration();
        final Reader reader = client.createReader(topic, MessageId.EARLIEST, readerConf);
        while ((msg = reader.readNext()) != null) {
            System.out.println("Received message : " + msg);
        }

    }

}
