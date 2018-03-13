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

package io.openmessaging.consumer;

import io.openmessaging.KeyValue;
import io.openmessaging.Message;
import io.openmessaging.OMSBuiltinKeys;
import io.openmessaging.ServiceLifecycle;
import io.openmessaging.exception.OMSRuntimeException;

/**
 * A {@code MessageIterator} is provided by {@code Stream} and is used to
 * retrieve messages a specified stream like a read-only iterator.
 *
 * @version OMS 1.0
 * @see StreamingConsumer#stream(String)
 * @since OMS 1.0
 */
public interface MessageIterator extends ServiceLifecycle {
    /**
     * Returns the attributes of this {@code MessageIterator} instance.
     * <p>
     * There are some standard attributes defined by OMS for {@code Stream}:
     * <ul>
     * <li> {@link OMSBuiltinKeys#OPERATION_TIMEOUT}, the default timeout period for operations of {@code
     * Stream}.
     * </ul>
     *
     * @return the attributes
     */
    KeyValue properties();

    /**
     * Persists this iterator to local or remote server, that depends on specified
     * implementation of {@link MessageIterator}.
     */
    void commit();

    /**
     * Returns {@code true} if this iterator has more messages when
     * traversing the iterator in the forward direction.
     *
     * @return {@code true} if the iterator has more messages
     */
    boolean hasNext();

    /**
     * Returns the next message in the iteration and advances the offset position.
     * <p>
     * This method may be called repeatedly to iterate through the iteration,
     * or intermixed with calls to {@link #previous} to go back and forth.
     *
     * @return the next message in the list
     * @throws OMSRuntimeException if the iteration has no more message
     */
    Message next();

    /**
     * Returns {@code true} if this partition iterator has more messages when
     * traversing the iterator in the reverse direction.
     *
     * @return {@code true} if the partition iterator has more messages when traversing the iterator in the reverse
     * direction
     */
    boolean hasPrevious();

    /**
     * Returns the previous message in the iteration and moves the offset
     * position backwards.
     * <p>
     * This method may be called repeatedly to iterate through the iteration backwards,
     * or intermixed with calls to {@link #next} to go back and forth.
     *
     * @return the previous message in the list
     * @throws OMSRuntimeException if the iteration has no previous message
     */
    Message previous();
}