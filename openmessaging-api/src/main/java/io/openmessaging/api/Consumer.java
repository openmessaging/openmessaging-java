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
 * Consumer interface.
 *
 * @version OMS 1.2.0
 * @since OMS 1.2.0
 */
public interface Consumer extends ConsumerBase, Admin {

    /**
     * Subscribe message in order.
     *
     * @param topic message topic.
     * @param subExpression Subscribe to the filter expression string, which the broker filters based on this
     * expression. <br> eg: "tag1 || tag2 || tag3"<br>, if subExpression is equal to null or *, it means subscribe all
     * messages.
     * @param listener The message callback listener, the consumer receives the message and then passes it to the
     * message callback listener for consumption.
     */
    void subscribe(final String topic, final String subExpression, final MessageListener listener);

    /**
     * Subscribe to messages, which can be filtered using SQL expressions.
     *
     * @param topic message topic
     * @param selector Subscribe to the message selector (can be empty, indicating no filtering), the ONS server filters
     * according to the expression in this selector. Currently supports two expression syntax: {@link
     * ExpressionType#TAG}, {@link ExpressionType#SQL92} Among them, the effect of TAG filtering is consistent with the
     * above interface.
     * @param listener Message callback listener
     */
    void subscribe(final String topic, final MessageSelector selector, final MessageListener listener);

    /**
     * Subscribe message in order.
     *
     * @param topic message topic.
     * @param subExpression Subscribe to the filter expression string, which the broker filters based on this
     * expression. <br> eg: "tag1 || tag2 || tag3"<br>, if subExpression is equal to null or *, it means subscribe all
     * messages.
     * @param listener The message callback listener, the consumer receives the message and then passes it to the
     * message callback listener for consumption.
     */
    <T> void subscribe(final String topic, final String subExpression, final GenericMessageListener<T> listener);

    /**
     * Subscribe to messages, which can be filtered using SQL expressions.
     *
     * @param topic message topic
     * @param selector Subscribe to the message selector (can be empty, indicating no filtering), the ONS server filters
     * according to the expression in this selector. Currently supports two expression syntax: {@link
     * ExpressionType#TAG}, {@link ExpressionType#SQL92} Among them, the effect of TAG filtering is consistent with the
     * above interface.
     * @param listener Message callback listener
     */
    <T> void subscribe(final String topic, final MessageSelector selector, final GenericMessageListener<T> listener);

    /**
     * Subscribe to messages with async message listener.
     * @param topic
     * @param subExpression
     * @param listener
     */
    void subscribe(final String topic, final String subExpression, final AsyncMessageListener listener);

    /**
     * Subscribe to messages with async message listener, which can be filtered using SQL expressions.
     * @param topic
     * @param selector
     * @param listener
     */
    void subscribe(final String topic, final MessageSelector selector, final AsyncMessageListener listener);

    /**
     * Subscribe to messages with async message listener.
     * @param topic
     * @param subExpression
     * @param listener
     * @param <T>
     */
    <T> void subscribe(final String topic, final String subExpression, final AsyncGenericMessageListener<T> listener);

    /**
     * Subscribe to messages with async message listener, which can be filtered using SQL expressions.
     * @param topic
     * @param selector
     * @param listener
     * @param <T>
     */
    <T> void subscribe(final String topic, final MessageSelector selector, final AsyncGenericMessageListener<T> listener);
}
