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
        //Load and start the vendor implementation from a specific OMS driver URL.
        final MessagingAccessPoint messagingAccessPoint =
            OMS.getMessagingAccessPoint("oms:rocketmq://localhost:10911/us-east");
        messagingAccessPoint.startup();

        //Fetch a ResourceManager to create Queue resource.
        ResourceManager resourceManager = messagingAccessPoint.resourceManager();
        final PushConsumer consumer = messagingAccessPoint.createPushConsumer();
        consumer.startup();

        //Consume messages from a simple queue.
        String simpleQueue = "NS://HELLO_QUEUE";
        resourceManager.createQueue( simpleQueue, OMS.newKeyValue());

        //This queue doesn't has a source queue, so only the message delivered to the queue directly can
        //be consumed by this consumer.
        consumer.attachQueue(simpleQueue, new MessageListener() {
            @Override
            public void onReceived(Message message, Context context) {
                System.out.println("Received one message: " + message);
                context.ack();
            }

        });

        //Register a shutdown hook to close the opened endpoints.
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                consumer.shutdown();
                messagingAccessPoint.shutdown();
            }
        }));
    }
}