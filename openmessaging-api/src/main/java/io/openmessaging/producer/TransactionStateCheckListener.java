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

import io.openmessaging.Message;

/**
 * Each executor will be associated with a transactional message, can be used to execute local transaction branch and
 * submit the transaction status(commit or rollback).
 * <p>
 *
 * The associated message will be exposed to consumer when the local transaction has been committed, or be discarded if
 * local transaction has been rolled back.
 *
 * <p>
 * If the executor doesn't submit the transaction status for a long time, the server may lookup it forwardly through
 * {@link TransactionStateCheckListener#check(Message, TransactionalContext)}
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface TransactionStateCheckListener {

    /**
     * Checks the status of the local transaction branch.
     *
     * @param message the associated message.
     * @param context the check context.
     */
    void check(Message message, TransactionalContext context);

    interface TransactionalContext {
        /**
         * Commits a transaction.
         */
        void commit();

        /**
         * Rolls back a transaction.
         */
        void rollback();

        /**
         * Unknown transaction status, may be this transaction still on going.
         */
        void unknown();
    }
}
