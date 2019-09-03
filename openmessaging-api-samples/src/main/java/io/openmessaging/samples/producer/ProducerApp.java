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

import io.openmessaging.Message;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.OMS;
import io.openmessaging.OnExceptionContext;
import io.openmessaging.Producer;
import io.openmessaging.SendCallback;
import io.openmessaging.SendResult;
import java.util.Properties;

public class ProducerApp {
    public static void main(String[] args) {
        final MessagingAccessPoint messagingAccessPoint =
            OMS.getMessagingAccessPoint("oms:rocketmq://alice@rocketmq.apache.org/us-east");

        final Producer producer = messagingAccessPoint.createProducer(new Properties());
        producer.start();

        //Register a shutdown hook to close the opened endpoints.
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                producer.shutdown();
            }
        }));

        Message message = new Message("NS://Topic", "TagA", "Hello MQ".getBytes());

        SendResult sendResult = producer.send(message);
        System.out.println("SendResult: " + sendResult);

        //Sends a message to the specified destination async.
        producer.sendAsync(message, new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println("SendResult: " + sendResult);
            }

            @Override
            public void onException(OnExceptionContext context) {

            }
        });

        //Sends a message to the specified destination in one way mode.
        producer.sendOneway(message);

        producer.shutdown();
    }
}
