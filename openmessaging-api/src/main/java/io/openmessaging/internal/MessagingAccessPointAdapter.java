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

package io.openmessaging.internal;

import io.openmessaging.KeyValue;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.OMS;
import io.openmessaging.OMSBuiltinKeys;
import io.openmessaging.OMSResponseStatus;
import io.openmessaging.exception.OMSRuntimeException;
import java.lang.reflect.Constructor;

import static io.openmessaging.OMSResponseStatus.generateException;

/**
 * The {@code MessagingAccessPointAdapter} provides a common implementation to create a specified {@code
 * MessagingAccessPoint} instance, used by OMS internally.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
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
    public static MessagingAccessPoint getMessagingAccessPoint(String url, KeyValue attributes) {
        AccessPointURI accessPointURI = new AccessPointURI(url);
        String driverImpl = parseDriverImpl(accessPointURI.getDriverType(), attributes);

        attributes.put(OMSBuiltinKeys.ACCESS_POINTS, accessPointURI.getHosts());
        attributes.put(OMSBuiltinKeys.DRIVER_IMPL, driverImpl);
        attributes.put(OMSBuiltinKeys.REGION, accessPointURI.getRegion());
        attributes.put(OMSBuiltinKeys.ACCOUNT_ID, accessPointURI.getAccountId());

        try {
            Class<?> driverImplClass = Class.forName(driverImpl);
            Constructor constructor = driverImplClass.getConstructor(KeyValue.class);
            MessagingAccessPoint vendorImpl = (MessagingAccessPoint) constructor.newInstance(attributes);
            checkSpecVersion(OMS.specVersion, vendorImpl.version());
            return vendorImpl;
        } catch (Throwable e) {
            throw generateException(OMSResponseStatus.STATUS_10000, url);
        }
    }

    private static String parseDriverImpl(String driverType, KeyValue attributes) {
        if (attributes.containsKey(OMSBuiltinKeys.DRIVER_IMPL)) {
            return attributes.getString(OMSBuiltinKeys.DRIVER_IMPL);
        }
        return "io.openmessaging." + driverType + ".MessagingAccessPointImpl";
    }

    private static void checkSpecVersion(final String specVersion, final String implVersion) {
        String majorVerOfImpl;
        String majorVerOfSpec = specVersion.substring(0, specVersion.indexOf('.', specVersion.indexOf('.') + 1));
        try {
            majorVerOfImpl = implVersion.substring(0, implVersion.indexOf('.', implVersion.indexOf('.') + 1));
        } catch (Throwable e) {
            throw generateException(OMSResponseStatus.STATUS_10002, implVersion);
        }
        if (!majorVerOfSpec.equals(majorVerOfImpl)) {
            throw generateException(OMSResponseStatus.STATUS_10003, implVersion, specVersion);
        }
    }
}