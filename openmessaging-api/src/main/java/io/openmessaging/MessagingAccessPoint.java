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

package io.openmessaging;

import io.openmessaging.consumer.Consumer;
import io.openmessaging.consumer.MessageListener;
import io.openmessaging.consumer.PullConsumer;
import io.openmessaging.consumer.PushConsumer;
import io.openmessaging.exception.OMSRuntimeException;
import io.openmessaging.exception.OMSSecurityException;
import io.openmessaging.manager.ResourceManager;
import io.openmessaging.message.MessageFactory;
import io.openmessaging.producer.Producer;
import io.openmessaging.producer.TransactionStateCheckListener;
import java.util.Collection;

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
 * producer.send(producer.createBytesMessage("HELLO_QUEUE", "HELLO_BODY".getBytes(Charset.forName("UTF-8"))));
 * </pre>
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
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
     * <li> {@link OMSBuiltinKeys#ACCESS_POINTS}, the specified access points.
     * <li> {@link OMSBuiltinKeys#DRIVER_IMPL}, the fully qualified class name of the specified MessagingAccessPoint's
     * implementation, the default value is {@literal io.openmessaging.<driver_type>.MessagingAccessPointImpl}.
     * <li> {@link OMSBuiltinKeys#REGION}, the region the resources reside in.
     * <li> {@link OMSBuiltinKeys#ACCOUNT_ID}, the ID of the specific account system that owns the resource.
     * </ul>
     *
     * @return the attributes
     */
    KeyValue attributes();

    /**
     * Creates a new {@code Producer} for the specified {@code MessagingAccessPoint}.
     *
     * @return the created {@code Producer}
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails to handle this request due to some internal
     * error
     * @throws OMSSecurityException if have no authority to create a producer.
     */
    Producer createProducer();

    /**
     * Creates a new transactional {@code Producer} for the specified {@code MessagingAccessPoint}, the producer is able
     * to respond to requests from the server to check the status of the transaction.
     *
     * @param transactionStateCheckListener transactional check listener {@link TransactionStateCheckListener}
     * @return the created {@code Producer}
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails to handle this request due to some internal
     * error
     * @throws OMSSecurityException if have no authority to create a producer.
     */
    Producer createProducer(TransactionStateCheckListener transactionStateCheckListener);

    /**
     * Creates a new {@code PushConsumer} for the specified {@code MessagingAccessPoint}.
     * The returned {@code PushConsumer} isn't attached to any queue,
     * uses {@link PushConsumer#bindQueue(Collection, MessageListener)} to attach queues.
     *
     * @return the created {@code PushConsumer}
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails to handle this request
     * due to some internal error
     */
    PushConsumer createPushConsumer();

    /**
     * Creates a new {@code PullConsumer} for the specified {@code MessagingAccessPoint}.
     *
     * @return the created {@code PullConsumer}
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails to handle this request
     * due to some internal error
     */
    PullConsumer createPullConsumer();

    /**
     * Creates a new {@code PushConsumer} for the specified {@code MessagingAccessPoint} with some preset attributes.
     *
     * @param attributes the preset attributes
     * @return the created {@code PushConsumer}
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails to handle this request
     * due to some internal error
     */
    PushConsumer createPushConsumer(KeyValue attributes);

    /**
     * Creates a new {@code PullConsumer} for the specified {@code MessagingAccessPoint}.
     *
     * @return the created {@code PullConsumer}
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails to handle this request
     * due to some internal error
     */
    PullConsumer createPullConsumer(KeyValue attributes);

    /**
     * Gets a lightweight {@code ResourceManager} instance from the specified {@code MessagingAccessPoint}.
     *
     * @return the resource manger
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails to handle this request due to some internal
     * error
     * @throws OMSSecurityException if have no authority to obtain a resource manager.
     */
    ResourceManager resourceManager();

    /**
     * Gets a {@link MessageFactory} instance from the specified {@code MessagingAccessPoint}.
     *
     * @return the resource manger
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails to handle this request due to some internal
     * error
     */
    MessageFactory messageFactory();
}
