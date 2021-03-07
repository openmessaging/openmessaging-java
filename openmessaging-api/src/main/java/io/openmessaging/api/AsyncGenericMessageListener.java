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
package io.openmessaging.api;

/**
 * Async message listener, registered for consume messages by consumer.
 * <p>
 * <strong>
 * Thread safe requirements: this interface will be invoked by multi threads, so users should keep thread safe during
 * the consume process.
 * </strong>
 * </p>
 */
public interface AsyncGenericMessageListener<T> extends GenericListener<T> {
    /**
     * Asynchronously consumer message interface which allow async commit consumption status, implemented by the application,
     * unstable situations such as network jitter may lead to message duplication, and services sensitive to repeated messages
     * need to guarantee idempotent.
     *
     * @param message received message
     * @param context async consume context bound to the message
     */
    void consume(final GenericMessage<T> message, final AsyncConsumeContext context);

}