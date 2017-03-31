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
 * A {@code Queue} supports streaming consumption.
 * <p>
 * A {@code IterableConsumer} object supports consume messages from a
 * specified queue by streaming way.
 *
 * @author vintagewang@apache.org
 * @author yukon@apache.org
 * @version OMS 1.0
 * @see MessagingAccessPoint#createIterableConsumer(String)
 * @since OMS 1.0
 */
public interface IterableConsumer {
    /**
     * Fetches the current offset of this partition iterator.
     *
     * @return the current offset, return -1 if the iterator is first created.
     */
    long current();

    /**
     * Fetches the first offset of this partition iterator.
     *
     * @return the first offset, return -1 if the partition has no message.
     */
    long begin();

    /**
     * Fetches the last offset of this partition iterator.
     *
     * @return the last offset, return 0 if the iterator is first created.
     */
    long end();

    /**
     * Moves the current offset to the specified timestamp.
     * <p>
     * Moves the current offset to the first offset, if the given timestamp
     * is earlier than the first message's store timestamp in this partition iterator.
     * <p>
     * Moves the current offset to the last offset, if the given timestamp
     * is later than the last message's store timestamp in this partition iterator.
     *
     * @param timestamp the specified timestamp
     */
    void seekByTime(long timestamp);

    /**
     * Moves the current offset to the specified offset.
     *
     * @param offset the specified offset
     */
    void seekByOffset(long offset);

    /**
     * Persist this iterator to local or remote server, that depends on specified
     * implementation of {@link IterableConsumer}.
     */
    void persist();

    /**
     * Returns {@code true} if this partition iterator has more messages when
     * traversing the iterator in the forward direction.
     *
     * @return {@code true} if the partition iterator has more messages when
     *         traversing the iterator in the forward direction
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
     * @return {@code true} if the partition iterator has more messages when
     *         traversing the iterator in the reverse direction
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