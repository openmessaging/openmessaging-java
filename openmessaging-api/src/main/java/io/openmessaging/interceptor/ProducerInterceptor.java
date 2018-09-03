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

package io.openmessaging.interceptor;

import io.openmessaging.Message;

/**
 * A {@code ProducerInterceptor} is used to intercept send operations of producer.
 * <p>
 * The interceptor is able to view or modify the message being transmitted and collect
 * the send record.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface ProducerInterceptor {
    /**
     * Invoked before the message is actually sent to the network.
     * <p>
     * This allows for modification of the message if necessary.
     *
     * @param message a message will be sent.
     * @param attributes the extensible attributes delivered to the intercept thread.
     */
    void preSend(Message message, Context attributes);

    /**
     * Invoked immediately after the successful send invocation.
     *
     * @param message the message is actually sent.
     * @param attributes the extensible attributes delivered to the intercept thread.
     */
    void postSend(Message message, Context attributes);
    
}
