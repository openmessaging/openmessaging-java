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

package io.openmessaging.api;

import io.openmessaging.api.internal.MessagingAccessPointAdapter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * <p>
 * The oms class provides some static methods to create a {@code MessagingAccessPoint} from the specified OMS driver url
 * and some useful util methods.
 * </p>
 *
 * <p>
 * The brackets indicate that the extra access points are optional, and a correct OMS driver url needs at least one
 * access point, which consists of hostname and port, like localhost:8081.
 * </p>
 *
 * @version OMS 1.2.0
 * @since OMS 1.1.0
 */
public final class OMS {

    private final Properties properties = new Properties();

    public static OMS builder() {
        return new OMS();
    }

    /**
     * Set the endpoint provided by messaging vendor.
     *
     * @param endpoint
     * @return
     */
    public OMS endpoint(String endpoint) {
        this.properties.put(OMSBuiltinKeys.ENDPOINT, endpoint);
        return this;
    }

    public OMS schemaRegistryUrl(String schemaRegistryUrl) {
        this.properties.put(OMSBuiltinKeys.SCHEMA_REGISTRY_URL, schemaRegistryUrl);
        return this;
    }

    /**
     * Set the region provided by messaging vendor.
     *
     * @param region
     * @return
     */
    public OMS region(String region) {
        this.properties.put(OMSBuiltinKeys.REGION, region);
        return this;
    }

    /**
     * <p>
     * Set the the driver type of the specified MessagingAccessPoint's * implementation, the default value is {@literal
     * io.openmessaging.<driver_type>.MessagingAccessPointImpl}.
     * </p>
     *
     * <p>
     * But if the {@link OMS#driverImpl(String)} attribute was set, this attribute will be ignored.
     * </p>
     *
     * @param driver
     * @return
     */
    public OMS driver(String driver) {
        this.properties.put(OMSBuiltinKeys.DRIVER, driver);
        return this;
    }

    /**
     * <p>
     * Set the the fully qualified class name of the specified MessagingAccessPoint's * implementation, the default
     * value is {@literal io.openmessaging.<driver_type>.MessagingAccessPointImpl}.
     * </p>
     *
     * <p>
     * If this attribute was set, {@link OMS#driver(String)} will be ignored.
     * </p>
     *
     * @param driverImpl
     * @return
     */
    public OMS driverImpl(String driverImpl) {
        this.properties.put(OMSBuiltinKeys.DRIVER_IMPL, driverImpl);
        return this;
    }

    /**
     * <p>
     * Set credentials used by the client.
     * </p>
     *
     * @param credentials provided by vendors.
     * @return
     */
    public OMS withCredentials(Properties credentials) {
        if (credentials.getProperty(OMSBuiltinKeys.ACCESS_KEY) != null) {
            this.properties.put(OMSBuiltinKeys.ACCESS_KEY, credentials.getProperty(OMSBuiltinKeys.ACCESS_KEY));
        }
        if (credentials.getProperty(OMSBuiltinKeys.SECRET_KEY) != null) {
            this.properties.put(OMSBuiltinKeys.SECRET_KEY, credentials.getProperty(OMSBuiltinKeys.SECRET_KEY));
        }
        if (credentials.getProperty(OMSBuiltinKeys.SECURITY_TOKEN) != null) {
            this.properties.put(OMSBuiltinKeys.SECURITY_TOKEN, credentials.getProperty(OMSBuiltinKeys.SECURITY_TOKEN));
        }
        return this;
    }

    public MessagingAccessPoint build() {
        return MessagingAccessPointAdapter.getMessagingAccessPoint(this.properties);
    }

    /**
     * Set extra custom configs.
     *
     * @param config extra configs
     * @return
     */
    public MessagingAccessPoint build(Properties config) {
        Set<Map.Entry<Object, Object>> entrySet = config.entrySet();
        for (Map.Entry<Object, Object> entry : entrySet) {
            if (!this.properties.containsKey(entry.getKey())) {
                this.properties.put(entry.getKey(), entry.getValue());
            }
        }
        return MessagingAccessPointAdapter.getMessagingAccessPoint(this.properties);
    }

    /**
     * The version format is X.Y.Z (Major.Minor.Patch), a pre-release version may be denoted by appending a hyphen and a
     * series of dot-separated identifiers immediately following the patch version, like X.Y.Z-alpha.
     *
     * <p>
     * OMS version follows semver scheme partially.
     *
     * @see <a href="http://semver.org">http://semver.org</a>
     */
    public static String specVersion = "UnKnown";

    static {
        InputStream stream = OMS.class.getClassLoader().getResourceAsStream("oms.spec.properties");
        try {
            if (stream != null) {
                Properties properties = new Properties();
                properties.load(stream);
                specVersion = String.valueOf(properties.get("version"));
            }
        } catch (IOException ignore) {
        }
    }

    private OMS() {
    }
}
