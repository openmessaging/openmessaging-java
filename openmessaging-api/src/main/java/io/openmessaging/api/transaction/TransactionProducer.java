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

package io.openmessaging.api.transaction;

import io.openmessaging.api.Admin;
import io.openmessaging.api.Message;
import io.openmessaging.api.SendResult;

/**
 * Send transactional message.
 *
 * @version OMS 1.2.0
 * @since OMS 1.2.0
 */
public interface TransactionProducer extends Admin {

    /**
     * This method is used to send a transactional message. A transactional message is sent in three steps:
     * <ol>
     * <li>The service implementation class first sends a half message to the message server;</li>
     * <li>Execute local transactions via <code>executer</code></li>
     * <li>According to the execution result of the previous step, it is decided to send a commit or roll back the
     * semi-message sent by the first step</li>
     * </ol>
     *
     * @param message transactional message
     * @param localTransactionExecutor local transactional executor
     * @param arg Apply a custom parameter that can be passed to the local transaction executor
     * @return Send result
     */
    SendResult send(final Message message,
        final LocalTransactionExecuter localTransactionExecutor,
        final Object arg);
}
