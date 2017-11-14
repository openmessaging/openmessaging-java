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

package io.openmessaging;

import io.openmessaging.api.Client;
import io.openmessaging.api.ClientConfiguration;
import io.openmessaging.api.ClientDriver;
import io.openmessaging.exception.OMSException;
import io.openmessaging.internal.AccessPointURI;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * The basic service for managing a set of OMS drivers.
 */
public class ClientDriverManager {

    static class ClientDriverInfo {

        final Class<? extends ClientDriver> driverClass;
        final String driverClassName;

        ClientDriverInfo(Class<? extends ClientDriver> driverClass) {
            this.driverClass = driverClass;
            this.driverClassName = this.driverClass.getName();
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("driver[")
                .append(driverClassName)
                .append("]");
            return sb.toString();
        }
    }

    private static final ConcurrentMap<String, ClientDriverInfo> drivers;
    private static boolean initialized = false;

    static {
        drivers = new ConcurrentHashMap<String, ClientDriverInfo>();
        initialize();
    }

    static void initialize() {
        if (initialized) {
            return;
        }
        loadInitialDrivers();
        initialized = true;
    }

    private static void loadInitialDrivers() {
        Set<String> driverList = new HashSet<>();
        // load drivers from system property
        String driversStr = System.getProperty("openmessaging.client.drivers");
        if (null != driversStr) {
            String[] driversArray = driversStr.split(":");
            for (String driver : driversArray) {
                driverList.add(driver);
            }
        }
        // initialize the drivers
        for (String driverClsName : driverList) {
            try {
                Class<ClientDriver> driverClass =
                    (Class<ClientDriver>) Class.forName(driverClsName, true, ClassLoader.getSystemClassLoader());
                ClientDriver driver = driverClass.newInstance();
                ClientDriverInfo driverInfo = new ClientDriverInfo(driverClass);
                drivers.put(driver.scheme().toLowerCase(), driverInfo);
            } catch (Exception ex) {
                // logger.warn("Failed to load client driver {} : ", driverClsName, ex);
            }
        }
    }

    /**
     * Prevent the ClientDriverManager class from being instantiated.
     */
    private ClientDriverManager() {}

    /**
     * Register the client {@code driver}.
     *
     * @param driver the client driver
     */
    public static void registerDriver(String backend, Class<? extends ClientDriver> driver) {
        if (!initialized) {
            initialize();
        }

        String scheme = backend.toLowerCase();
        ClientDriverInfo oldDriverInfo = drivers.get(scheme);
        if (null != oldDriverInfo) {
            return;
        }
        ClientDriverInfo newDriverInfo = new ClientDriverInfo(driver);
        oldDriverInfo = drivers.putIfAbsent(scheme, newDriverInfo);
        if (null != oldDriverInfo) {
            // logger.debug("Driver for {} is already there.", scheme);
        }
    }

    /**
     * Retrieve the client driver for {@code scheme}.
     *
     * @param scheme the scheme for the client driver
     * @return the client driver
     * @throws NullPointerException when scheme is null
     */
    static ClientDriver getDriverByType(String scheme) {
        Objects.requireNonNull(scheme, "Driver Scheme is null");
        if (!initialized) {
            initialize();
        }
        ClientDriverInfo driverInfo = drivers.get(scheme.toLowerCase());
        if (null == driverInfo) {
            throw new IllegalArgumentException("Unknown backend " + scheme);
        }
        try {
            return driverInfo.driverClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Failed to initialize driver : " + driverInfo);
        }
    }

    /**
     * Retrieve the client driver for {@code uri}.
     *
     * @param uri the openmessaging uri
     * @return the client driver for {@code uri}
     * @throws NullPointerException if the openmessaging {@code uri} is null or doesn't have scheme
     *          or there is no client driver registered for the scheme
     * @throws IllegalArgumentException if the openmessaging {@code uri} scheme is illegal
     */
    private static ClientDriver getDriver(String uri) {
        // Validate the uri and load the backend according to scheme
        Objects.requireNonNull(uri, "OpenMessaging uri is null");
        AccessPointURI accessPointURI = new AccessPointURI(uri);
        return getDriverByType(accessPointURI.getDriverType());
    }

    public static Client createClient(String uri) throws OMSException {
        return getDriver(uri).create(uri);
    }

    public static Client createClient(String uri, ClientConfiguration conf) throws OMSException {
        return getDriver(uri).create(uri, conf);
    }

}
