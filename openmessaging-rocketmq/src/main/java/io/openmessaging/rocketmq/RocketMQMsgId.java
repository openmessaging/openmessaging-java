/*
 * Copyright 2017 OpenMessaging
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.openmessaging.rocketmq;

import io.openmessaging.api.MessageId;

class RocketMQMsgId implements MessageId {

    private final String msgId;

    RocketMQMsgId(String msgId) {
        this.msgId = msgId;
    }

    @Override
    public int compareTo(MessageId o) {
        // TODO: implement this one
        throw new UnsupportedOperationException();
    }

    @Override
    public int hashCode() {
        return msgId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RocketMQMsgId)) {
            return false;
        }
        return msgId.equals(((RocketMQMsgId) obj).msgId);
    }

    @Override
    public String toString() {
        return msgId;
    }
}
