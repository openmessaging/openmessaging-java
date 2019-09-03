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

package io.openmessaging.internal;

import io.openmessaging.Consumer;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.OMS;
import io.openmessaging.OMSBuiltinKeys;
import io.openmessaging.Producer;
import io.openmessaging.PullConsumer;
import io.openmessaging.batch.BatchConsumer;
import io.openmessaging.order.OrderConsumer;
import io.openmessaging.order.OrderProducer;
import io.openmessaging.transaction.LocalTransactionChecker;
import io.openmessaging.transaction.TransactionProducer;
import java.util.Properties;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MessagingAccessPointAdapterTest {
    @Test
    public void getMessagingAccessPoint() {
        String testURI = "oms:test-vendor://alice@rocketmq.apache.org/us-east:default_space";

        Properties properties = new Properties();
        properties.put(OMSBuiltinKeys.DRIVER_IMPL, "io.openmessaging.internal.TestVendor");
        MessagingAccessPoint messagingAccessPoint = OMS.getMessagingAccessPoint(testURI, properties);
        assertThat(messagingAccessPoint).isExactlyInstanceOf(TestVendor.class);
    }
}

class TestVendor implements MessagingAccessPoint {
    public TestVendor(Properties properties) {

    }

    @Override public String version() {
        return "1.1.3";
    }

    @Override public Properties attributes() {
        return null;
    }

    @Override public Producer createProducer(Properties properties) {
        return null;
    }

    @Override public OrderProducer createOrderProducer(Properties properties) {
        return null;
    }

    @Override public TransactionProducer createTransactionProducer(Properties properties,
        LocalTransactionChecker checker) {
        return null;
    }

    @Override public Consumer createConsumer(Properties properties) {
        return null;
    }

    @Override public PullConsumer createPullConsumer(Properties properties) {
        return null;
    }

    @Override public BatchConsumer createBatchConsumer(Properties properties) {
        return null;
    }

    @Override public OrderConsumer createOrderedConsumer(Properties properties) {
        return null;
    }
}