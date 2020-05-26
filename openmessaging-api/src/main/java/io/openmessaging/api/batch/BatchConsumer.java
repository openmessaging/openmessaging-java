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

import io.openmessaging.api.Admin;
import io.openmessaging.api.ConsumerBase;

/**
 * Batch message consumer, used to subscribe to messages in batch.
 *
 * @version OMS 1.2.0
 * @since OMS 1.2.0
 */
public interface BatchConsumer extends ConsumerBase, Admin {

    /**
     * Subscribe message
     *
     * @param topic
     * @param subExpression Subscribe to the filter expression string, which the broker filters based on this
     * expression. <br> eg: "tag1 || tag2 || tag3"<br>, if subExpression is equal to null or *, it means subscribe all
     * messages.
     * @param listener consume message callback listener.
     */
    void subscribe(final String topic, final String subExpression, final BatchMessageListener listener);

    /**
     * Subscribe message
     *
     * @param topic
     * @param subExpression Subscribe to the filter expression string, which the broker filters based on this
     * expression. <br> eg: "tag1 || tag2 || tag3"<br>, if subExpression is equal to null or *, it means subscribe all
     * messages.
     * @param listener consume message callback listener.
     */
    void subscribe(final String topic, final String subExpression, final GenericBatchMessageListener listener);
}
