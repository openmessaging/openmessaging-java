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
import io.openmessaging.ResourceManager;
import io.openmessaging.consumer.MessageListener;
import io.openmessaging.consumer.PushConsumer;
import io.openmessaging.exception.OMSResourceNotExistException;
import io.openmessaging.producer.Producer;

public class RoutingApp {
    public static void main(String[] args) throws OMSResourceNotExistException {
        final MessagingAccessPoint messagingAccessPoint =
            OMS.getMessagingAccessPoint("oms:rocketmq://alice@rocketmq.apache.org/us-east:default_space");

        messagingAccessPoint.startup();

        String destinationQueue = "DESTINATION_QUEUE";
        String sourceQueue = "SOURCE_QUEUE";

        ResourceManager resourceManager = messagingAccessPoint.resourceManager();

        //Create the destination queue.
        resourceManager.createQueue(destinationQueue, OMS.newKeyValue());
        //Create the source queue.
        resourceManager.createQueue(sourceQueue, OMS.newKeyValue());

        KeyValue routingAttr = OMS.newKeyValue();
        routingAttr.put(OMSBuiltinKeys.ROUTING_SOURCE, sourceQueue)
            .put(OMSBuiltinKeys.ROUTING_DESTINATION, destinationQueue)
            .put(OMSBuiltinKeys.ROUTING_EXPRESSION, "color = 'red'");

        resourceManager.createRouting("HELLO_ROUTING", routingAttr);

        //Send messages to the source queue ahead of the routing
        final Producer producer = messagingAccessPoint.createProducer();

        producer.send(producer.createBytesMessage(sourceQueue, "RED_COLOR".getBytes())
            .putUserHeaders("color", "red"));

        producer.send(producer.createBytesMessage(sourceQueue, "GREEN_COLOR".getBytes())
            .putUserHeaders("color", "green"));

        //Consume messages from the queue behind the routing.
        final PushConsumer pushConsumer = messagingAccessPoint.createPushConsumer();

        pushConsumer.attachQueue(destinationQueue, new MessageListener() {
            @Override
            public void onReceived(Message message, Context context) {
                //The message sent to the sourceQueue will be delivered to anotherConsumer by the routing rule
                //In this case, the push consumer will only receive the message with red color.
                System.out.println("Received a red message: " + message);
                context.ack();
            }

        });
    }
}
