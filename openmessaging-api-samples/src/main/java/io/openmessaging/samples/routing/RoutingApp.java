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
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.openmessaging.samples.routing;

import io.openmessaging.KeyValue;
import io.openmessaging.Message;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.OMS;
import io.openmessaging.OMSBuiltinKeys;
import io.openmessaging.consumer.Consumer;
import io.openmessaging.consumer.MessageListener;
import io.openmessaging.exception.OMSResourceNotExistException;
import io.openmessaging.manager.ResourceManager;
import io.openmessaging.manager.RoutingStrategy;
import io.openmessaging.producer.Producer;

public class RoutingApp {
    public static void main(String[] args) throws OMSResourceNotExistException {
        //Load and start the vendor implementation from a specific OMS driver URL.
        final MessagingAccessPoint messagingAccessPoint =
            OMS.getMessagingAccessPoint("oms:rocketmq://alice@rocketmq.apache.org/us-east");

        String destinationQueue = "NS://DESTINATION_QUEUE";
        String sourceQueue = "NS://SOURCE_QUEUE";
        //Fetch a ResourceManager to create source Queue, destination Queue, and the Routing instance.
        ResourceManager resourceManager = messagingAccessPoint.resourceManager();

        //Create the destination queue.
        resourceManager.createQueue(destinationQueue);
        //Create the source queue.
        resourceManager.createQueue(sourceQueue);

        RoutingStrategy strategy = new RoutingStrategy();
        strategy.setSourceQueue("srcQueue");
        strategy.setDestinationQueue("destQueue");
        strategy.setRoutingRule("duplicate");
        resourceManager.createRouting("NS://HELLO_ROUTING", strategy);

        //Send messages to the source queue ahead of the routing
        final Producer producer = messagingAccessPoint.createProducer();
        producer.startup();

        Message message = producer.createMessage(sourceQueue, "RED_COLOR".getBytes());
        message.properties().put("color","freen").put("shape","round");


        producer.send(message);


        //Consume messages from the queue behind the routing.
        final Consumer consumer = messagingAccessPoint.createConsumer();
        consumer.startup();

        consumer.bindQueue(destinationQueue, new MessageListener() {
            @Override
            public void onReceived(Message message, Context context) {
                //The message sent to the sourceQueue will be delivered to anotherConsumer by the routing rule
                //In this case, the push consumer will only receive the message with red color.
                System.out.println("Received a red message: " + message);
                context.ack();
            }

        });

        //Register a shutdown hook to close the opened endpoints.
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                producer.shutdown();
                consumer.shutdown();
            }
        }));
    }
}
