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
package io.openmessaging.extension;

import io.openmessaging.annotation.Optional;
import io.openmessaging.exception.OMSDestinationException;
import io.openmessaging.exception.OMSRuntimeException;
import io.openmessaging.exception.OMSSecurityException;
import io.openmessaging.exception.OMSTimeOutException;
import java.util.Set;

/**
 * <p>
 * This interface contains some methods are used for getting configurations related implementation. but this interface
 * are not mandatory.
 * </p>
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
@Optional
public interface Extension {

    /**
     * This method used for getting the related queue's meta data, and this method is optional, vendors may not provide
     * this method based on their implementation.
     * <p>
     *
     * @param queueName Queue name, message destination.
     * @return {@link QueueMetaData} Queue config in the server
     * @throws OMSSecurityException when have no authority to send messages to a given destination.
     * @throws OMSTimeOutException when the given timeout elapses before the send operation completes.
     * @throws OMSDestinationException when have no given destination in the server.
     * @throws OMSRuntimeException when the {@code Producer} fails to send the message due to some internal error.
     */
    Set<QueueMetaData> getQueueMetaData(String queueName);
}
