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

import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.exception.OMSDestinationException;
import io.openmessaging.exception.OMSRuntimeException;
import io.openmessaging.exception.OMSSecurityException;
import io.openmessaging.exception.OMSTimeOutException;
import java.util.Set;

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
     * @param nsName the name of the new namespace.
     * @throws OMSSecurityException when have no authority to create namespace.
     * @throws OMSTimeOutException when the given timeout elapses before the create operation completes.
     * @throws OMSDestinationException when this given destination has been created in the server.
     * @throws OMSRuntimeException when the {@code ResourceManager} fails to create namespace due to some internal
     * error.
     */
    void createNamespace(String nsName);

    /**
     * Deletes an existing namespace.
     *
     * @param nsName the namespace needs to be deleted.
     * @throws OMSSecurityException when have no authority to delete this namespace.
     * @throws OMSTimeOutException when the given timeout elapses before the delete operation completes.
     * @throws OMSDestinationException when have no given destination in the server.
     * @throws OMSRuntimeException when the {@code ResourceManager} fails to delete the namespace due to some internal
     * error.
     */
    void deleteNamespace(String nsName);

    /**
     * Switch to an existing namespace.
     *
     * @param targetNamespace the namespace needs to be switched.
     * @throws OMSSecurityException when have no authority to delete this namespace.
     * @throws OMSTimeOutException when the given timeout elapses before the delete operation completes.
     * @throws OMSDestinationException when have no given destination in the server.
     * @throws OMSRuntimeException when the {@code ResourceManager} fails to delete the namespace due to some internal
     * error.
     */
    void switchNamespace(String targetNamespace);

    /**
     * Gets the namespace list in the current {@code MessagingAccessPoint}.
     *
     * @return the set of all namespaces.
     * @throws OMSSecurityException when have no authority to delete this namespace.
     * @throws OMSTimeOutException when the given timeout elapses before the list operation completes.
     * @throws OMSRuntimeException when the {@code ResourceManager} fails to list the namespace due to some internal
     * error.
     */
    Set<String> listNamespaces();

    /**
     * Creates a {@code Queue} resource in the configured namespace with some preset attributes.
     * <p>
     * The standard OMS {@code Queue} schema must start with the {@code Namespace} prefix:
     * <p>
     * {@literal <namespace_name>://<queue_name>}
     *
     * @param queueName the name of the new queue.
     * @throws OMSSecurityException when have no authority to create this queue.
     * @throws OMSTimeOutException when the given timeout elapses before the create operation completes.
     * @throws OMSDestinationException when the given destination has been created in the server.
     * @throws OMSRuntimeException when the {@code ResourceManager} fails to delete the namespace due to some internal
     * error.
     */
    void createQueue(String queueName);

    /**
     * Deletes an existing queue resource.
     *
     * @param queueName the queue needs to be deleted.
     * @throws OMSSecurityException when have no authority to delete this namespace.
     * @throws OMSTimeOutException when the given timeout elapses before the delete operation completes.
     * @throws OMSDestinationException when have no given destination in the server.
     * @throws OMSRuntimeException when the {@code ResourceManager} fails to delete the namespace due to some internal
     * error.
     */
    void deleteQueue(String queueName);

    /**
     * Gets the queue list in the specific namespace.
     *
     * @param nsName the specific namespace.
     * @return the set of queues exists in current namespace.
     * @throws OMSSecurityException when have no authority to delete this namespace.
     * @throws OMSTimeOutException when the given timeout elapses before the list operation completes.
     * @throws OMSRuntimeException when the {@code ResourceManager} fails to list the namespace due to some internal
     * error.
     */
    Set<String> listQueues(String nsName);

    /**
     * In order to enable consumers to get the message in the specified mode, the filter rule follows the sql standard
     * to filter out messages.
     *
     * @param queueName queue name.
     * @param filterString SQL expression to filter out messages.
     * @throws OMSSecurityException when have no authority to add this filter.
     * @throws OMSTimeOutException when the given timeout elapses before the add filter operation completes.
     * @throws OMSRuntimeException when the {@code ResourceManager} fails to add a new filter due to some internal
     * error.
     */
    void filter(String queueName, String filterString);

    /**
     * Routing from sourceQueue to targetQueue. Both queues are could be received messages after creating route action.
     *
     * @param sourceQueue source queue, process messages received from producer and duplicate those to target queue.
     * @param targetQueue receive messages from source queue.
     * @throws OMSSecurityException when have no authority to add this routing.
     * @throws OMSTimeOutException when the given timeout elapses before the routing operation completes.
     * @throws OMSRuntimeException when the {@code ResourceManager} fails to add a new routing due to some internal
     * error.
     */
    void routing(String sourceQueue, String targetQueue);

}
