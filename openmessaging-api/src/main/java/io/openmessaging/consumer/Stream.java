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
import io.openmessaging.OMSBuiltinKeys;
import io.openmessaging.ServiceLifecycle;

/**
 * A {@code Queue} is consists of many streams. A {@code Stream} supports consume
 * messages from a specified stream through an iterator.
 *
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
     * <li> {@link OMSBuiltinKeys#BEGIN_POSITION}, the begin position of this stream.
     * <li> {@link OMSBuiltinKeys#END_POSITION}, the end position of this stream.
     * <li> {@link OMSBuiltinKeys#BEGIN_TIMESTAMP}, the begin position represented
     * by this timestamp of this stream.
     * <li> {@link OMSBuiltinKeys#END_TIMESTAMP}, the end offset position
     * by this timestamp of this stream.
     * </ul>
     *
     * @return the attributes
     */
    KeyValue properties();

    /**
     * Creates a {@code StreamIterator} from the begin position of current stream.
     *
     * @return a message iterator at the begin position.
     */
    StreamIterator begin();

    /**
     * Creates a {@code StreamIterator} from the end position of current stream.
     *
     * @return a message iterator at the begin position.
     */
    StreamIterator end();

    /**
     * Creates a {@code StreamIterator} from the fixed position represented
     * by the specified timestamp of current stream.
     * <p>
     * Creates a {@code StreamIterator} from the begin position if the given timestamp
     * is earlier than the first message's store timestamp in this stream.
     * <p>
     * Creates a {@code StreamIterator} from the end position, if the given timestamp
     * is later than the last message's store timestamp in this stream.
     *
     * @param timestamp the specified timestamp
     * @return a message iterator at the specified timestamp
     */
    StreamIterator seek(long position);
}