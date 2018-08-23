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

import io.openmessaging.Message;
import io.openmessaging.exception.OMSRuntimeException;

/**
 * A {@code StreamingIterator} is provided by {@code Stream} and is used to
 * retrieve messages a specified stream like a read-only iterator.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface StreamingIterator {

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
     * @throws OMSRuntimeException if the iteration has no more message, or
     * the the consumer fails to receive the next message
     */
    Message next();

    /**
     * Returns {@code true} if this partition iterator has more messages when
     * traversing the iterator in the reverse direction.
     *
     * @return {@code true} if the partition iterator has more messages when
     * traversing the iterator in the reverse direction
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
     * @throws OMSRuntimeException if the iteration has no previous message, or
     * the the consumer fails to receive the previous message
     */
    Message previous();

    /**
     * Returns the position of the message that would be returned by a
     * subsequent call to {@link #next}.
     *
     * @return the position of the next message
     * @throws OMSRuntimeException if the iteration has no next message
     */
    String nextPosition();

    /**
     * Returns the position of the message that would be returned by a
     * subsequent call to {@link #previous}.
     *
     * @return the position of the previous message
     * @throws OMSRuntimeException if the iteration has no previous message
     */
    String previousPosition();
}