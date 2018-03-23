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
import io.openmessaging.ResourceManager;
import io.openmessaging.consumer.MessageListener;
import io.openmessaging.consumer.PushConsumer;
import io.openmessaging.exception.OMSResourceNotExistException;

public class PushConsumerApp {
    public static void main(String[] args) throws OMSResourceNotExistException {
        final MessagingAccessPoint messagingAccessPoint = OMS.getMessagingAccessPoint("oms:rocketmq://localhost:10911/us-east:resourceManager");
        messagingAccessPoint.startup();
        System.out.println("MessagingAccessPoint startup OK");
        ResourceManager resourceManager = messagingAccessPoint.getResourceManager();

        final PushConsumer consumer = messagingAccessPoint.createPushConsumer();
        // Consume messages from a simple queue.
        {
            String simpleQueue = "HELLO_QUEUE";
            resourceManager.createQueue("NS1", simpleQueue, OMS.newKeyValue());

            //This queue doesn't has a source queue, so only the message delivered to the queue directly can
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
            String complexQueue = "QUEUE_WITH_SOURCE_QUEUE";
            String sourceQueue = "SOURCE_QUEUE";

            //Create the complex queue.
            resourceManager.createQueue("NS_01", complexQueue, OMS.newKeyValue());
            //Create the source queue.
            resourceManager.createQueue("NS_01", sourceQueue, OMS.newKeyValue());

            anotherConsumer.attachQueue(complexQueue, new MessageListener() {
                @Override
                public void onReceived(Message message, Context context) {
                    //The message sent to the sourceQueue will be delivered to anotherConsumer
                    System.out.println("Received one message: " + message);
                    context.ack();
                }

            });
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