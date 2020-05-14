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
 * An interface for build message.
 *
 * @param <T> Type to be serialized from.
 * @version OMS 2.0.0
 * @since OMS 2.0.0
 */
public interface MessageBuilder<T> {

    /**
     * Used for message key.
     *
     * @param key message key
     * @return
     */
    MessageBuilder withKey(String key);

    /**
     * Used for set message tags.
     *
     * @param tags
     * @return
     */
    MessageBuilder withTags(String tags);

    /**
     * Used for set message sharding key.
     *
     * @param shardingKey
     * @return
     */
    MessageBuilder withShardingKey(String shardingKey);

    /**
     * Used for set user properties.
     *
     * @param key
     * @param value
     * @return
     */
    MessageBuilder withProperty(final String key, final String value);

    /**
     * Used for set message body.
     *
     * @param t object need to be serialized.
     * @return
     */
    MessageBuilder withBody(T t);

    /**
     * Get the topic which this {@code MessageBuilder} belongs to.
     *
     * @return
     */
    String getTopic();

    /**
     * Used for build message.
     *
     * @return message to be sent.
     */
    Message build();
}
