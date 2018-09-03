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

import io.openmessaging.Message;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.OMS;
import io.openmessaging.consumer.Consumer;
import io.openmessaging.manager.ResourceManager;

public class PullConsumerApp {
    public static void main(String[] args) {
        //Load and start the vendor implementation from a specific OMS driver URL.
        final MessagingAccessPoint messagingAccessPoint =
            OMS.getMessagingAccessPoint("oms:rocketmq://alice@rocketmq.apache.org/us-east");

        //Fetch a ResourceManager to create Queue resource.
        ResourceManager resourceManager = messagingAccessPoint.resourceManager();
        resourceManager.createQueue("NS://HELLO_QUEUE");

        //Start a PullConsumer to receive messages from the specific queue.
        final Consumer consumer = messagingAccessPoint.createConsumer();
        consumer.start();

        //Register a shutdown hook to close the opened endpoints.
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                consumer.stop();
            }
        }));
        consumer.bindQueue("NS://HELLO_QUEUE");
        Message message = consumer.receive(1000);
        System.out.println("Received message: " + message);
        //Acknowledge the consumed message
        consumer.ack(message.headers().getMessageId());
    }
}
