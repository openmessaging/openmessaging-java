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

package io.openmessaging.message;

import io.openmessaging.KeyValue;
import io.openmessaging.annotation.Optional;
import io.openmessaging.consumer.BatchMessageListener;
import io.openmessaging.consumer.Consumer;
import io.openmessaging.consumer.MessageListener;
import io.openmessaging.consumer.MessageReceipt;
import io.openmessaging.exception.OMSMessageFormatException;
import io.openmessaging.extension.ExtensionHeader;

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
 * As for the message header, OMS defines three kinds types: headers {@link Header} {@link ExtensionHeader} and
 * properties {@link KeyValue}, with respect to flexibility in vendor implementation and user usage.
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
    /**
     * Returns all the system header fields of the {@code Message} object as a {@code KeyValue}.
     *
     * @return the system headers of a {@code Message}
     */
    Header header();

    /**
     * This interface is optional, Therefore, users need to check whether the interface is implemented and the
     * correctness of its implementation.
     * <p>
     *
     * @return The implementation of {@link ExtensionHeader}
     */
    @Optional
    ExtensionHeader extensionHeader();

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

    /**
     * Get the {@code MessageReceipt} of this Message, which will be used to acknowledge this message.
     *
     * @see Consumer#ack(io.openmessaging.consumer.MessageReceipt)
     * @see MessageListener.Context#ack()
     * @see BatchMessageListener.Context#success(io.openmessaging.consumer.MessageReceipt...)
     */
    MessageReceipt getMessageReceipt();

}