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

/**
 * A {@code PullConsumerAck} object has all of the {@code PullConsumer} features,
 * and supports submit the consume result by acknowledgement.
 *
 * @author vintagewang@apache.org
 * @author yukon@apache.org
 *
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface PullConsumerAck extends PullConsumer {
    /**
     * Acknowledges the specified and consumed message.
     * <p>
     * Messages that have been received but not acknowledged may be redelivered.
     *
     * @throws OMSRuntimeException if the consumer fails to acknowledge the messages due to some internal error.
     */
    void ack(Message message);

    /**
     * Acknowledges the specified and consumed message with the specified properties.
     * <p>
     * Messages that have been received but not acknowledged may be redelivered.
     *
     * @throws OMSRuntimeException if the consumer fails to acknowledge the messages due to some internal error.
     */
    void ack(Message message, final KeyValue properties);
}