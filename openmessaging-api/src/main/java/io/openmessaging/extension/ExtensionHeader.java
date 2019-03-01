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
package io.openmessaging.extension;

import io.openmessaging.annotation.Optional;
import io.openmessaging.message.Message;

/**
 * <p>
 * The {@code ExtensionHeader} interface contains extended properties for common implementations in current messaging
 * and streaming field, such as the queue-based partitioning implementation, but the related properties in this
 * interface are not mandatory.
 * </p>
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
@Optional
public interface ExtensionHeader {
    /**
     * The {@code PARTITION}  in extension header field contains the partition of target destination which the message
     * is being sent.
     * <p>
     *
     * When a {@link Message} is set with this value, this message will be delivered to specified partition, but the
     * premise is that the implementation of the server side is dependent on the partition or a queue-like storage
     * mechanism.
     * <p>
     *
     * @param partition The specified partition will be sent to.
     */
    ExtensionHeader setPartition(int partition);

    /**
     * This method is only called by the server. and {@code OFFSET} represents this message offset in partition.
     * <p>
     *
     * @param offset The offset in the current partition, used to quickly get this message in the queue
     */
    ExtensionHeader setOffset(long offset);

    /**
     * A client can use the {@code CORRELATION_ID} field to link one message with another. A typical use is to link a
     * response message with its request message.
     */
    ExtensionHeader setCorrelationId(String correlationId);

    /**
     * This field {@code TRANSACTION_ID} is used in transactional message, and it can be used to trace a transaction.
     * <p></p>
     * So the same {@code TRANSACTION_ID} will be appeared not only in prepare message, but also in commit message, and
     * consumer received message also contains this field.
     */
    ExtensionHeader setTransactionId(String transactionId);

    /**
     * The {@code STORE_TIMESTAMP} header field contains the store timestamp of a message in server side.
     * <p>
     * When a message is sent, STORE_TIMESTAMP is ignored.
     * <p>
     * When the send method returns it contains a server-assigned value.
     * <p>
     * This filed is a {@code long} value, measured in milliseconds.
     */
    ExtensionHeader setStoreTimestamp(long storeTimestamp);

    /**
     * The {@code STORE_HOST} header field contains the store host info of a message in server side.
     * <p>
     * When a message is sent, STORE_HOST is ignored.
     * <p>
     * When the send method returns it contains a server-assigned value.
     */
    ExtensionHeader setStoreHost(String storeHost);

    /**
     * The {@code messagekey} header field contains the custom key of a message.
     * <p>
     * This key is a customer identifier for a class of messages, and this key may be used for server to hash or
     * dispatch messages, or even can use this key to implement order message.
     * <p>
     */
    ExtensionHeader setMessageKey(String messageKey);

    /**
     * The {@code TRACE_ID} header field contains the trace ID of a message, which represents a global and unique
     * identification, to associate key events in the whole lifecycle of a message, like sent by who, stored at where,
     * and received by who.
     * <p></p>
     * And, the messaging system only plays exchange role in a distributed system in most cases, so the TraceID can be
     * used to trace the whole call link with other parts in the whole system.
     */
    ExtensionHeader setTraceId(String traceId);

    /**
     * The {@code DELAY_TIME} header field contains a number that represents the delayed times in milliseconds.
     * <p></p>
     * The message will be delivered after delayTime milliseconds starting from {@code BORN_TIMESTAMP} . When this filed
     * isn't set explicitly, this means this message should be delivered immediately.
     */
    ExtensionHeader setDelayTime(long delayTime);

    /**
     * The {@code EXPIRE_TIME} header field contains the expiration time, it represents a time-to-live value.
     * <p>
     * The {@code EXPIRE_TIME} represents a relative valid interval that a message can be delivered in it. If the
     * EXPIRE_TIME field is specified as zero, that indicates the message does not expire.
     * </p>
     * <p>
     * When an undelivered message's expiration time is reached, the message should be destroyed. OMS does not define a
     * notification of message expiration.
     * </p>
     */
    ExtensionHeader setExpireTime(long expireTime);

    /**
     * This method will return the partition of this message belongs.
     * <p>
     *
     * @return The {@code PARTITION} to which the message belongs
     */
    int getPartiton();

    /**
     * This method will return the {@code OFFSET}  in the partition to which the message belongs to, but the premise is
     * that the implementation of the server side is dependent on the partition or a queue-like storage mechanism.
     *
     * @return The offset of the partition to which the message belongs.
     */
    long getOffset();

    /**
     * See {@link ExtensionHeader#setCorrelationId(String)}
     *
     * @return correlationId
     */
    String getCorrelationId();

    /**
     * See {@link ExtensionHeader#setTransactionId(String)}
     *
     * @return transactionId
     */
    String getTransactionId();

    /**
     * See {@link ExtensionHeader#setStoreTimestamp(long)}
     *
     * @return storeTimestamp
     */
    long getStoreTimestamp();

    /**
     * See {@link ExtensionHeader#setStoreHost(String)}
     *
     * @return storeHost
     */
    String getStoreHost();

    /**
     * See {@link ExtensionHeader#setDelayTime(long)}
     *
     * @return delayTime
     */
    long getDelayTime();

    /**
     * See {@link ExtensionHeader#setExpireTime(long)}
     *
     * @return expireTime
     */
    long getExpireTime();

    /**
     * See {@link ExtensionHeader#setMessageKey(String)}
     *
     * @return messageKey
     */
    String getMessageKey();

    /**
     * See {@link ExtensionHeader#setTraceId(String)}
     *
     * @return traceId
     */
    String getTraceId();
}
