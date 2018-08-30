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

import io.openmessaging.exception.OMSRuntimeException;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.failBecauseExceptionWasNotThrown;

public class AccessPointURITest {
    private String fullSchemaURI = "oms:rocketmq://alice@rocketmq.apache.org/us-east";

    @Test
    public void testParse_DriverIsIllegal() {
        String missDriverType = "oms://alice@rocketmq.apache.org/us-east";
        try {
            new AccessPointURI(missDriverType);
            failBecauseExceptionWasNotThrown(OMSRuntimeException.class);
        } catch (Exception e) {
            assertThat(e).hasMessageContaining(String.format("The OMS driver URL [%s] is illegal.", missDriverType));
        }


        String missRegion = "oms:rocketmq://alice@rocketmq.apache.org/";
        try {
            new AccessPointURI(missRegion);
            failBecauseExceptionWasNotThrown(OMSRuntimeException.class);
        } catch (Exception e) {
            assertThat(e).hasMessageContaining(String.format("The OMS driver URL [%s] is illegal.", missRegion));
        }
    }

    @Test
    public void testGetAccessPointString() {
        AccessPointURI accessPointURI = new AccessPointURI(fullSchemaURI);
        assertThat(accessPointURI.getAccessPointString()).isEqualTo(fullSchemaURI);
    }

    @Test
    public void testGetDriverType() {
        AccessPointURI accessPointURI = new AccessPointURI(fullSchemaURI);
        assertThat(accessPointURI.getDriverType()).isEqualTo("rocketmq");
    }

    @Test
    public void testGetAccountId() {
        AccessPointURI accessPointURI = new AccessPointURI(fullSchemaURI);
        assertThat(accessPointURI.getAccountId()).isEqualTo("alice");
    }

    @Test
    public void testGetHosts() {
        AccessPointURI accessPointURI = new AccessPointURI(fullSchemaURI);
        assertThat(accessPointURI.getHosts()).isEqualTo("rocketmq.apache.org");

        String multipleHostsURI = "oms:rocketmq://alice@rocketmq.apache.org,pulsar.apache.org:9091/us-east:default_space";
        accessPointURI = new AccessPointURI(multipleHostsURI);
        assertThat(accessPointURI.getHosts()).isEqualTo("rocketmq.apache.org,pulsar.apache.org:9091");
    }

    @Test
    public void testGetRegion() {
        AccessPointURI accessPointURI = new AccessPointURI(fullSchemaURI);

        assertThat(accessPointURI.getRegion()).isEqualTo("us-east");
    }
}