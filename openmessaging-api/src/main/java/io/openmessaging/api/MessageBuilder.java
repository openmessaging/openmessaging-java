/*
 * Copyright 2017 OpenMessaging
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.openmessaging.api;

import java.nio.ByteBuffer;
import java.util.Map;

/**
 * A message builder to build messages.
 *
 * <p>Use this class to create messages to be send by the OMS producer.
 */
public interface MessageBuilder {

    /**
     * Set the content of the message.
     *
     * @param data
     *            array containing the payload
     * @return message builder
     */
    default MessageBuilder setContent(byte[] data) {
        return setContent(ByteBuffer.wrap(data));
    }

    /**
     * Set the content of the message.
     *
     * @param data
     *            array containing the payload
     * @param offset
     *            offset into the data array
     * @param length
     *            length of the payload starting from the above offset
     * @return message builder
     */
    default MessageBuilder setContent(byte[] data, int offset, int length) {
        return setContent(ByteBuffer.wrap(data, offset, length));
    }

    /**
     * Set the content of the message.
     *
     * @param buf
     *            a {@link ByteBuffer} with the payload of the message
     * @return message builder
     */
    MessageBuilder setContent(ByteBuffer buf);

    //
    // Headers and Metadata
    //

    /**
     * Sets a new property on a message.
     *
     * @param name
     *            the name of the property
     * @param value
     *            the associated value
     * @return message builder
     */
    MessageBuilder setProperty(String name, String value);

    /**
     * Add all the properties in the provided map.
     *
     * @param properties a map of properties to add to the map.
     * @return message builder
     */
    MessageBuilder setProperties(Map<String, String> properties);

    /**
     * Sets the key of the message for routing policy.
     *
     * @param key routing key.
     * @return message builder
     */
    MessageBuilder setRoutingKey(String key);

    /**
     * Set the event time for a given message.
     *
     * @param timestamp
     *              event time assigned to this message.
     * @return message builder
     */
    MessageBuilder setEventTime(long timestamp);

    /**
     * Specify a custom sequence id for the message being published.
     *
     * <p>This sequence id can be used by the messaging system for achieving idempotent producing.
     *
     * <p>The sequence id should follow couple rules:
     * <ol>
     * <li><code>sequenceId >= 0</code>
     * <li>Sequence id for a message needs to be greater than sequence id for earlier messages:
     * <code>sequenceId(N+1) > sequenceId(N)</code>
     * <li>It's not necessary for sequence ids to be consecutive. There can be holes between messages. Eg. the
     * <code>sequenceId</code> could represent an offset or a cumulative size.
     * </ol>
     *
     * @param sequenceId
     *            the sequence id to assign to the current message
     * @return message builder
     */
    MessageBuilder setSequenceId(long sequenceId);

}
