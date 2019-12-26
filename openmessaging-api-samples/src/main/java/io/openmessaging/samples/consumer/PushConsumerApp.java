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

package io.openmessaging.samples.consumer;

import io.openmessaging.api.Action;
import io.openmessaging.api.ConsumeContext;
import io.openmessaging.api.Consumer;
import io.openmessaging.api.Message;
import io.openmessaging.api.MessageListener;
import io.openmessaging.api.MessagingAccessPoint;
import io.openmessaging.api.OMS;
import java.util.Properties;

public class PushConsumerApp {
    public static void main(String[] args) {
        //Load and start the vendor implementation from a specific OMS driver URL.
        final MessagingAccessPoint messagingAccessPoint =
            OMS.builder()
                .region("Shenzhen")
                .endpoint("127.0.0.1:9876")
                .driver("rocketmq")
                .withCredentials(new Properties())
                .build();

        Properties properties = new Properties();
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

        consumer.subscribe(topic, "*", new MessageListener(){
            @Override
            public Action consume(Message message, ConsumeContext context) {

                return Action.CommitMessage;
            }
        });


        consumer.shutdown();
    }
}