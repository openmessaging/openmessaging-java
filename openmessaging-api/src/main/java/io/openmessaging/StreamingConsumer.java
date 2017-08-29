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

import java.util.List;

/**
 * A {@code Queue} is divided by many streams.
 * <p>
 * A {@code StreamingConsumer} object supports consume messages from a
 * specified partition like a iterator.
 *
 * @author yukon@apache.org
 * @version OMS 1.0
 * @see Stream
 * @since OMS 1.0
 */
public interface StreamingConsumer extends ServiceLifecycle {
    /**
     * Returns the attributes of this {@code StreamingConsumer} instance.
     * Changes to the return {@code KeyValue} are not reflected in physical {@code StreamingConsumer},
     * and use {@link ResourceManager#setConsumerProperties(String, KeyValue)} to modify.
     * <p>
     * There are some standard attributes defined by OMS for {@code StreamingConsumer}:
     * <ul>
     * <li> {@link OMSBuiltinKeys#CONSUMER_ID}, the unique consumer id for a consumer instance.
     * <li> {@link OMSBuiltinKeys#OPERATION_TIMEOUT}, the default timeout period for operations of {@code
     * StreamingConsumer}.
     * </ul>
     *
     * @return the attributes
     */
    KeyValue properties();

    /**
     * Returns all the streams of the related queue.
     *
     * @return the partition list
     */
    List<String> streams();

    /**
     * Returns all the consumers of the related queue.
     *
     * @return the consumers list
     */
    List<String> consumers();

    /**
     * Creates a partition iterator from a specified partition.
     *
     * @param messageStreams a specified partition
     * @return a partition iterator
     */
    Stream stream(String streamName);

    /**
     * Creates a partition iterator from a specified partition, with some preset attributes.
     *
     * @param messageGroupName a specified partition
     * @return a partition iterator
     * @see Stream#properties()
     */
    Stream stream(String streamName, KeyValue properties);
}
