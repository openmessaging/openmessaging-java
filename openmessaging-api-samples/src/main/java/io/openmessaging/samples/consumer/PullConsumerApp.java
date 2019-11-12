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

import io.openmessaging.api.Message;
import io.openmessaging.api.MessagingAccessPoint;
import io.openmessaging.api.OMS;
import io.openmessaging.api.PullConsumer;
import io.openmessaging.api.TopicPartition;
import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Set;

public class PullConsumerApp {
    public static void main(String[] args) {
        //Load and start the vendor implementation from a specific OMS driver URL.
        final MessagingAccessPoint messagingAccessPoint =
            OMS.getMessagingAccessPoint("oms:rocketmq://alice@rocketmq.apache.org/us-east");
        Properties properties = new Properties();
        //Start a PullConsumer to receive messages from the specific queue.
        final PullConsumer consumer = messagingAccessPoint.createPullConsumer(properties);

        //Register a shutdown hook to close the opened endpoints.
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                consumer.shutdown();
            }
        }));

        Set<TopicPartition> topicPartitions = consumer.topicPartitions("NS://TOPIC");
        consumer.assign(topicPartitions);
        consumer.start();

        List<Message> message = consumer.poll(Duration.ofMillis(1000));
        System.out.println("Received message: " + message);
        //Acknowledge the consumed message
        consumer.commitSync();
        consumer.shutdown();

    }
}
