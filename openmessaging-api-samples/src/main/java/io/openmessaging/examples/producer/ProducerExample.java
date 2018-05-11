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

package io.openmessaging.examples.producer;

import io.openmessaging.ClientDriverManager;
import io.openmessaging.api.Client;
import io.openmessaging.api.ClientDriver;
import io.openmessaging.api.Message;
import io.openmessaging.api.MessageBuilder;
import io.openmessaging.api.MessageId;
import io.openmessaging.api.producer.Producer;
import io.openmessaging.api.producer.ProducerConfiguration;
import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;

/**
 * Example: a producer produces messages.
 */
public class ProducerExample {

    private static final Charset UTF_8 = Charset.forName("UTF-8");

    public static void main(String[] args) throws Exception {
        final Client client = ClientDriverManager.createClient(
            "oms://rocketmq://localhost:10911/us-east:namespace");

        final String topic = "path/to/topic";

        final ProducerConfiguration producerConf = new ProducerConfiguration();
        final Producer producer = client.createProducer(topic, producerConf);

        // write message sync
        MessageBuilder builder = null; // provide the builder from
        Message msg =
            builder.setContent("Hellow OMS".getBytes(UTF_8)).build();

        MessageId msgId = producer.send(msg);
        System.out.println("Send sync message OK, message id is " + msgId);

        // write message async
        CompletableFuture<MessageId> result = producer.sendAsync(msg);
        result.whenComplete((mid, cause) -> {
            if (null == cause) {
                System.out.println("Send async message OK, message id is " + mid);
            } else {
                System.out.println("Send async message Failed, cause : " + cause);
            }
        });

        // write message oneway
        producer.sendAsync(msg);
        System.out.println("Send oneway message OK");
    }

}
