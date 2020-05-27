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
package io.openmessaging.samples.consumer;

import io.openmessaging.api.Action;
import io.openmessaging.api.Consumer;
import io.openmessaging.api.GenericMessage;
import io.openmessaging.api.GenericMessageListener;
import io.openmessaging.api.MessageConsumeContext;
import io.openmessaging.api.MessagingAccessPoint;
import io.openmessaging.api.OMS;
import io.openmessaging.api.OMSBuiltinKeys;
import io.openmessaging.samples.MessageSample;
import java.util.Properties;

public class GenericPushConsumerApp {
    public static void main(String[] args) {
        //Load and start the vendor implementation from a specific OMS driver URL.
        final MessagingAccessPoint messagingAccessPoint =
            OMS.builder()
                .region("Shanghai")
                .endpoint("127.0.0.1:9876")
                .driver("rocketmq")
                .withCredentials(new Properties())
                .build();

        Properties properties = new Properties();
        properties.setProperty(OMSBuiltinKeys.DESERIALIZER, "io.openmessaging.openmeta.impl.Deserializer");
        properties.setProperty(OMSBuiltinKeys.OPEN_META_URL, "http://localhost:1234");

        final Consumer consumer = messagingAccessPoint.createConsumer(properties);
        consumer.start();

        //Register a shutdown hook to close the opened endpoints.
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                consumer.shutdown();
            }
        }));

        //Consume messages from a simple queue.
        String topic = "NS://HELLO_TOPIC";

        consumer.subscribe(topic, "*", new GenericMessageListener<MessageSample>() {

            @Override
            public Class<MessageSample> payloadClass() {
                return MessageSample.class;
            }

            @Override
            public Action consume(GenericMessage<MessageSample> message, MessageConsumeContext context) {
                MessageSample messageSample = message.getValue();
                System.out.println("Received message: " + messageSample);
                return Action.CommitMessage;
            }
        });

        consumer.shutdown();
    }
}
