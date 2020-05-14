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
package io.openmessaging.api.order;

import io.openmessaging.api.GenericMessage;
import io.openmessaging.api.MessageConsumeContext;

/**
 * Generic order message listener, vendors should promise this listener invoked by order, it maybe means vendors should
 * keep something thread safe and keep concurrent consume closed.
 *
 * @version OMS 2.0.0
 * @since OMS 2.0.0
 */
public interface GenericMessageOrderListener<T> {

    /**
     * When message arrived, this method will be invoked by order.
     *
     * @param message received message
     * @param context
     * @return {@link OrderAction} if this message consumed success, {@link OrderAction#Success} should be returned,
     * otherwise return {@link OrderAction#Suspend}
     */
    OrderAction consume(final GenericMessage<T> message, final MessageConsumeContext context);
}
