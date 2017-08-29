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

package io.openmessaging.samples.producer;

import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.MessagingAccessPointFactory;
import io.openmessaging.Producer;
import io.openmessaging.Promise;
import io.openmessaging.PromiseListener;
import io.openmessaging.SendResult;
import java.nio.charset.Charset;

public class ProducerApp {
    public static void main(String[] args) {
        final MessagingAccessPoint messagingAccessPoint = MessagingAccessPointFactory
            .getMessagingAccessPoint("openmessaging:rocketmq://localhost:10911/namespace");

        final Producer producer = messagingAccessPoint.createProducer();

        messagingAccessPoint.startup();
        System.out.println("MessagingAccessPoint startup OK");

        producer.startup();
        System.out.println("Producer startup OK");

        //Add a shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                producer.shutdown();
                messagingAccessPoint.shutdown();
            }
        }));

        //Sync
        {
            SendResult sendResult = producer.send(producer.createTopicBytesMessage(
                "HELLO_TOPIC", "HELLO_BODY".getBytes(Charset.forName("UTF-8"))));

            System.out.println("Send sync message OK, message id is: " + sendResult.messageId());
        }

        //Async with Promise
        {
            final Promise<SendResult> result = producer.sendAsync(producer.createTopicBytesMessage(
                "HELLO_TOPIC", "HELLO_BODY".getBytes(Charset.forName("UTF-8"))));

            final SendResult sendResult = result.get(3000L);
            System.out.println("Send async message OK, message id is: " + sendResult.messageId());
        }

        //Async with PromiseListener
        {
            final Promise<SendResult> result = producer.sendAsync(producer.createTopicBytesMessage(
                "HELLO_TOPIC", "HELLO_BODY".getBytes(Charset.forName("UTF-8"))));

            result.addListener(new PromiseListener<SendResult>() {
                @Override
                public void operationSucceeded(Promise<SendResult> promise) {
                    System.out.println("Send async message OK, message id is: " + promise.get().messageId());
                }

                @Override
                public void operationFailed(Promise<SendResult> promise) {
                    System.out.println("Send async message Failed, cause is: " + promise.getThrowable().getMessage());
                }
            });
        }

        //Oneway
        {
            producer.sendOneway(producer.createTopicBytesMessage(
                "HELLO_TOPIC", "HELLO_BODY".getBytes(Charset.forName("UTF-8"))));

            System.out.println("Send oneway message OK");
        }
    }
}
