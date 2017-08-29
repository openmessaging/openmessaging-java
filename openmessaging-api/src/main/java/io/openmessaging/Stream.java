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
public interface Stream extends ServiceLifecycle {
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
     * Fetches the current offset of this partition iterator.
     *
     * @return the current offset, return -1 if the iterator is first created.
     */
    MessageIterator current();

    /**
     * Fetches the first offset of this partition iterator.
     *
     * @return the first offset, return -1 if the partition has no message.
     */
    MessageIterator begin();

    /**
     * Fetches the last offset of this partition iterator.
     *
     * @return the last offset, return 0 if the iterator is first created.
     */
    MessageIterator end();

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
    MessageIterator seekByTime(long timestamp);
}