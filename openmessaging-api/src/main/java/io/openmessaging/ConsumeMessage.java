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

import io.openmessaging.consumer.BatchMessageListener;
import io.openmessaging.consumer.Consumer;
import io.openmessaging.consumer.MessageListener;
import io.openmessaging.consumer.MessageReceipt;

/**
 *
 * A {@code ConsumeMessage} is a {@code Message} with a {@code MessageReceipt}.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface ConsumeMessage extends Message {
    /**
     *  Get the {@code MessageReceipt} of this Message, which will be used to acknowledge this message.
     *
     *  @see Consumer#ack(io.openmessaging.consumer.MessageReceipt)
     *  @see MessageListener.Context#ack()
     *  @see BatchMessageListener.Context#success(io.openmessaging.consumer.MessageReceipt...)
     */
    MessageReceipt getMessageRecept();
}
