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
package io.openmessaging.samples.service;

import io.openmessaging.KeyValue;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.MessagingAccessPointFactory;
import io.openmessaging.ServiceEndPoint;
import io.openmessaging.samples.service.impl.HelloServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Provider {
    private static final Logger LOG = LoggerFactory.getLogger(Provider.class);

    public static void main(String[] args) {

        try {
            KeyValue properties = MessagingAccessPointFactory.buildKeyValue();
            properties.put("protocol.name", "mvp");
            properties.put("service.prefer.tag", "hello");
            final MessagingAccessPoint messagingAccessPoint = MessagingAccessPointFactory.getMessagingAccessPoint("relay:default:100.81.2.5:8443", properties);

            final ServiceEndPoint serviceEndPoint = messagingAccessPoint.createServiceEndPoint();

            serviceEndPoint.publish(new HelloServiceImpl());

            messagingAccessPoint.startup();

            serviceEndPoint.startup();

            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                public void run() {
                    messagingAccessPoint.shutdown();
                    serviceEndPoint.shutdown();
                }
            }));
        }
        catch (Throwable e) {
            LOG.error(e.toString());
        }
    }
}
