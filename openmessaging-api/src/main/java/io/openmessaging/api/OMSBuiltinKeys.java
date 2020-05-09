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

/**
 * This is the centralized source for keys that are used for OMS standard attributes.
 *
 * @version OMS 1.1.0
 * @since OMS 1.1.0
 */
public interface OMSBuiltinKeys {
    /**
     * The {@code DRIVER_IMPL} key represents the vendor implementation entry of {@link MessagingAccessPoint}.
     */
    String DRIVER_IMPL = "driverImpl";

    /**
     * The {@code ACCESS_KEY} key shows the specified access key in OMS driver schema.
     */
    String ACCESS_KEY = "accessKey";

    /**
     * The {@code SECRET_KEY} key shows the specified secret key in OMS attribute.
     */
    String SECRET_KEY = "secretKey";

    /**
     * The {@code SECURITY_TOKEN} key shows the specified security token in OMS attribute.
     */
    String SECURITY_TOKEN = "securityToken";

    /**
     * The {@code REGION} key shows the specified region in OMS driver schema.
     */
    String REGION = "region";

    /**
     * The {@code ENDPOINT} key shows the specified host in OMS attribute.
     */
    String ENDPOINT = "endpoint";

    /**
     * The {@code DRIVER} key represents the vendor type of {@link MessagingAccessPoint}, but if {@code DRIVER_IMPL} is
     * not empty, this {@code DRIVER} value will be ignored.
     */
    String DRIVER = "driver";

    String SERIALLIZER = "serializer";

    String DESERIALIZER = "deserializer";

    String OPEN_META_URL = "openMetaUrl";

}
