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

import io.openmessaging.exception.OMSRuntimeException;

/**
 * A {@code Queue} is divided by many streams.
 * <p>
 * A {@code Stream} object supports consume messages from a
 * specified partition like a iterator.
 *
 * @author vintagewang@apache.org
 * @author yukon@apache.org
 * @version OMS 1.0
 * @see StreamingConsumer#stream(String)
 * @since OMS 1.0
 */
public interface MessageIterator extends ServiceLifecycle {
    /**
     * Returns the attributes of this {@code Stream} instance.
     * <p>
     * There are some standard attributes defined by OMS for {@code Stream}:
     * <ul>
     * <li> {@link OMSBuiltinKeys#OPERATION_TIMEOUT}, the default timeout period for operations of {@code
     * Stream}.
     * <li> {@link OMSBuiltinKeys#BEGIN_OFFSET}, the begin offset boarder of this partition iterator.
     * <li> {@link OMSBuiltinKeys#END_OFFSET}, the end offset boarder of this partition iterator.
     * <li> {@link OMSBuiltinKeys#BEGIN_TIMESTAMP}, the begin offset represented
     * by this timestamp of this partition iterator.
     * <li> {@link OMSBuiltinKeys#END_TIMESTAMP}, the end offset represented
     * by this timestamp of this partition iterator.
     * </ul>
     *
     * @return the attributes
     */
    KeyValue properties();

    /**
     * Persist this iterator to local or remote server, that depends on specified
     * implementation of {@link MessageIterator}.
     */
    void commit(boolean flush);

    /**
     * Returns {@code true} if this partition iterator has more messages when
     * traversing the iterator in the forward direction.
     *
     * @return {@code true} if the partition iterator has more messages when traversing the iterator in the forward
     * direction
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