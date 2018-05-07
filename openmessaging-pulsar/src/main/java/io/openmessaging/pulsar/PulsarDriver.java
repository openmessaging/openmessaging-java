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

package io.openmessaging.pulsar;

import io.openmessaging.api.Client;
import io.openmessaging.api.ClientConfiguration;
import io.openmessaging.api.ClientDriver;
import io.openmessaging.exception.OMSException;
import io.openmessaging.internal.AccessPointURI;
import org.apache.pulsar.client.api.PulsarClientException;

public class PulsarDriver implements ClientDriver {
    @Override
    public int getMajorVersion() {
        return 0;
    }

    @Override
    public int getMinorVersion() {
        return 12;
    }

    @Override
    public String scheme() {
        return "pulsar";
    }

    @Override
    public boolean acceptsURL(String url) {
        AccessPointURI uri = new AccessPointURI(url);
        return "pulsar".equals(uri.getDriverType().toLowerCase());
    }

    @Override
    public Client create(String url) throws OMSException {
        AccessPointURI uri = new AccessPointURI(url);
        String pulsarUrl = "pulsar://" + uri.getAccessPointString();
        try {
            return new PulsarClient(pulsarUrl);
        } catch (PulsarClientException e) {
            throw new OMSException("create error", e);
        }
    }

    @Override
    public Client create(String url, ClientConfiguration conf) throws OMSException {
        throw new UnsupportedOperationException();
    }
}
