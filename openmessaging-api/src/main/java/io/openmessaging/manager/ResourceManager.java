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

package io.openmessaging.manager;

import io.openmessaging.KeyValue;
import io.openmessaging.Message;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.common.BaseResult;
import io.openmessaging.common.ErrorCode;
import io.openmessaging.exception.OMSResourceNotExistException;
import java.util.List;

/**
 * The {@code ResourceManager} is to provide a unified interface of resource management, allowing developers to manage
 * the namespace, queue and routing resources.
 * <p>
 * Create, set, get and delete are the four basic operations of {@code ResourceManager}.
 * <p>
 * {@code ResourceManager} also supports dynamic fetch and update of resource attributes.
 * <p>
 * {@link MessagingAccessPoint#resourceManager()} ()} is the unique method to obtain a {@code ResourceManager} instance.
 * Changes made through this instance will immediately apply to the message-oriented middleware (MOM) behind {@code
 * MessagingAccessPoint}.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface ResourceManager {
    /**
     * Creates a {@code Namespace} resource with some preset attributes.
     * <p>
     * A namespace wraps the OMS resources in an abstract concept that makes it appear to the users within the namespace
     * that they have their own isolated instance of the global OMS resources.
     *
     * @param nsName the name of the new namespace
     */
    BaseResult createNamespace(String nsName);

    /**
     * Sets the attributes of the specific namespace, the old attributes will be replaced by the provided attributes,
     * only the provided key will be updated.
     *
     * @param nsName the specific namespace
     * @param attributes the new attributes
     * @return {@link BaseResult#getErrorCode()} will return {@link ErrorCode#NAMESPACE_NOT_EXIST} if the specific
     * namespace does not exist
     */
    BaseResult setNamespaceAttributes(String nsName, KeyValue attributes);

    /**
     * Gets the attributes of the specific namespace.
     *
     * @param nsName the specific namespace
     * @return the attributes of namespace
     * @throws OMSResourceNotExistException if the specific namespace does not exist
     */
    KeyValue getNamespaceAttributes(String nsName);

    /**
     * Deletes an existing namespace resource.
     *
     * @param nsName the namespace needs to be deleted
     * @return {@link BaseResult#getErrorCode()} will return {@link ErrorCode#NAMESPACE_NOT_EXIST} if the specific
     * namespace does not exist
     */
    BaseResult deleteNamespace(String nsName);

    /**
     * Gets the namespace list in the current {@code MessagingAccessPoint}.
     *
     * @return the list of all namespaces
     */
    List<String> listNamespaces();

    /**
     * Creates a {@code Queue} resource in the configured namespace with some preset attributes.
     * <p>
     * The standard OMS {@code Queue} schema must start with the {@code Namespace} prefix:
     * <p>
     * {@literal <namespace_name>://<queue_name>}
     *
     * @param queueName the name of the new queue
     * @return {@link BaseResult#getErrorCode()} will return {@link ErrorCode#NAMESPACE_NOT_EXIST} if the specific
     * namespace does not exist
     */
    BaseResult createQueue(String queueName);

    /**
     * Creates a {@code Queue} resource in the configured namespace with some preset attributes.
     * <p>
     * The standard OMS {@code Queue} schema must start with the {@code Namespace} prefix:
     * <p>
     * {@literal <namespace_name>://<queue_name>}
     *
     * @param queueName the name of the new queue
     * @param queueConfig the preset config
     * @return {@link BaseResult#getErrorCode()} will return {@link ErrorCode#NAMESPACE_NOT_EXIST} if the specific
     * namespace does not exist
     */
    BaseResult createQueue(String queueName, QueueConfig queueConfig);

    /**
     * Sets the attributes of the specified queue, the old attributes will be replaced by the provided attributes, only
     * the provided key will be updated.
     *
     * @param queueName the queue name
     * @param queueConfig the new attributes
     * @return {@link BaseResult#getErrorCode()} will return {@link ErrorCode#NAMESPACE_NOT_EXIST} if the specific
     * namespace does not exist
     */
    BaseResult setQueueConfig(String queueName, QueueConfig queueConfig);

    /**
     * Gets the attributes of the specified queue.
     *
     * @param queueName the queue name
     * @return {@link BaseResult#getErrorCode()} will return {@link ErrorCode#NAMESPACE_NOT_EXIST} if the specific
     * namespace does not exist
     */
    QueueConfig getQueueConfig(String queueName);

    /**
     * Deletes an existing queue resource.
     *
     * @param queueName the queue needs to be deleted
     * @return {@link BaseResult#getErrorCode()} will return {@link ErrorCode#NAMESPACE_NOT_EXIST} if the specific
     * namespace does not exist
     */
    BaseResult deleteQueue(String queueName);

    /**
     * Gets the queue list in the specific namespace.
     *
     * @param nsName the specific namespace
     * @return the list of all queues, {@link BaseResult#getErrorCode()} will return {@link
     * ErrorCode#NAMESPACE_NOT_EXIST} if the specific namespace does not exist
     */
    QueueListResult listQueues(String nsName);

    /**
     * Creates a {@code Routing} resource in the configured namespace with some preset attributes.
     * <p>
     * The standard OMS {@code Routing} schema must start with the {@code Namespace} prefix:
     * <p>
     * {@literal <namespace_name>://<routing_name>}
     *
     * @param routingName the name of the new routing
     * @param routingStrategy the preset routing strategy
     * @return {@link BaseResult#getErrorCode()} will return {@link ErrorCode#NAMESPACE_NOT_EXIST} if the specific
     * namespace does not exist
     */
    BaseResult createRouting(String routingName, RoutingStrategy routingStrategy);

    /**
     * Gets the attributes of the specified routing.
     *
     * @param routingName the routing name
     * @return {@link BaseResult#getErrorCode()} will return {@link ErrorCode#NAMESPACE_NOT_EXIST} if the specific
     * namespace does not exist and  {@link ErrorCode#ROUTING_NOT_EXIST}
     */
    RoutingStrategy getRouting(String routingName);

    /**
     * Deletes an existing routing resource.
     *
     * @param routingName the routing needs to be deleted
     * @return {@link BaseResult#getErrorCode()} will return {@link ErrorCode#NAMESPACE_NOT_EXIST} if the specific
     * namespace does not exist
     */
    BaseResult deleteRouting(String routingName);

    /**
     * Gets the routing list in the specific namespace.
     *
     * @param nsName the specific namespace
     * @return {@link BaseResult#getErrorCode()} will return {@link ErrorCode#NAMESPACE_NOT_EXIST} if the specific
     * namespace does not exist
     */
    RoutingListResult listRoutings(String nsName);

    /**
     * Gets the stream list behind the specified queue.
     *
     * @param queueName the queue name
     * @return {@link BaseResult#getErrorCode()} will return {@link ErrorCode#NAMESPACE_NOT_EXIST} if the specific
     * namespace does not exist
     */
    StreamListResult listStreams(String queueName);

}
