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

import io.openmessaging.exception.OMSRuntimeException;
import io.openmessaging.internal.DefaultKeyValue;
import io.openmessaging.internal.MessagingAccessPointAdapter;

/**
 * A factory that provides some static methods to create a {@code MessagingAccessPoint}
 * from the specified OMS driver url.
 * <p>
 * The complete URL syntax is:
 * <p>
 * {@literal openmessaging:<driver_type>://<access_point>[,<access_point>,...]/<namespace>}
 * <p>
 * The first part of the URL specifies which OMS implementation is to be used, rocketmq is a
 * optional driver type.
 * <p>
 * The brackets indicate that the extra access points are optional, but a correct OMS driver url
 * needs at least one access point, which consists of hostname and port, like localhost:8081.
 * <p>
 * A namespace wraps the OMS resources in an abstraction that makes it appear to the users
 * within the namespace that they have their own isolated instance of the global OMS resources.
 *
 * @author vintagewang@apache.org
 * @author yukon@apache.org
 * @version OMS 1.0
 * @see ResourceManager
 * @since OMS 1.0
 */
public class MessagingAccessPointFactory {
    /**
     * Returns a {@code MessagingAccessPoint} instance from the specified OMS driver url.
     *
     * @param url the specified OMS driver url
     * @return a {@code MessagingAccessPoint} instance
     * @throws OMSRuntimeException if the factory fails to create a {@code MessagingAccessPoint}
     * due to some driver url some syntax error or internal error.
     */
    public static MessagingAccessPoint getMessagingAccessPoint(String url) {
        return getMessagingAccessPoint(url, newKeyValue());
    }

    /**
     * Returns a {@code MessagingAccessPoint} instance from the specified OMS driver url
     * with some preset properties, which will be passed to MessagingAccessPoint's implementation
     * class as a unique constructor parameter.
     *
     * There are some standard properties defined by OMS for this method:
     *
     * <ul>
     * <li> {@link PropertyKeys#DRIVER_IMPL}, the fully qualified class name of the specified MessagingAccessPoint's
     * implementation, the default value is {@literal io.openmessaging.<driver_type>.MessagingAccessPointImpl}.
     * </ul>
     *
     * @param url the specified OMS driver url
     * @return a {@code MessagingAccessPoint} instance
     * @throws OMSRuntimeException if the factory fails to create a {@code MessagingAccessPoint}
     * due to some driver url some syntax error or internal error.
     */
    public static MessagingAccessPoint getMessagingAccessPoint(String url, KeyValue properties) {
        return MessagingAccessPointAdapter.getMessagingAccessPoint(url, properties);
    }

    /**
     * Returns a default and internal {@code KeyValue} implementation instance.
     *
     * @return a {@code KeyValue} instance
     */
    public static KeyValue newKeyValue() {
        return new DefaultKeyValue();
    }
}
