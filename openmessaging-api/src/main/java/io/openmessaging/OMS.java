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
import io.openmessaging.manager.ResourceManager;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The oms class provides some static methods to create a {@code MessagingAccessPoint} from the specified OMS driver url
 * and some useful util methods.
 * <p>
 * The complete OMS driver URL syntax is:
 * <p>
 * {@literal oms:<driver_type>://[account_id@]host1[:port1][,host2[:port2],...[,hostN[:portN]]]/<region>}
 * <p>
 * The first part of the URL specifies which OMS implementation is to be used, rocketmq is a optional driver type.
 * <p>
 * The brackets indicate that the extra access points are optional, and a correct OMS driver url needs at least one
 * access point, which consists of hostname and port, like localhost:8081.
 *
 * @version OMS 1.0.0
 * @see ResourceManager
 * @since OMS 1.0.0
 */
public final class OMS {
    /**
     * Returns a {@code MessagingAccessPoint} instance from the specified OMS driver url.
     *
     * @param url the specified OMS driver url
     * @return a {@code MessagingAccessPoint} instance
     * @throws OMSRuntimeException if the factory fails to create a {@code MessagingAccessPoint} due to some driver url
     * some syntax error or internal error.
     */
    public static MessagingAccessPoint getMessagingAccessPoint(String url) {
        return getMessagingAccessPoint(url, OMS.newKeyValue());
    }

    /**
     * Returns a {@code MessagingAccessPoint} instance from the specified OMS driver url with some preset attributes,
     * which will be passed to MessagingAccessPoint's implementation class as a unique constructor parameter.
     *
     * There are some standard attributes defined by OMS for this method, the same as {@link
     * MessagingAccessPoint#attributes()} ()}
     *
     * @param url the specified OMS driver url
     * @return a {@code MessagingAccessPoint} instance
     * @throws OMSRuntimeException if the factory fails to create a {@code MessagingAccessPoint} due to some driver url
     * some syntax error or internal error.
     */
    public static MessagingAccessPoint getMessagingAccessPoint(String url, KeyValue attributes) {
        return MessagingAccessPointAdapter.getMessagingAccessPoint(url, attributes);
    }

    /**
     * Returns a default and internal {@code KeyValue} implementation instance.
     *
     * @return a {@code KeyValue} instance
     */
    public static KeyValue newKeyValue() {
        return new DefaultKeyValue();
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
