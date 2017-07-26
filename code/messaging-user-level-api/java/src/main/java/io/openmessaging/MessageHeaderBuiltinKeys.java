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
 * The {@code MessageHeaderBuiltinKeys} class describes each OMS message header field.
 * A message's complete header is transmitted to all OMS clients that receive the
 * message.
 *
 * @author vintagewang@apache.org
 * @author yukon@apache.org
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface MessageHeaderBuiltinKeys {
    /**
     * The {@code MessageId} header field contains a value that uniquely identifies
     * each message sent by a {@code Producer}.
     * <p>
     * When a message is sent, MessageId is ignored.
     * <p>
     * When the send method returns it contains a producer-assigned value.
     */
    String MessageId = "MessageId";

    /**
     * The {@code Topic} header field is the destination which the message is being sent.
     * <p>
     * When a message is sent this value is should be set properly.
     * <p>
     * When a message is received, its {@code Topic} value must be equivalent to the
     * value assigned when it was sent.
     */
    String Topic = "Topic";

    /**
     * The {@code Queue} header field is the destination which the message is being sent.
     * <p>
     * When a message is sent this value is should be set properly.
     * <p>
     * When a message is received, its {@code Queue} value must be equivalent to the
     * value assigned when it was sent.
     */
    String Queue = "Queue";

    /**
     * The {@code BornTimestamp} header field contains the time a message was handed
     * off to a {@code Producer} to be sent.
     * <p>
     * When a message is sent, BornTimestamp will be set with current timestamp as the born
     * timestamp of a message in client side, on return from the send method, the message's
     * BornTimestamp header field contains this value. When a message is received its
     * BornTimestamp header field contains this same value.
     * <p>
     * This filed is a {@code long} value, measured in milliseconds.
     */
    String BornTimestamp = "BornTimestamp";

    /**
     * The {@code BornHost} header field contains the born host info of a message in client side.
     * <p>
     * When a message is sent, BornHost will be set with the local host info,
     * on return from the send method, the message's BornHost header field contains this value.
     * When a message is received its BornHost header field contains this same value.
     */
    String BornHost = "BornHost";

    /**
     * The {@code StoreTimestamp} header field contains the store timestamp of a message in server side.
     * <p>
     * When a message is sent, StoreTimestamp is ignored.
     * <p>
     * When the send method returns it contains a server-assigned value.
     * <p>
     * This filed is a {@code long} value, measured in milliseconds.
     */
    String StoreTimestamp = "StoreTimestamp";

    /**
     * The {@code StoreHost} header field contains the store host info of a message in server side.
     * <p>
     * When a message is sent, StoreHost is ignored.
     * <p>
     * When the send method returns it contains a server-assigned value.
     */
    String StoreHost = "StoreHost";

    /**
     * The {@code StartTime} header field contains the startup timestamp that a message
     * can be delivered to consumer client.
     * <p>
     * If StartTime field isn't set explicitly, use BornTimestamp as the startup timestamp.
     * <p>
     * This filed is a {@code long} value, measured in milliseconds.
     */
    String StartTime = "StartTime";

    /**
     * The {@code StopTime} header field contains the stop timestamp that a message
     * should be discarded after this timestamp, and no consumer can consume this message.
     * <p>
     * {@code (StartTime ~ StopTime)} represents a absolute valid interval that a message
     * can be delivered in it.
     * <p>
     * If a earlier timestamp is set than StartTime, that means the message does not expire.
     * <p>
     * This filed is a {@code long} value, measured in milliseconds.
     * <p>
     * When an undelivered message's expiration time is reached, the message should be destroyed.
     * OMS does not define a notification of message expiration.
     */
    String StopTime = "StopTime";

    /**
     * The {@code Timeout} header field contains the expiration time, it represents a
     * time-to-live value.
     * <p>
     * {@code (BornTimestamp ~ BornTimestamp + Timeout)} represents a relative valid interval
     * that a message can be delivered in it.
     * If the Timeout field is specified as zero, that indicates the message does not expire.
     * <p>
     * The Timeout header field has higher priority than StartTime/StopTime header fields.
     * <p>
     * When an undelivered message's expiration time is reached, the message should be destroyed.
     * OMS does not define a notification of message expiration.
     */
    String Timeout = "Timeout";

    /**
     * The {@code Priority} header field contains the priority level of a message,
     * a message with higher priority values should be delivered preferentially.
     * <p>
     * OMS defines a ten level priority value with 0 as the lowest priority and 9 as the highest.
     * OMS does not require that a provider strictly implement priority ordering of messages;
     * however, it should do its best to deliver expedited messages ahead of normal messages.
     * <p>
     * If Priority field isn't set explicitly, use {@code 4} as the default priority.
     */
    String Priority = "Priority";

    /**
     * The {@code Reliability} header field contains the reliability level of a message.
     * A MOM server should guarantee the reliability level for a message.
     */
    String Reliability = "Reliability";

    /**
     * The {@code SearchKey} header field contains index search key of a message.
     * Clients can query similar messages by search key, and have a quick response.
     */
    String SearchKey = "SearchKey";

    /**
     * The {@code ScheduleExpression} header field contains schedule expression of a message.
     * <p>
     * The message will be delivered by the specified ScheduleExpression, which is a CRON expression.
     *
     * @see <a href="https://en.wikipedia.org/wiki/Cron#CRON_expression">https://en.wikipedia.org/wiki/Cron#CRON_expression</a>
     */
    String ScheduleExpression = "ScheduleExpression";

    /**
     * The {@code ShardingKey} header field contains the sharding key of a message.
     * The messages with same SHARDING_PARTITION should be sent to a destination orderly.
     */
    String ShardingKey = "ShardingKey";

    /**
     * The {@code TraceId} header field contains the trace id of a message, which represents a global and unique
     * identification, and can be used in distributed system to trace the whole call link.
     */
    String TraceId = "TraceId";
}
