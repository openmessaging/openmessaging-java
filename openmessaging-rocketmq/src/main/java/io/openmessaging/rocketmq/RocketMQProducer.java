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

import io.openmessaging.api.Message;
import io.openmessaging.api.MessageId;
import io.openmessaging.api.producer.Producer;
import io.openmessaging.api.producer.ProducerStats;
import io.openmessaging.exception.OMSException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.remoting.exception.RemotingException;

class RocketMQProducer implements Producer {

    private final String topicName;
    private final DefaultMQProducer producer;
    private final RocketMQProducerStats stats;

    RocketMQProducer(DefaultMQProducer producer, String topicName)
            throws MQClientException {
        this.topicName = topicName;
        this.producer = producer;
        this.stats = new RocketMQProducerStats();
    }

    @Override
    public String getTopic() {
        return topicName;
    }

    @Override
    public String getProducerName() {
        return producer.getInstanceName();
    }

    @Override
    public MessageId send(Message message) throws OMSException {
        try {
            return sendAsync(message).get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new OMSException("Interrupted", e);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof OMSException) {
                throw (OMSException) e.getCause();
            } else {
                throw new OMSException("send error", e.getCause());
            }
        }
    }

    @Override
    public CompletableFuture<MessageId> sendAsync(Message message) {
        // TODO: refactor on how message is generated
        org.apache.rocketmq.common.message.Message rocketMsg =
            new org.apache.rocketmq.common.message.Message(
                topicName,
                "",
                message.getData());
        CompletableFuture<MessageId> sendFuture = new CompletableFuture<>();
        try {
            producer.send(rocketMsg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    sendFuture.complete(new RocketMQMsgId(sendResult.getMsgId()));
                }

                @Override
                public void onException(Throwable throwable) {
                    sendFuture.completeExceptionally(throwable);
                }
            });
        } catch (MQClientException | RemotingException e) {
            sendFuture.completeExceptionally(e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            sendFuture.completeExceptionally(e);
        }
        return sendFuture;
    }

    @Override
    public long getLastSequenceId() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProducerStats getStats() {
        return stats;
    }

    @Override
    public void close() {
        // no-op
    }

    @Override
    public CompletableFuture<Void> closeAsync() {
        return CompletableFuture.completedFuture(null);
    }
}
