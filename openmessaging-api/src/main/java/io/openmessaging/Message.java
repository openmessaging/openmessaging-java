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

import io.openmessaging.exception.OMSMessageFormatException;

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
 * As for the message header, OMS defines two kinds types: headers {@link Headers} and properties {@link KeyValue}, with
 * respect to flexibility in vendor implementation and user usage.
 * <ul>
 * <li>
 * System Headers, OMS defines some standard attributes that represent the characteristics of the message.
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
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface Message {

    interface Headers {

        /**
         * The {@code DESTINATION} header field contains the destination to which the message is being sent.
         * <p>
         * When a message is sent this value is set to the right {@code Queue}, then the message will be sent to the
         * specified destination.
         * <p>
         * When a message is received, its destination is equivalent to the {@code Queue} where the message resides in.
         */
        Headers setDestination(String destination);

        /**
         * The {@code MESSAGE_ID} header field contains a value that uniquely identifies each message sent by a {@code
         * Producer}.
         */
        Headers setMessageId(String messageId);

        /**
         * The {@code BORN_TIMESTAMP} header field contains the time a message was handed off to a {@code Producer} to
         * be sent.
         * <p>
         * When a message is sent, BORN_TIMESTAMP will be set with current timestamp as the born timestamp of a message
         * in client side, on return from the send method, the message's BORN_TIMESTAMP header field contains this
         * value.
         * <p>
         * When a message is received its, BORN_TIMESTAMP header field contains this same value.
         * <p>
         * This filed is a {@code long} value, measured in milliseconds.
         */
        Headers setBornTimestamp(long bornTimestamp);

        /**
         * The {@code BORN_HOST} header field contains the born host info of a message in client side.
         * <p>
         * When a message is sent, BORN_HOST will be set with the local host info, on return from the send method, the
         * message's BORN_HOST header field contains this value.
         * <p>
         * When a message is received, its BORN_HOST header field contains this same value.
         */
        Headers setBornHost(String bornHost);

        /**
         * The {@code STORE_TIMESTAMP} header field contains the store timestamp of a message in server side.
         * <p>
         * When a message is sent, STORE_TIMESTAMP is ignored.
         * <p>
         * When the send method returns it contains a server-assigned value.
         * <p>
         * This filed is a {@code long} value, measured in milliseconds.
         */
        Headers setStoreTimestamp(long storeTimestamp);

        /**
         * The {@code STORE_HOST} header field contains the store host info of a message in server side.
         * <p>
         * When a message is sent, STORE_HOST is ignored.
         * <p>
         * When the send method returns it contains a server-assigned value.
         */
        Headers setStoreHost(String storeHost);

        /**
         * The {@code DELAY_TIME} header field contains a number that represents the delayed times in milliseconds.
         * <p></p>
         * The message will be delivered after delayTime milliseconds starting from {@CODE BORN_TIMESTAMP} . When this
         * filed isn't set explicitly, this means this message should be delivered immediately.
         */
        Headers setDelayTime(long delayTime);

        /**
         * The {@code EXPIRE_TIME} header field contains the expiration time, it represents a time-to-live value.
         * <p>
         * The {@code EXPIRE_TIME} represents a relative valid interval that a message can be delivered in it. If the
         * EXPIRE_TIME field is specified as zero, that indicates the message does not expire.
         * </p>
         * <p>
         * When an undelivered message's expiration time is reached, the message should be destroyed. OMS does not
         * define a notification of message expiration.
         * </p>
         */
        Headers setExpireTime(long expireTime);

        /**
         * The {@code PRIORITY} header field contains the priority level of a message, a message with a higher priority
         * value should be delivered preferentially.
         * <p>
         * OMS defines a ten level priority value with 1 as the lowest priority and 10 as the highest, and the default
         * priority is 5. The priority beyond this region will be ignored.
         * <p>
         * OMS does not require or provide any guarantee that the message should be delivered in priority order
         * strictly, but the vendor should provide a best effort to deliver expedited messages ahead of normal
         * messages.
         * <p>
         * If PRIORITY field isn't set explicitly, use {@code 5} as the default priority.
         */
        Headers setPriority(short priority);

        /**
         * The {@code RELIABILITY} header field contains the reliability level of a message, the vendor should guarantee
         * the reliability level for a message.
         * <p>
         * OMS defines two modes of message delivery:
         * <ul>
         * <li>
         * PERSISTENT, the persistent mode instructs the vendor should provide stable storage to ensure the message
         * won't be lost.
         * </li>
         * <li>
         * NON_PERSISTENT, this mode does not require the message be logged to stable storage, in most cases, the memory
         * storage is enough for better performance and lower cost.
         * </li>
         * </ul>
         */
        Headers setDurability(short durability);

        /**
         * The {@code messagekey} header field contains the custom key of a message.
         * <p>
         * This key is a customer identifier for a class of messages, and this key may be used for server to hash or
         * dispatch messages, or even can use this key to implement order message.
         * <p>
         */
        Headers setMessageKey(String messageKey);

        /**
         * The {@code TRACE_ID} header field contains the trace ID of a message, which represents a global and unique
         * identification, to associate key events in the whole lifecycle of a message, like sent by who, stored at
         * where, and received by who.
         * <p></p>
         * And, the messaging system only plays exchange role in a distributed system in most cases, so the TraceID can
         * be used to trace the whole call link with other parts in the whole system.
         */
        Headers setTraceId(String traceId);

        /**
         * The {@code DELIVERY_COUNT} header field contains a number, which represents the count of the message
         * delivery.
         */
        Headers setDeliveryCount(int deliveryCount);

        /**
         * This field {@code TRANSACTION_ID} is used in transactional message, and it can be used to trace a
         * transaction.
         * <p></p>
         * So the same {@code TRANSACTION_ID} will be appeared not only in prepare message, but also in commit message,
         * and consumer received message also contains this field.
         */
        Headers setTransactionId(String transactionId);

        /**
         * A client can use the {@code CORRELATION_ID} field to link one message with another. A typical use is to link
         * a response message with its request message.
         */
        Headers setCorrelationId(String correlationId);

        /**
         * The field {@code COMPRESSION} in headers represents the message body compress algorithm. vendors are free to
         * choose the compression algorithm, but must ensure that the decompressed message is delivered to the user.
         */
        Headers setCompression(short compression);

        /**
         * See {@link Headers#setDestination(String)}
         *
         * @return destination
         */
        String getDestination();

        /**
         * See {@link Headers#setMessageId(String)}
         *
         * @return messageId
         */
        String getMessageId();

        /**
         * See {@link Headers#setBornTimestamp(long)}
         *
         * @return bornTimestamp
         */
        long getBornTimestamp();

        /**
         * See {@link Headers#setBornHost(String)}
         *
         * @return bornHost
         */
        String getBornHost();

        /**
         * See {@link Headers#setStoreTimestamp(long)}
         *
         * @return storeTimestamp
         */
        long getStoreTimestamp();

        /**
         * See {@link Headers#setStoreHost(String)}
         *
         * @return storeHost
         */
        String getStoreHost();

        /**
         * See {@link Headers#setDelayTime(long)}
         *
         * @return delayTime
         */
        long getDelayTime();

        /**
         * See {@link Headers#setExpireTime(long)}
         *
         * @return expireTime
         */
        long getExpireTime();

        /**
         * See {@link Headers#setPriority(short)}
         *
         * @return priority
         */
        short getPriority();

        /**
         * See {@link Headers#setDurability(short)}
         *
         * @return durability
         */
        short getDurability();

        /**
         * See {@link Headers#setMessageKey(String)}
         *
         * @return messageKey
         */
        String getMessageKey();

        /**
         * See {@link Headers#setTraceId(String)}
         *
         * @return traceId
         */
        String getTraceId();

        /**
         * See {@link Headers#setDeliveryCount(int)}
         *
         * @return deliveryCount
         */
        int getDeliveryCount();

        /**
         * See {@link Headers#setTransactionId(String)}
         *
         * @return transactionId
         */
        String getTransactionId();

        /**
         * See {@link Headers#setCorrelationId(String)}
         *
         * @return correlationId
         */
        String getCorrelationId();

        /**
         * See {@link Headers#setCompression(short)}
         *
         * @return compression
         */
        short getCompression();

    }

    /**
     * Returns all the system header fields of the {@code Message} object as a {@code KeyValue}.
     *
     * @return the system headers of a {@code Message}
     */
    Headers headers();

    /**
     * Returns all the customized user header fields of the {@code Message} object as a {@code KeyValue}.
     *
     * @return the user properties of a {@code Message}
     */
    KeyValue properties();

    /**
     * Get data from message body
     *
     * @return message body
     * @throws OMSMessageFormatException if the message body cannot be assigned to the specified type
     */
    byte[] getData();

    /**
     * Set data to message body
     *
     * @param data set message body in binary stream
     */
    void setData(byte[] data);

}