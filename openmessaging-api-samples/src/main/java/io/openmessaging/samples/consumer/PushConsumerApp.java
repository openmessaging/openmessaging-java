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

import io.openmessaging.ResourceManager;
import io.openmessaging.Message;
import io.openmessaging.MessageListener;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.MessagingAccessPointFactory;
import io.openmessaging.OMS;
import io.openmessaging.PushConsumer;
import io.openmessaging.exception.OMSResourceNotExistException;

public class PushConsumerApp {
    public static void main(String[] args) throws OMSResourceNotExistException {
        final MessagingAccessPoint messagingAccessPoint = MessagingAccessPointFactory
            .getMessagingAccessPoint("openmessaging:rocketmq://localhost:10911/namespace");
        messagingAccessPoint.startup();
        System.out.println("MessagingAccessPoint startup OK");
        ResourceManager resourceManager = messagingAccessPoint.getResourceManager();

        final PushConsumer consumer = messagingAccessPoint.createPushConsumer();
        // Consume messages from a simple queue.
        {
            String simpleQueue = "HELLO_QUEUE";
            resourceManager.createQueue("NS1", simpleQueue, OMS.newKeyValue());

            //This queue doesn't has a source topic, so only the message delivered to the queue directly can
            //be consumed by this consumer.
            consumer.attachQueue(simpleQueue, new MessageListener() {
                @Override
                public void onReceived(Message message, Context context) {
                    System.out.println("Received one message: " + message);
                    context.ack();
                }

            });

            consumer.startup();
            System.out.println("Consumer startup OK");
        }

        //Consume messages from a complex queue.
        final PushConsumer anotherConsumer = messagingAccessPoint.createPushConsumer();
        {
            String complexQueue = "QUEUE_HAS_SOURCE_TOPIC";
            String sourceTopic = "SOURCE_TOPIC";

            //Create the complex queue.
            resourceManager.createQueue("NS_01", complexQueue, OMS.newKeyValue());
            //Create the source topic.
            resourceManager.createTopic("NS_01", sourceTopic, OMS.newKeyValue());

        }

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                consumer.shutdown();
                anotherConsumer.shutdown();
                messagingAccessPoint.shutdown();
            }
        }));
    }
}