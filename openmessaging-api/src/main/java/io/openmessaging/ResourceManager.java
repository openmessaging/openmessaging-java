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
     * --------------
     */
    void createNamespace(String nsName, KeyValue attributes);

    void setNamespaceAttributes(String nsName, KeyValue attributes) throws OMSResourceNotExistException;

    KeyValue getNamespaceAttributes(String nsName) throws OMSResourceNotExistException;

    void deleteNamespace(String nsName) throws OMSResourceNotExistException;

    List<String> listNamespaces();

    /**
     * --------------
     */
    void createTopic(String nsName, String topicName, KeyValue attributes);

    void setTopicAttributes(String nsName, String topicName, KeyValue attributes) throws OMSResourceNotExistException;

    KeyValue getTopicAttributes(String nsName, String topicName) throws OMSResourceNotExistException;

    void deleteTopic(String nsName, String topicName) throws OMSResourceNotExistException;

    List<String> listTopics(String nsName);

    /**
     * --------------
     */
    void createQueue(String nsName, String queueName, KeyValue attributes);

    void setQueueAttributes(String nsName, String queueName, KeyValue attributes) throws OMSResourceNotExistException;

    KeyValue getQueueAttributes(String nsName, String queueName) throws OMSResourceNotExistException;

    void deleteQueue(String nsName, String queueName) throws OMSResourceNotExistException;

    List<String> listQueues(String nsName);

    /**
     * --------------
     */
    void createRouting(String nsName, String routingName, KeyValue attributes);

    void setRoutingAttributes(String nsName, String routingName,
        KeyValue attributes) throws OMSResourceNotExistException;

    KeyValue getRoutingAttributes(String nsName, String routingName) throws OMSResourceNotExistException;

    void deleteRouting(String nsName, String routingName) throws OMSResourceNotExistException;

    List<String> listRoutings(String nsName);

    /**
     * --------------
     */
    void updateMessage(String messageId, KeyValue headers);
}
