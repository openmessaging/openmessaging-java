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

import io.openmessaging.BatchToPartition;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.MessagingAccessPointManager;
import io.openmessaging.Producer;
import java.nio.charset.Charset;

public class ProducerApp {
    public static void main(String[] args) throws InterruptedException {
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

        BatchToPartition batchToPartition = producer.createBatchToPartition("PARTITION-001", MessagingAccessPointManager.buildKeyValue().put("SessionTimeout", 1000L));

        try {
            for (int i = 0; i < 10000; i++) {
                batchToPartition.send(producer.createBytesMessageToTopic("HELLO_TOPIC", "HELLO_BODY".getBytes(Charset.forName("UTF-8"))));
            }
            batchToPartition.commit();
        } catch (Exception e) {
            batchToPartition.rollback();
        }

        Thread.sleep(1000L);

        producer.shutdown();
        messagingAccessPoint.shutdown();
    }
}
