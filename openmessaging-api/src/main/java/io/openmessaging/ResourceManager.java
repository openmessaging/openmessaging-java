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

import io.openmessaging.exception.OMSResourceNotExistException;
import java.util.List;

/**
 * The {@code ResourceManager} is responsible for providing a unified interface of resource management,
 * allows the user to manage the topic, queue, namespace resources.
 * <p>
 * Create, fetch, update and destroy are the four basic functions of {@code ResourceManager}.
 * <p>
 * And the {@code ResourceManager} also supports fetch and update resource attributes dynamically.
 * <p>
 * The attributes of consumer and producer also are treated as {@code Resource}. {@code ResourceManager}
 * allows the user to fetch producer and consumer list in a specified topic or queue,
 * and update their resource attributes dynamically.
 * <p>
 * {@link MessagingAccessPoint#getResourceManager()} is the unique method to obtain a {@code ResourceManager}
 * instance, any changes made by this instance will reflect to the message-oriented middleware (MOM) or
 * other product behind the {@code MessagingAccessPoint}.
 *
 * @author vintagewang@apache.org
 * @author yukon@apache.org
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface ResourceManager extends ServiceLifecycle {
    /**
     * Creates a {@code Namespace} resource for the specified {@code MessagingAccessPoint} with some preset properties.
     * <p>
     * Note that this method will simply create the physical Namespace in the specified {@code MessagingAccessPoint}.
     *
     * @param nsName the namespace name
     * @param attributes the preset properties
     */
    void createNamespace(String nsName, KeyValue attributes);

    /**
     * Updates the attributes of a specified namespace, all the old attributes will be removed and apply the new
     * attributes.
     *
     * @param nsName the namespace name
     * @param attributes the new attributes
     * @throws OMSResourceNotExistException if the specified namespace is not exists
     */
    void setNamespaceAttributes(String nsName, KeyValue attributes) throws OMSResourceNotExistException;

    /**
     * Gets the attributes of a specified namespace.
     *
     * @param nsName the namespace name
     * @return the attributes of namespace
     * @throws OMSResourceNotExistException if the specified namespace is not exists
     */
    KeyValue getNamespaceAttributes(String nsName) throws OMSResourceNotExistException;

    /**
     * Deletes an existing namespace resource.
     *
     * @param nsName the namespace needs to be deleted
     * @throws OMSResourceNotExistException if the specified namespace is not exists
     */
    void deleteNamespace(String nsName) throws OMSResourceNotExistException;

    /**
     * Gets the namespace list in current {@code MessagingAccessPoint}.
     *
     * @return the list of all namespaces
     */
    List<String> listNamespaces();

    /**
     * Creates a {@code Topic} resource in the specified namespace with some preset properties.
     * <p>
     *
     * @param nsName the namespace name
     * @param topicName the topic name
     * @param attributes the preset properties
     */
    void createTopic(String nsName, String topicName, KeyValue attributes);

    /**
     * Updates the attributes of a specified topic, all the old attributes will be removed and apply the new
     * attributes.
     *
     * @param nsName the namespace name
     * @param topicName the topic name
     * @param attributes the new attributes
     * @throws OMSResourceNotExistException if the specified topic or namespace is not exists
     */
    void setTopicAttributes(String nsName, String topicName, KeyValue attributes) throws OMSResourceNotExistException;

    /**
     * Gets the attributes of a specified topic.
     *
     * @param nsName the namespace name
     * @param topicName the topic name
     * @return the attributes of namespace
     * @throws OMSResourceNotExistException if the specified topic or namespace is not exists
     */
    KeyValue getTopicAttributes(String nsName, String topicName) throws OMSResourceNotExistException;

    /**
     * Deletes an existing topic resource.
     *
     * @param nsName the namespace of the existing topic
     * @param topicName the topic needs to be deleted
     * @throws OMSResourceNotExistException if the specified namespace is not exists
     */
    void deleteTopic(String nsName, String topicName) throws OMSResourceNotExistException;

    /**
     * Gets the topic list in specified namespace, return a empty list if the namespace is not exists
     *
     * @param nsName the namespace name
     * @return the list of all topics
     */
    List<String> listTopics(String nsName);

    /**
     * Creates a {@code Queue} resource in the specified namespace with some preset properties.
     * <p>
     *
     * @param nsName the namespace name
     * @param queueName the queue name
     * @param attributes the preset properties
     */
    void createQueue(String nsName, String queueName, KeyValue attributes);

    /**
     * Updates the attributes of a specified queue, all the old attributes will be removed and apply the new
     * attributes.
     *
     * @param nsName the namespace name
     * @param queueName the topic name
     * @param attributes the new attributes
     * @throws OMSResourceNotExistException if the specified topic or namespace is not exists
     */
    void setQueueAttributes(String nsName, String queueName, KeyValue attributes) throws OMSResourceNotExistException;

    /**
     * Gets the attributes of a specified queue.
     *
     * @param nsName the namespace name
     * @param queueName the topic name
     * @return the attributes of namespace
     * @throws OMSResourceNotExistException if the specified topic or namespace is not exists
     */
    KeyValue getQueueAttributes(String nsName, String queueName) throws OMSResourceNotExistException;

    /**
     * Deletes an existing queue resource.
     *
     * @param nsName the namespace of the existing queue
     * @param queueName the queue needs to be deleted
     * @throws OMSResourceNotExistException if the specified queue or namespace is not exists
     */
    void deleteQueue(String nsName, String queueName) throws OMSResourceNotExistException;

    /**
     * Gets the queue list in specified namespace, return a empty list if the namespace is not exists
     *
     * @param nsName the namespace name
     * @return the list of all queues
     */
    List<String> listQueues(String nsName);

    /**
     * Creates a {@code Routing} resource in the specified namespace with some preset properties.
     * <p>
     *
     * @param nsName the namespace name
     * @param routingName the routing name
     * @param attributes the preset properties
     */
    void createRouting(String nsName, String routingName, KeyValue attributes);

    /**
     * Updates the attributes of a specified routing, all the old attributes will be removed and apply the new
     * attributes.
     *
     * @param nsName the namespace name
     * @param routingName the routing name
     * @param attributes the new attributes
     * @throws OMSResourceNotExistException if the specified routing or namespace is not exists
     */
    void setRoutingAttributes(String nsName, String routingName, KeyValue attributes) throws OMSResourceNotExistException;

    /**
     * Gets the attributes of a specified routing.
     *
     * @param nsName the namespace name
     * @param routingName the routing name
     * @return the attributes of namespace
     * @throws OMSResourceNotExistException if the specified routing or namespace is not exists
     */
    KeyValue getRoutingAttributes(String nsName, String routingName) throws OMSResourceNotExistException;

    /**
     * Deletes an existing routing resource.
     *
     * @param nsName the namespace of the existing routing
     * @param routingName the routing needs to be deleted
     * @throws OMSResourceNotExistException if the specified routing or namespace is not exists
     */
    void deleteRouting(String nsName, String routingName) throws OMSResourceNotExistException;

    /**
     * Gets the routing list in specified namespace, return a empty list if the namespace is not exists
     *
     * @param nsName the namespace name
     * @return the list of all routing
     */
    List<String> listRoutings(String nsName);

    /**
     * Message also is a resource, the headers of message can be updated by this method
     *
     * @param messageId the id of message
     * @param headers the new headers
     */
    void updateMessage(String messageId, KeyValue headers);
}
