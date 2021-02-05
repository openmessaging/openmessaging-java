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
package io.openmessaging.samples.producer;

import java.util.Properties;

import io.openmessaging.api.GenericMessage;
import io.openmessaging.api.Message;
import io.openmessaging.api.MessagingAccessPoint;
import io.openmessaging.api.OMS;
import io.openmessaging.api.SendResult;
import io.openmessaging.api.transaction.GenericLocalTransactionChecker;
import io.openmessaging.api.transaction.GenericLocalTransactionExecuter;
import io.openmessaging.api.transaction.LocalTransactionChecker;
import io.openmessaging.api.transaction.LocalTransactionExecuter;
import io.openmessaging.api.transaction.TransactionProducer;
import io.openmessaging.api.transaction.TransactionStatus;
import io.openmessaging.samples.MessageSample;

public class GenericTransactionProducerApp {

    public static void main(String[] args) {
        final MessagingAccessPoint messagingAccessPoint =
                OMS.builder()
                        .region("Shenzhen")
                        .endpoint("127.0.0.1:9876")
                        .schemaRegistryUrl("http://localhost:1234")
                        .driver("rocketmq")
                        .withCredentials(new Properties())
                        .build();

        final TransactionProducer producer = messagingAccessPoint.createTransactionProducer(new Properties());

        // register local transaction checker for NS://TopicA, message's payload in NS://TopicA must be
        // object instead of bytes
        producer.registerGenericLocalTransactionChecker("NS://topicA",
                new GenericLocalTransactionChecker<MessageSample>(){
            @Override
            public TransactionStatus check(GenericMessage<MessageSample> msg) {
                MessageSample sample = msg.getValue();
                System.out.println("receive message from transaction check, payload is " + sample);
                return TransactionStatus.CommitTransaction;
            }

            @Override
            public Class<MessageSample> payloadClass() {
                return MessageSample.class;
            }
        });

        // register local transaction checker for NS://TopicB
        producer.registerLocalTransactionChecker("NS://topicB", new LocalTransactionChecker() {
            @Override
            public TransactionStatus check(Message msg) {
                return TransactionStatus.CommitTransaction;
            }
        });

        producer.start();

        //Register a shutdown hook to close the opened endpoints.
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                producer.shutdown();
            }
        }));

        MessageSample messageSample = new MessageSample("Bob");

        Message genericMessage = producer.messageBuilder().withTopic("NS://topicA")
                .withValue(messageSample).withKey("messageKey").withTags("TagA").withSerializationType("json").build();

        SendResult sendResult = producer.send(genericMessage, new GenericLocalTransactionExecuter<MessageSample>() {
            @Override
            public TransactionStatus execute(GenericMessage<MessageSample> message, Object arg) {
                MessageSample sample = message.getValue();
                System.out.println("message payload is " + sample);
                return TransactionStatus.CommitTransaction;
            }

            @Override
            public Class<MessageSample> payloadClass() {
                return MessageSample.class;
            }
        }, null);
        System.out.println("Send transaction message OK, message id is: " + sendResult.getMessageId());


        Message message = new Message("NS://TopicB", "TagA", "Hello MQ".getBytes());
        //Sends a transaction message to the specified destination synchronously.
        SendResult result = producer.send(message, new LocalTransactionExecuter() {
            @Override public TransactionStatus execute(Message message, Object arg) {
                return TransactionStatus.CommitTransaction;
            }
        }, null);
        System.out.println("Send transaction message OK, message id is: " + result.getMessageId());
    }

}
