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

package io.openmessaging.api;

import io.openmessaging.api.batch.BatchConsumer;
import io.openmessaging.api.order.OrderConsumer;
import io.openmessaging.api.order.OrderProducer;
import io.openmessaging.api.transaction.LocalTransactionChecker;
import io.openmessaging.api.transaction.TransactionProducer;
import java.util.Properties;

/**
 * An instance of {@code MessagingAccessPoint} may be obtained from {@link OMS}, which is capable of creating {@code
 * Producer}, {@code Consumer}, {@code ResourceManager}, and other facility entities.
 * <p>
 * For example:
 * <pre>
 * MessagingAccessPoint messagingAccessPoint = OMS.getMessagingAccessPoint("oms:rocketmq://alice@rocketmq.apache.org/us-east:default_space");
 * messagingAccessPoint.startup();
 * Producer producer = messagingAccessPoint.createProducer();
 * producer.startup();
 * </pre>
 *
 * @version OMS 1.1.0
 * @since OMS 1.1.0
 */
public interface MessagingAccessPoint {

    /**
     * Returns the target OMS specification version of the specified vendor implementation.
     *
     * @return the OMS version of implementation
     * @see OMS#specVersion
     */
    String version();

    /**
     * Returns the attributes of this {@code MessagingAccessPoint} instance.
     * <p>
     * There are some standard attributes defined by OMS for {@code MessagingAccessPoint}:
     * <ul>
     * <li> {@link OMSBuiltinKeys#ENDPOINT}, the specified access points.
     * <li> {@link OMSBuiltinKeys#DRIVER_IMPL}, the fully qualified class name of the specified MessagingAccessPoint's
     * implementation, the default value is {@literal io.openmessaging.<driver_type>.MessagingAccessPointImpl}.
     * <li> {@link OMSBuiltinKeys#REGION}, the region the resources reside in.
     * </ul>
     *
     * @return the attributes
     */
    Properties attributes();

    /**
     * Creates a new {@code Producer} for the specified {@code MessagingAccessPoint}.
     *
     * @return the created {@code Producer}
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails to handle this request due to some internal
     * error
     * @throws OMSSecurityException if have no authority to create a producer.
     */
    Producer createProducer(final Properties properties);

    /**
     * Creates a new {@code OrderProducer} for the specified {@code MessagingAccessPoint}.
     *
     * @return the created {@code OrderProducer}
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails to handle this request due to some internal
     * error
     * @throws OMSSecurityException if have no authority to create a producer.
     */
    OrderProducer createOrderProducer(final Properties properties);

    /**
     * Creates a new {@code TransactionProducer} for the specified {@code MessagingAccessPoint}.
     *
     * @return the created {@code TransactionProducer}
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails to handle this request due to some internal
     * error
     * @throws OMSSecurityException if have no authority to create a producer.
     */
    TransactionProducer createTransactionProducer(final Properties properties, final LocalTransactionChecker checker);

    /**
     * Creates a new {@code Consumer} for the specified {@code MessagingAccessPoint}. The returned {@code Consumer}
     * isn't subscribe any topic, and default Consumer will running with push model.
     *
     * @return the created {@code Consumer}
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails to handle this request due to some internal
     * error
     */
    Consumer createConsumer(final Properties properties);

    /**
     * Creates a new {@code PullConsumer} for the specified {@code MessagingAccessPoint}. The returned {@code Consumer}
     * isn't subscribe any topic.
     *
     * @return the created {@code PullConsumer}
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails to handle this request due to some internal
     * error
     */
    PullConsumer createPullConsumer(final Properties properties);

    /**
     * Creates a new {@code BatchConsumer} for the specified {@code MessagingAccessPoint}. The returned {@code Consumer}
     * isn't subscribe any topic.
     *
     * @return the created {@code BatchConsumer}
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails to handle this request due to some internal
     * error
     */
    BatchConsumer createBatchConsumer(final Properties properties);

    /**
     * Creates a new {@code OrderConsumer} for the specified {@code MessagingAccessPoint}. The returned {@code Consumer}
     * isn't subscribe any topic.
     *
     * @return the created {@code OrderConsumer}
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails to handle this request due to some internal
     * error
     */
    OrderConsumer createOrderedConsumer(final Properties properties);

    /**
     *
     * @param topic
     * @param properties
     * @param <T>
     * @return
     */
    <T> MessageBuilder<T> createMessageBuilder(String topic, final Properties properties);
}
