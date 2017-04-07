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

package io.openmessaging.samples.simple;

import io.openmessaging.Message;
import io.openmessaging.MessageListener;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.MessagingAccessPointFactory;
import io.openmessaging.ReceivedMessageContext;
import io.openmessaging.PushConsumer;
import io.openmessaging.exception.OMSResourceNotExistException;

public class ConsumerQueueApp {
    public static void main(String[] args) throws OMSResourceNotExistException {
        final MessagingAccessPoint messagingAccessPoint = MessagingAccessPointFactory.getMessagingAccessPoint("openmessaging:rocketmq://localhost:10911/namespace");

        final PushConsumer consumer = messagingAccessPoint.createPushConsumer();

        consumer.attachQueue("HELLO_QUEUE", new MessageListener() {
            @Override public void onMessage(Message message, ReceivedMessageContext context) {
                System.out.println("receive one message: " + message);
            }
        });

        messagingAccessPoint.startup();
        System.out.println("messagingAccessPoint startup OK");

        consumer.startup();
        System.out.println("consumer startup OK");

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                consumer.shutdown();
                messagingAccessPoint.shutdown();
            }
        }));
    }
}