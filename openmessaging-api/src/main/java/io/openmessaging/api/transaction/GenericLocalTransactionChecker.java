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

import io.openmessaging.api.GenericMessage;

/**
 * Check local transaction using generic message, callback from broker.
 *
 * @version OMS 2.0.1
 * @since OMS 2.0.1
 */
public interface GenericLocalTransactionChecker<T> {

    /**
     * Check the local transaction, the Broker callback Producer, send the unfinished transaction to the Producer, and
     * the Producer will decide again whether the transaction is committed or rolled back.   
     *
     * @param msg
     * @return {@link TransactionStatus} Transaction status, including commit transaction, rollback transaction, unknown
     * state      
     */
    TransactionStatus check(final GenericMessage<T> msg);

    /**
     * Returns the class of the payload in message.
     *
     * @return the class of message payload that user expects
     */
    Class<T> payloadClass();
}
