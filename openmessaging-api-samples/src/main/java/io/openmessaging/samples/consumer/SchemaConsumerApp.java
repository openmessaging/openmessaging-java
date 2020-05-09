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
package io.openmessaging.samples.consumer;

import io.openmessaging.api.Action;
import io.openmessaging.api.ConsumeContext;
import io.openmessaging.api.GenericMessageListener;
import io.openmessaging.api.MessagingAccessPoint;
import io.openmessaging.api.OMS;
import io.openmessaging.api.OMSBuiltinKeys;
import io.openmessaging.api.OMSConsumer;
import io.openmessaging.samples.User;
import java.util.List;
import java.util.Properties;

public class SchemaConsumerApp {

    public static void main(String[] args) {
        //Load and start the vendor implementation from a specific OMS driver URL.
        final MessagingAccessPoint messagingAccessPoint =
            OMS.builder()
                .region("Shanghai")
                .endpoint("127.0.0.1:9876")
                .driver("rocketmq")
                .withCredentials(new Properties())
                .build();

        Properties properties = new Properties();
        properties.setProperty(OMSBuiltinKeys.SERIALLIZER, "io.openmessaging.openmeta.Serilizer");
        properties.setProperty(OMSBuiltinKeys.OPEN_META_URL, "localhost:1234");

        OMSConsumer<User> pullConsumer = messagingAccessPoint.createOMSConsumer(properties);
        pullConsumer.subscribe("topic", "*");
        List<User> users = pullConsumer.poll();
        System.out.println(users);

        OMSConsumer pushConsumer = messagingAccessPoint.createOMSConsumer(properties);

        pushConsumer.subscribe("testTopic", "*", new GenericMessageListener<User>() {
            @Override public Action consume(User user, ConsumeContext context) {
                System.out.println(user);
                return Action.CommitMessage;
            }
        });
    }
}
