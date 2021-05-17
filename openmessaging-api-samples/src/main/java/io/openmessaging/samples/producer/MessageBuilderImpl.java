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
package io.openmessaging.samples.producer;

import io.openmessaging.api.Message;
import io.openmessaging.api.MessageBuilder;
import io.openmessaging.api.serialization.Serializer;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MessageBuilderImpl<T> implements MessageBuilder<T> {

    private T body;

    private String keys;

    private String tags;

    private String shardingKey;

    private String topic;

    private final Map<String, String> userProperties = new HashMap<String, String>();

    private final Properties properties;

    private Serializer<T> serializer;

    public MessageBuilderImpl(Properties properties) throws Exception {
        this.properties = properties;
        this.serializer = new DefaultSerializer<>();
    }

    @Override public MessageBuilder withTopic(String topic) {
        this.topic = topic;
        return this;
    }

    @Override public MessageBuilder withValue(T t) {
        this.body = t;
        return this;
    }

    @Override public MessageBuilder withKey(String keys) {
        this.keys = keys;
        return this;
    }

    @Override public MessageBuilder withTags(String tags) {
        this.tags = tags;
        return this;
    }

    @Override public MessageBuilder withProperty(String key, String value) {
        this.properties.put(key, value);
        return this;
    }

    @Override public MessageBuilder withShardingKey(String shardingKey) {
        this.shardingKey = shardingKey;
        return this;
    }

    @Override public MessageBuilder<T> withSerializationType(String serializationType) {
        if (serializationType.equalsIgnoreCase("json")) {
            this.serializer = new JsonSerializer<T>();
        }
        return this;
    }

    @Override public String getTopic() {
        return this.topic;
    }

    @Override public Message build() {
        Message message = new Message();
        message.setKey(this.keys);
        message.setBody(this.serializer.serialize(this.topic, body));
        return message;
    }
}
