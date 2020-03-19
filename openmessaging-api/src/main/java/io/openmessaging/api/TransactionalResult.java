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
 * The result of sending a OMS prepare message, this result can be used to commits or or rollback a prepare message.
 *
 * @version OMS 2.0.1
 * @since OMS 2.0.1
 */
public abstract class TransactionalResult extends SendResult {

    /**
     * Commits a transaction.
     */
    public abstract void commit();

    /**
     * Rolls back a transaction.
     */
    public abstract void rollback();

}