/*
 * Copyright 2017 OpenMessaging
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.openmessaging.api;

import io.openmessaging.exception.OMSException;

/**
 * The driver to provide OMS client.
 *
 * <p>The OMS framework allows for multiple messaging drivers.
 * Each driver should supply a class that implements this {@code ClientDriver} interface.
 * The OMS driver manager will try to load as many drivers as it can find and then for any given
 * <i>create client</i> request, it will ask each driver in turn to try to connect to the target url.
 *
 * <p>When a {@code ClientDriver} class is loaded, it should create an instance of itself and register
 * it with the driver manager. This means that a user can load and register a driver by calling
 * <i>Class.forName("foo.bah.ClientDriver")</i>
 *
 * <h2>OMS URL</h2>
 *
 * <p>
 * The complete OMS URL syntax is:
 * {@literal oms:<driver_scheme>://<access_point>[,<access_point>,...]/<namespace>}
 *
 * <p>The first part of the URL specifies which OMS scheme (implementation) is to be used. For example, pulsar and
 * rocketmq are two options.
 *
 * <p>The second part of the URL specifies the messaging access points. The brackets indicate that the extra access
 * points are optional, but a correct OMS driver url needs at least one access point, which consists of hostname and
 * port, like localhost:8081.
 *
 * <p>The last part of the URL specifies the namespace. A namespace wraps the OMS resources in an abstraction that
 * makes it appear to the users within the namespace that they have their own isolated instance of the global OMS
 * resources.
 */
public interface ClientDriver {

    /**
     * Retrieves the driver's major version number.
     *
     * @return the driver's major version number.
     */
    int getMajorVersion();

    /**
     * Retrieves the driver's minor version number.
     *
     * @return the driver's minor version number.
     */
    int getMinorVersion();

    /**
     * Retrieves the driver's scheme. E.g. "pulsar", "rocketmq".
     *
     * @return driver's scheme.
     */
    String scheme();

    /**
     * Retrieves whether the driver thinks that it can open a connection to the given URL.
     *
     * @param url the service url for the messaging service.
     * @return true if this driver understands the given <i>url</i>; false otherwise.
     */
    boolean acceptsURL(String url);

    /**
     * Create a new OMS client using the default client configuration.
     *
     * @param url the oms url to be used
     * @return a new oms client instance
     * @throws OMSException
     */
    Client create(String url) throws OMSException;

    /**
     * Create a new OMS client using the provided client configuration.
     *
     * @param url the oms url to be used
     * @param conf client configuration
     * @return a new oms client instance
     * @throws OMSException
     */
    Client create(String url, ClientConfiguration conf) throws OMSException;

}
