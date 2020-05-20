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
package io.openmessaging.api.batch;

import io.openmessaging.api.Action;
import io.openmessaging.api.GenericMessage;
import io.openmessaging.api.MessageConsumeContext;
import java.util.List;

/**
 * Batch generic message listener.
 *
 * @version OMS 2.0.0
 * @since OMS 2.0.0
 */
public interface GenericBatchMessageListener<T> {
    /**
     * When message arrived, this method will be invoked by order.
     *
     * @param messages received message
     * @param context
     * @return {@link Action} if this message consumed success, {@link Action#CommitMessage} should be returned,
     * otherwise return {@link Action#ReconsumeLater}
     */
    Action consume(final List<GenericMessage<T>> messages, final MessageConsumeContext context);
}
