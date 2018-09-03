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

package io.openmessaging.producer;

/**
 * The result of sending a OMS prepare message to server with the message id, this result can be used to commits or or
 * rolls back a prepare message.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface TransactionalResult extends SendResult {
    /**
     * The unique transactionId id related to the {@code TransactionResult} instance.
     *
     * @return the transactional id
     */
    String transactionId();

    /**
     * Commits a transaction.
     */
    void commit();

    /**
     * Rolls back a transaction.
     */
    void rollback();
}
