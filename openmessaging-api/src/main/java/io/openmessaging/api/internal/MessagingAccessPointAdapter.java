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

package io.openmessaging.api.internal;

import io.openmessaging.api.MessagingAccessPoint;
import io.openmessaging.api.OMSBuiltinKeys;
import io.openmessaging.api.OMSResponseStatus;
import io.openmessaging.api.exception.OMSRuntimeException;
import java.lang.reflect.Constructor;
import java.util.Properties;

import static io.openmessaging.api.OMSResponseStatus.generateException;

/**
 * The {@code MessagingAccessPointAdapter} provides a common implementation to create a specified {@code
 * MessagingAccessPoint} instance, used by OMS internally.
 *
 * @version OMS 1.1.0
 * @since OMS 1.1.0
 */
public class MessagingAccessPointAdapter {
    /**
     * Returns a {@code MessagingAccessPoint} instance from the specified OMS driver URL with some preset userHeaders.
     *
     * @param url the driver URL.
     * @param attributes the preset userHeaders.
     * @return a {@code MessagingAccessPoint} instance.
     * @throws OMSRuntimeException if the adapter fails to create a {@code MessagingAccessPoint} instance from the URL.
     */
    public static MessagingAccessPoint getMessagingAccessPoint(String url, Properties attributes) {
        AccessPointURI accessPointURI = new AccessPointURI(url);
        String driverImpl = parseDriverImpl(accessPointURI.getDriverType(), attributes);

        attributes.put(OMSBuiltinKeys.ACCESS_POINTS, accessPointURI.getHosts());
        attributes.put(OMSBuiltinKeys.DRIVER_IMPL, driverImpl);
        if (accessPointURI.getRegion() != null) {
            attributes.put(OMSBuiltinKeys.REGION, accessPointURI.getRegion());
        }
        if (accessPointURI.getAccountId() != null) {
            attributes.put(OMSBuiltinKeys.ACCOUNT_ID, accessPointURI.getAccountId());
        }

        try {
            Class<?> driverImplClass = Class.forName(driverImpl);
            Constructor constructor = driverImplClass.getConstructor(Properties.class);
            MessagingAccessPoint vendorImpl = (MessagingAccessPoint) constructor.newInstance(attributes);
            return vendorImpl;
        } catch (Throwable e) {
            throw generateException(OMSResponseStatus.STATUS_10000, url);
        }
    }

    private static String parseDriverImpl(String driverType, Properties attributes) {
        if (attributes.containsKey(OMSBuiltinKeys.DRIVER_IMPL)) {
            return attributes.getProperty(OMSBuiltinKeys.DRIVER_IMPL);
        }
        return "io.openmessaging." + driverType + ".MessagingAccessPointImpl";
    }

}