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
 * The {@code Message} interface is the root interface of all OMS messages, and the most commonly used OMS message is
 * {@link BytesMessage}.
 * <p>
 * Most message-oriented middleware (MOM) products treat messages as lightweight entities that consist of
 * header and body and is used by separate applications to exchange a piece of information,
 * like <a href="http://rocketmq.apache.org/">Apache RocketMQ</a>.
 * <p>
 * The header contains fields used by the messaging system that describes the message's meta information,
 * like QoS level, origin, destination, and so on, while the body contains the application data being transmitted.
 * <p>
 * As for the header, OMS defines two kinds types:  System Header and User Header,
 * with respect to flexibility in vendor implementation and user usage.
 * <ul>
 * <li>
 * System Header, OMS defines some standard attributes that represent the characteristics of the message.
 * </li>
 * <li>
 * User Header, some OMS vendors may require enhanced extra attributes of the message
 * or some users may want to clarify some customized attributes to draw the body.
 * OMS provides the improved scalability for these scenarios.
 * </li>
 * </ul>
 * The body contains the application data being transmitted,
 * which is generally ignored by the messaging system and simply transmitted to its destination.
 * <p>
 * In BytesMessage, the body is just a byte array, may be compressed and uncompressed
 * in the transmitting process by the messaging system.
 * The application is responsible for explaining the concrete content and format of the message body,
 * OMS is never aware of that.
 *
 * The body part is placed in the implementation classes of {@code Message}.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface Message {
    /**
     * Returns all the system header fields of the {@code Message} object as a {@code KeyValue}.
     *
     * @return the system headers of a {@code Message}
     * @see BuiltinKeys
     */
    KeyValue sysHeaders();

    /**
     * Returns all the customized user header fields of the {@code Message} object as a {@code KeyValue}.
     *
     * @return the user headers of a {@code Message}
     */
    KeyValue userHeaders();

    /**
     * Puts a {@code String}-{@code int} {@code KeyValue} entry to the system headers of a {@code Message}.
     *
     * @param key the key to be placed into the system headers
     * @param value the value corresponding to <tt>key</tt>
     */
    Message putSysHeaders(String key, int value);

    /**
     * Puts a {@code String}-{@code long} {@code KeyValue} entry to the system headers of a {@code Message}.
     *
     * @param key the key to be placed into the system headers
     * @param value the value corresponding to <tt>key</tt>
     */
    Message putSysHeaders(String key, long value);

    /**
     * Puts a {@code String}-{@code double} {@code KeyValue} entry to the system headers of a {@code Message}.
     *
     * @param key the key to be placed into the system headers
     * @param value the value corresponding to <tt>key</tt>
     */
    Message putSysHeaders(String key, double value);

    /**
     * Puts a {@code String}-{@code String} {@code KeyValue} entry to the system headers of a {@code Message}.
     *
     * @param key the key to be placed into the system headers
     * @param value the value corresponding to <tt>key</tt>
     */
    Message putSysHeaders(String key, String value);

    /**
     * Puts a {@code String}-{@code int} {@code KeyValue} entry to the user headers of a {@code Message}.
     *
     * @param key the key to be placed into the user headers
     * @param value the value corresponding to <tt>key</tt>
     */
    Message putUserHeaders(String key, int value);

    /**
     * Puts a {@code String}-{@code long} {@code KeyValue} entry to the user headers of a {@code Message}.
     *
     * @param key the key to be placed into the user headers
     * @param value the value corresponding to <tt>key</tt>
     */
    Message putUserHeaders(String key, long value);

    /**
     * Puts a {@code String}-{@code double} {@code KeyValue} entry to the user headers of a {@code Message}.
     *
     * @param key the key to be placed into the user headers
     * @param value the value corresponding to <tt>key</tt>
     */
    Message putUserHeaders(String key, double value);

    /**
     * Puts a {@code String}-{@code String} {@code KeyValue} entry to the user headers of a {@code Message}.
     *
     * @param key the key to be placed into the user headers
     * @param value the value corresponding to <tt>key</tt>
     */
    Message putUserHeaders(String key, String value);

    interface BuiltinKeys {
        /**
         * The {@code MESSAGE_ID} header field contains a value that uniquely identifies
         * each message sent by a {@code Producer}.
         * <p>
         * When a message is sent, MESSAGE_ID is assigned by the producer.
         */
        String MESSAGE_ID = "MESSAGE_ID";

        /**
         * The {@code DESTINATION} header field contains the destination to which the message is being sent.
         * <p>
         * When a message is sent this value is set to the right {@code Queue}, then the message will be sent to
         * the specified destination.
         * <p>
         * When a message is received, its destination is equivalent to the {@code Queue} where the message resides in.
         */
        String DESTINATION = "DESTINATION";

        /**
         * The {@code RECEIPT_HANDLE} header field contains an identifier associated with the act of receiving a message.
         * <p></p>
         * A new receipt handle will be set properly every time a message is received, and the consumer could use
         * it to acknowledge the consumed message.
         */
        String RECEIPT_HANDLE = "RECEIPT_HANDLE";

        /**
         * The {@code BORN_TIMESTAMP} header field contains the time a message was handed
         * off to a {@code Producer} to be sent.
         * <p>
         * When a message is sent, BORN_TIMESTAMP will be set with current timestamp as the born
         * timestamp of a message in client side, on return from the send method, the message's
         * BORN_TIMESTAMP header field contains this value.
         * <p>
         * When a message is received its, BORN_TIMESTAMP header field contains this same value.
         * <p>
         * This filed is a {@code long} value, measured in milliseconds.
         */
        String BORN_TIMESTAMP = "BORN_TIMESTAMP";

        /**
         * The {@code BORN_HOST} header field contains the born host info of a message in client side.
         * <p>
         * When a message is sent, BORN_HOST will be set with the local host info,
         * on return from the send method, the message's BORN_HOST header field contains this value.
         * <p>
         * When a message is received, its BORN_HOST header field contains this same value.
         */
        String BORN_HOST = "BORN_HOST";

        /**
         * The {@code STORE_TIMESTAMP} header field contains the store timestamp of a message in server side.
         * <p>
         * When a message is sent, STORE_TIMESTAMP is ignored.
         * <p>
         * When the send method returns it contains a server-assigned value.
         * <p>
         * This filed is a {@code long} value, measured in milliseconds.
         */
        String STORE_TIMESTAMP = "STORE_TIMESTAMP";

        /**
         * The {@code STORE_HOST} header field contains the store host info of a message in server side.
         * <p>
         * When a message is sent, STORE_HOST is ignored.
         * <p>
         * When the send method returns it contains a server-assigned value.
         */
        String STORE_HOST = "STORE_HOST";

        /**
         * The {@code START_TIME} header field contains the startup timestamp that a message
         * can be delivered to consumer client.
         * <p>
         * If START_TIME field isn't set explicitly, use BORN_TIMESTAMP as the startup timestamp.
         * <p>
         * This filed is a {@code long} value, measured in milliseconds.
         */
        String START_TIME = "START_TIME";

        /**
         * The {@code STOP_TIME} header field contains the stop timestamp that a message
         * should be discarded after this timestamp, and no consumer can consume this message.
         * <p>
         * {@code (START_TIME ~ STOP_TIME)} represents an absolute valid interval that a message
         * can be delivered in it.
         * <p>
         * If an earlier timestamp is set than START_TIME, that means the message does not expire.
         * <p>
         * This filed is a {@code long} value, measured in milliseconds.
         * <p>
         * When an undelivered message's expiration time is reached, the message should be destroyed.
         * OMS does not define a notification of message expiration.
         */
        String STOP_TIME = "STOP_TIME";

        /**
         * The {@code TIMEOUT} header field contains the expiration time, it represents a
         * time-to-live value.
         * <p>
         * {@code (BORN_TIMESTAMP ~ BORN_TIMESTAMP + TIMEOUT)} represents a relative valid interval
         * that a message can be delivered in it.
         * If the TIMEOUT field is specified as zero, that indicates the message does not expire.
         * <p>
         * The TIMEOUT header field has higher priority than START_TIME/STOP_TIME header fields.
         * <p>
         * When an undelivered message's expiration time is reached, the message should be destroyed.
         * OMS does not define a notification of message expiration.
         */
        String TIMEOUT = "TIMEOUT";

        /**
         * The {@code PRIORITY} header field contains the priority level of a message,
         * a message with a higher priority value should be delivered preferentially.
         * <p>
         * OMS defines a ten level priority value with 1 as the lowest priority and 10 as the highest,
         * and the default priority is 5. The priority beyond this region will be ignored.
         * <p>
         * OMS does not require or provide any guarantee that the message should be delivered
         * in priority order strictly, but the vendor should provide a best effort to
         * deliver expedited messages ahead of normal messages.
         * <p>
         * If PRIORITY field isn't set explicitly, use {@code 5} as the default priority.
         */
        String PRIORITY = "PRIORITY";

        /**
         * The {@code RELIABILITY} header field contains the reliability level of a message, the vendor
         * should guarantee the reliability level for a message.
         * <p>
         * OMS defines two modes of message delivery:
         * <ul>
         * <li>
         * PERSISTENT, the persistent mode instructs the vendor should provide stable storage to
         * ensure the message won't be lost.
         * </li>
         * <li>
         * NON_PERSISTENT, this mode does not require the message be logged to stable storage,
         * in most cases, the memory storage is enough for better performance and lower cost.
         * </li>
         * </ul>
         */
        String RELIABILITY = "RELIABILITY";

        /**
         * The {@code SEARCH_KEYS} header field contains the multiple search keys of a message.
         * <p>
         * The keyword indexes will be built by the search keys, users can query similar
         * messages through these indexes and have a quick response.
         * <p>
         * This field is a {@code String} value, the different search keys are joined
         * together with a comma delimiter.
         * <p>
         * OMS defines that a message at most has five search keys.
         */
        String SEARCH_KEYS = "SEARCH_KEYS";

        /**
         * The {@code SCHEDULE_EXPRESSION} header field contains schedule expression of a message.
         * <p>
         * The message will be delivered by the specified SCHEDULE_EXPRESSION, which is a CRON expression.
         *
         * @see <a href="https://en.wikipedia.org/wiki/Cron#CRON_expression">https://en.wikipedia.org/wiki/Cron#CRON_expression</a>
         */
        String SCHEDULE_EXPRESSION = "SCHEDULE_EXPRESSION";

        /**
         * The {@code TRACE_ID} header field contains the trace ID of a message, which represents a global and unique
         * identification, to associate key events in the whole lifecycle of a message,
         * like sent by who, stored at where, and received by who.
         * <p></p>
         * And, the messaging system only plays exchange role in a distributed system in most cases,
         * so the TraceID can be used to trace the whole call link with other parts in the whole system.
         */
        String TRACE_ID = "TRACE_ID";

        /**
         * The {@code STREAM_KEY} header field contains the stream key of a message.
         * The messages with same stream key should be dispatched to the same stream of the queue.
         */
        String STREAM_KEY = "STREAM_KEY";

        /**
         * The {@code REDELIVERED_NUMBER} header field contains a number, which represents
         * the number of message delivery.
         */
        String REDELIVERED_NUMBER = "REDELIVERED_NUMBER";

        /**
         * The {@code REDELIVERED_REASON} header field contains the text description of the reason that causes
         * the last message delivery retry.
         */
        String REDELIVERED_REASON = "REDELIVERED_REASON";
    }
}