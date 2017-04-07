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

package io.openmessaging.samples.order;

import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.MessagingAccessPointManager;
import io.openmessaging.Producer;
import io.openmessaging.SequenceProducer;
import java.nio.charset.Charset;

public class ProducerApp {
    public static void main(String[] args) throws InterruptedException {
        final MessagingAccessPoint messagingAccessPoint = MessagingAccessPointManager.getMessagingAccessPoint("openmessaging:rocketmq://localhost:10911/namespace");

        final Producer producer = messagingAccessPoint.createProducer();

        messagingAccessPoint.startup();
        System.out.println("messagingAccessPoint startup OK");

        producer.startup();
        System.out.println("producer startup OK");

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                producer.shutdown();
                messagingAccessPoint.shutdown();
            }
        }));

        SequenceProducer sequenceProducer = messagingAccessPoint.createSequenceProducer();
        try {
            for (int i = 0; i < 10000; i++) {
                sequenceProducer.send(producer.createBytesMessageToTopic("HELLO_TOPIC", "HELLO_BODY".getBytes(Charset.forName("UTF-8"))));
            }
            sequenceProducer.commit();
        } catch (Exception e) {
            sequenceProducer.rollback();
        }

        Thread.sleep(1000L);

        producer.shutdown();
        messagingAccessPoint.shutdown();
    }
}
