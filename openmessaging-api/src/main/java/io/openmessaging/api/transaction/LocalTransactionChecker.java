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

import io.openmessaging.api.Message;

/**
 * Check local transactions, callback by broker.
 *
 * @version OMS 1.2.0
 * @since OMS 1.2.0
 */
public interface LocalTransactionChecker {

    /**
     * Check the local transaction, the Broker callback Producer, send the unfinished transaction to the Producer, and
     * the Producer will decide again whether the transaction is committed or rolled back.   
     *
     * @param msg message      
     * @return {@link TransactionStatus} Transaction status, including commit transaction, rollback transaction, unknown
     * state      
     */
    TransactionStatus check(final Message msg);
}
