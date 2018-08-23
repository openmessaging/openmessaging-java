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

import io.openmessaging.common.Response;
import io.openmessaging.consumer.Consumer;
import io.openmessaging.consumer.ConsumerConfig;
import io.openmessaging.consumer.MessageListener;
import io.openmessaging.consumer.StreamingConsumer;
import io.openmessaging.exception.OMSRuntimeException;
import io.openmessaging.manager.ResourceManager;
import io.openmessaging.producer.Producer;
import io.openmessaging.producer.ProducerConfig;
import java.util.jar.Attributes;

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
public interface MessagingAccessPoint extends Response {

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
     */
    Producer createProducer();

    /**
     * Creates a new {@code Producer} for the specified {@code MessagingAccessPoint} with some preset attributes.
     *
     * @param producerConfig the preset producer config
     * @return the created {@code Producer}
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails to handle this request due to some internal
     * error
     */
    Producer createProducer(ProducerConfig producerConfig);

    /**
     * Creates a new {@code PushConsumer} for the specified {@code MessagingAccessPoint}. The returned {@code Consumer}
     * isn't bind to any queue, uses {@link Consumer#bindQueue(String, MessageListener)} to bind queues.
     *
     * @return the created {@code PushConsumer}
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails to handle this request due to some internal
     * error
     */
    Consumer createConsumer();

    /**
     * Creates a new {@code PushConsumer} for the specified {@code MessagingAccessPoint}. The returned {@code Consumer}
     * isn't bind to any queue, uses {@link Consumer#bindQueue(String, MessageListener)} to bind queues.
     *
     * @param consumerConfig the preset config
     * @return the created {@code PushConsumer}
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails to handle this request due to some internal
     * error
     */
    Consumer createConsumer(ConsumerConfig consumerConfig);

    /**
     * Creates a new {@code StreamingConsumer} for the specified {@code MessagingAccessPoint}.
     *
     * @return the created {@code Stream}
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails to handle this request due to some internal
     * error
     */
    StreamingConsumer createStreamingConsumer();

    /**
     * Creates a new {@code StreamingConsumer} for the specified {@code MessagingAccessPoint} with some preset
     * attributes.
     *
     * @param consumerConfig the preset client config
     * @return the created consumer
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails to handle this request due to some internal
     * error
     */
    StreamingConsumer createStreamingConsumer(ConsumerConfig consumerConfig);

    /**
     * Gets a lightweight {@code ResourceManager} instance from the specified {@code MessagingAccessPoint}.
     *
     * @return the resource manger
     * @throws OMSRuntimeException if the {@code MessagingAccessPoint} fails to handle this request due to some internal
     * error
     */
    ResourceManager resourceManager();
}
