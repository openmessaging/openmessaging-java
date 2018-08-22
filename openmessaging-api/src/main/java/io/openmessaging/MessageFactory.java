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

/**
 * A factory interface for creating {@code Message} objects.
 *
 */
public interface MessageFactory {
    /**
     * Creates a {@code Message} object. A {@code Message} object is used to send a message containing a
     * stream of uninterpreted bytes.
     * <p>
     * The returned {@code Message} object only can be sent to the specified queue.
     *
     * @param queueName the target queue to send
     * @param body the body data for a message
     * @return the created {@code BytesMessage} object
     */
    Message createMessage(String queueName, byte[] body);
}