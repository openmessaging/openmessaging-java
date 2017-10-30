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
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.openmessaging.internal;

import static io.openmessaging.internal.InternalErrorCode.generateInternalException;

/**
 * Represents a <a href="https://github.com/openmessaging/specification/blob/master/oms_access_point_schema.md">AccessPoint String</a>.
 * The Connection String describes the details to connect a specific OMS service provider.
 */
public class AccessPointURI {
    private static final String PREFIX = "oms:";

    private final String accessPointString;
    private final String driverType;
    private final String accountId;
    private final String hosts;
    private final String region;
    private final String namespace;

    /**
     * The standard OMS access point schema is:
     * <p>
     * {@literal oms:<driver_type>://[account_id@]host1[:port1][,host2[:port2],...[,hostN[:portN]]]/<region>:<namespace>}
     * <p>
     *
     * More details please @see <a href="https://github.com/openmessaging/specification/blob/master/oms_access_point_schema.md">Access Point Schema</a>
     */
    private static final String PATTERN = "^oms:.+://.+/.+:.+$";

    AccessPointURI(String accessPointString) {
        validateAccessPointString(accessPointString);
        this.accessPointString = accessPointString;
        String unprocessedString = accessPointString.substring(PREFIX.length());

        // Split out the user OMS driver info
        int idx = unprocessedString.indexOf(":");
        this.driverType = unprocessedString.substring(0, idx);

        //Skip '<driver_type>://'
        unprocessedString = unprocessedString.substring(driverType.length() + 3);

        idx = unprocessedString.lastIndexOf('/');

        String userAndHostInformation = unprocessedString.substring(0, idx);
        String [] resourceInfo = unprocessedString.substring(idx + 1).split(":");

        idx = userAndHostInformation.indexOf('@');

        if (idx > 0) {
            accountId = userAndHostInformation.substring(0, idx);
            hosts = userAndHostInformation.substring(idx + 1);
        } else {
            hosts = userAndHostInformation;
            accountId = null;
        }

        this.region = resourceInfo[0];
        this.namespace = resourceInfo[1];
    }

    public String getAccessPointString() {
        return accessPointString;
    }

    public String getDriverType() {
        return driverType;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getHosts() {
        return hosts;
    }

    public String getRegion() {
        return region;
    }

    public String getNamespace() {
        return namespace;
    }

    private void validateAccessPointString(String accessPointString) {
        if (!accessPointString.matches(PATTERN)) {
            throw generateInternalException(InternalErrorCode.OMS_DRIVER_URL_ILLEGAL, accessPointString);
        }
    }
}
