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

import java.io.Serializable;
import java.util.Properties;

/**
 * The {@code Message} interface is the root interface of all OMS messages, and the most commonly used OMS message is
 * {@link Message}.
 * <p>
 * Most message-oriented middleware (MOM) products treat messages as lightweight entities that consist of header and
 * body and is used by separate applications to exchange a piece of information, like <a
 * href="http://rocketmq.apache.org/">Apache RocketMQ</a>.
 * <p>
 * The header contains fields used by the messaging system that describes the message's meta information, while the body
 * contains the application data being transmitted.
 * <p>
 * As for the message header, OMS defines two kinds types: userProperties and systemProperties with respect to
 * flexibility in vendor implementation and user usage.
 * <ul>
 * <li>
 * System Properties, OMS defines some standard attributes in {@link SystemPropKey} that represent the characteristics
 * of the message.
 * </li>
 * <li>
 * User properties, some OMS vendors may require enhanced extra attributes of the message or some users may want to
 * clarify some customized attributes to draw the body. OMS provides the improved scalability for these scenarios.
 * </li>
 * </ul>
 * The body contains the application data being transmitted, which is generally ignored by the messaging system and
 * simply transmitted to its destination.
 * <p>
 * In BytesMessage, the body is just a byte array, may be compressed and uncompressed in the transmitting process by the
 * messaging system. The application is responsible for explaining the concrete content and format of the message body,
 * OMS is never aware of that.
 *
 * The body part is placed in the implementation classes of {@code Message}.
 *
 * @version OMS 1.2.0
 * @since OMS 1.2.0
 */
public class Message implements Serializable {

    private static final long serialVersionUID = -1385924226856188094L;

    protected Properties systemProperties;

    private String topic;

    private Properties userProperties;

    private byte[] body;

    public Message() {
        this(null, null, "", null);
    }

    public Message(String topic, String tag, String key, byte[] body) {
        this.topic = topic;
        this.body = body;

        this.putSystemProperties(SystemPropKey.TAG, tag);
        this.putSystemProperties(SystemPropKey.KEY, key);
    }

    public void putSystemProperties(final String key, final String value) {
        if (null == this.systemProperties) {
            this.systemProperties = new Properties();
        }

        if (key != null && value != null) {
            this.systemProperties.put(key, value);
        }
    }

    public Message(String topic, String tags, byte[] body) {
        this(topic, tags, "", body);
    }

    public void putUserProperties(final String key, final String value) {
        if (null == this.userProperties) {
            this.userProperties = new Properties();
        }

        if (key != null && value != null) {
            this.userProperties.put(key, value);
        }
    }

    public String getUserProperties(final String key) {
        if (null != this.userProperties) {
            return (String) this.userProperties.get(key);
        }

        return null;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTag() {
        return this.getSystemProperties(SystemPropKey.TAG);
    }

    public String getSystemProperties(final String key) {
        if (null != this.systemProperties) {
            return this.systemProperties.getProperty(key);
        }

        return null;
    }

    public void setTag(String tag) {
        this.putSystemProperties(SystemPropKey.TAG, tag);
    }

    public String getKey() {
        return this.getSystemProperties(SystemPropKey.KEY);
    }

    public void setKey(String key) {
        this.putSystemProperties(SystemPropKey.KEY, key);
    }

    public String getMsgID() {
        return this.getSystemProperties(SystemPropKey.MSGID);
    }

    public void setMsgID(String msgid) {
        this.putSystemProperties(SystemPropKey.MSGID, msgid);
    }

    public Properties getSystemProperties() {
        if (null == systemProperties) {
            return new Properties();
        }
        return systemProperties;
    }

    public void setSystemProperties(Properties systemProperties) {
        this.systemProperties = systemProperties;
    }

    public Properties getUserProperties() {
        if (null == userProperties) {
            return new Properties();
        }
        return userProperties;
    }

    public void setUserProperties(Properties userProperties) {
        this.userProperties = userProperties;
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }

    public int getReconsumeTimes() {
        String pro = this.getSystemProperties(SystemPropKey.RECONSUMETIMES);
        if (pro != null) {
            return Integer.parseInt(pro);
        }

        return 0;
    }

    public void setReconsumeTimes(final int value) {
        putSystemProperties(SystemPropKey.RECONSUMETIMES, String.valueOf(value));
    }

    public long getBornTimestamp() {
        String pro = this.getSystemProperties(SystemPropKey.BORNTIMESTAMP);
        if (pro != null) {
            return Long.parseLong(pro);
        }

        return 0L;
    }

    public void setBornTimestamp(final long value) {
        putSystemProperties(SystemPropKey.BORNTIMESTAMP, String.valueOf(value));
    }

    public String getBornHost() {
        String pro = this.getSystemProperties(SystemPropKey.BORNHOST);
        return pro == null ? "" : pro;
    }

    public void setBornHost(final String value) {
        putSystemProperties(SystemPropKey.BORNHOST, value);
    }

    public long getStartDeliverTime() {
        String pro = this.getSystemProperties(SystemPropKey.STARTDELIVERTIME);
        if (pro != null) {
            return Long.parseLong(pro);
        }

        return 0L;
    }

    public String getShardingKey() {
        String pro = this.getSystemProperties(SystemPropKey.SHARDINGKEY);
        return pro == null ? "" : pro;
    }

    public void setShardingKey(final String value) {
        putSystemProperties(SystemPropKey.SHARDINGKEY, value);
    }

    public void setStartDeliverTime(final long value) {
        putSystemProperties(SystemPropKey.STARTDELIVERTIME, String.valueOf(value));
    }

    /**
     * Get the offset of this message assigned by the broker.
     *
     * @return Message offset in relative partition
     */
    public long getOffset() {
        String v = getSystemProperties(SystemPropKey.CONSUMEOFFSET);
        if (v != null) {
            return Long.parseLong(v);
        }
        return 0;
    }

    /**
     * Get the partition to which the message belongs.
     *
     * @return Message offset in relative partition
     */
    public TopicPartition getTopicPartition() {
        return new TopicPartition(topic, getSystemProperties(SystemPropKey.PARTITION));
    }

    @Override
    public String toString() {
        return "Message [topic=" + topic + ", systemProperties=" + systemProperties + ", userProperties=" + userProperties + ", body="
            + (body != null ? body.length : 0) + "]";
    }

    static public class SystemPropKey {
        public static final String TAG = "__TAG";
        public static final String KEY = "__KEY";
        public static final String MSGID = "__MSGID";
        public static final String SHARDINGKEY = "__SHARDINGKEY";
        public static final String RECONSUMETIMES = "__RECONSUMETIMES";
        public static final String BORNTIMESTAMP = "__BORNTIMESTAMP";
        public static final String BORNHOST = "__BORNHOST";

        public static final String STARTDELIVERTIME = "__STARTDELIVERTIME";

        public static final String CONSUMEOFFSET = "__CONSUMEOFFSET";

        public static final String PARTITION = "__PARTITION";
    }
}
