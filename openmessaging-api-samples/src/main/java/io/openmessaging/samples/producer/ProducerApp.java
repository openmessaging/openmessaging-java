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

import io.openmessaging.Future;
import io.openmessaging.Message;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.OMS;
import io.openmessaging.interceptor.Context;
import io.openmessaging.interceptor.ProducerInterceptor;
import io.openmessaging.producer.Producer;
import io.openmessaging.producer.SendResult;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ProducerApp {
    public static void main(String[] args) {
        final MessagingAccessPoint messagingAccessPoint =
            OMS.getMessagingAccessPoint("oms:rocketmq://alice@rocketmq.apache.org/us-east");

        final Producer producer = messagingAccessPoint.createProducer();
        producer.start();
        ProducerInterceptor interceptor = new ProducerInterceptor() {
            @Override
            public void preSend(Message message, Context attributes) {
            }

            @Override
            public void postSend(Message message, Context attributes) {
            }
        };
        producer.addInterceptor(interceptor);

        //Register a shutdown hook to close the opened endpoints.
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                producer.stop();
            }
        }));

        //Sends a message to the specified destination synchronously.
        Message message = producer.createMessage(
            "NS://HELLO_QUEUE", "HELLO_BODY".getBytes(Charset.forName("UTF-8")));
        SendResult sendResult = producer.send(message);
        System.out.println("SendResult: " + sendResult);

        //Sends a message to the specified destination async.
        Future<SendResult> sendResultFuture = producer.sendAsync(message);
        sendResult = sendResultFuture.get(1000);
        System.out.println("SendResult: " + sendResult);

        //Sends a message to the specified destination in one way mode.
        producer.sendOneway(message);

        //Sends messages to the specified destination in batch mode.
        List<Message> messages = new ArrayList<Message>(10);
        for (int i = 0; i < 10; i++) {
            Message msg = producer.createMessage("NS://HELLO_QUEUE", ("Hello" + i).getBytes());
            messages.add(msg);
        }
        producer.send(messages);
        producer.removeInterceptor(interceptor);
        producer.stop();
    }
}
