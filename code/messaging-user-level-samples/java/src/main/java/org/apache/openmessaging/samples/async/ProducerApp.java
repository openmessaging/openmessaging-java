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
package org.apache.openmessaging.samples.async;

import java.nio.charset.Charset;
import org.apache.openmessaging.MessagingAccessPoint;
import org.apache.openmessaging.MessagingAccessPointManager;
import org.apache.openmessaging.Producer;
import org.apache.openmessaging.Promise;
import org.apache.openmessaging.PromiseListener;

public class ProducerApp {
    public static void main(String[] args) {
        final MessagingAccessPoint messagingAccessPoint = MessagingAccessPointManager.getMessagingAccessPoint("openmessaging:rocketmq://localhost:10911/namespace");

        final Producer producer = messagingAccessPoint.createProducer();

        messagingAccessPoint.start();
        System.out.println("messagingAccessPoint startup OK");

        producer.start();
        System.out.println("producer startup OK");

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                producer.shutdown();
                messagingAccessPoint.shutdown();
            }
        }));

        {
            final Promise<Void> result = producer.sendAsync(producer.createBytesMessageToTopic("HELLO_TOPIC", "HELLO_BODY".getBytes(Charset.forName("UTF-8"))));
            final Void aVoid = result.get(3000L);
            System.out.println("send async message OK");
        }

        {
            final Promise<Void> result = producer.sendAsync(producer.createBytesMessageToTopic("HELLO_TOPIC", "HELLO_BODY".getBytes(Charset.forName("UTF-8"))));
            result.addListener(new PromiseListener<Void>() {
                @Override public void operationComplete(Promise<Void> promise) {
                    System.out.println("send async message OK");
                }

                @Override public void operationFailed(Promise<Void> promise) {
                    System.out.println("send async message Failed");
                }
            });

            System.out.println("send async message OK");
        }

        {
            producer.sendOneway(producer.createBytesMessageToTopic("HELLO_TOPIC", "HELLO_BODY".getBytes(Charset.forName("UTF-8"))));
            System.out.println("send oneway message OK");
        }
    }
}
