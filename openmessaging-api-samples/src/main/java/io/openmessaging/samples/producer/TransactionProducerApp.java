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

import io.openmessaging.Message;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.OMS;
import io.openmessaging.SendResult;
import io.openmessaging.transaction.LocalTransactionChecker;
import io.openmessaging.transaction.LocalTransactionExecutor;
import io.openmessaging.transaction.TransactionProducer;
import io.openmessaging.transaction.TransactionStatus;
import java.util.Properties;

public class TransactionProducerApp {
    public static void main(String[] args) {
        final MessagingAccessPoint messagingAccessPoint =
            OMS.getMessagingAccessPoint("oms:rocketmq://alice@rocketmq.apache.org/us-east");

        final TransactionProducer producer = messagingAccessPoint.createTransactionProducer(new Properties(), new LocalTransactionChecker() {
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

        Message message = new Message("NS://Topic", "TagA", "Hello MQ".getBytes());

        //Sends a transaction message to the specified destination synchronously.
        SendResult result = producer.send(message, new LocalTransactionExecutor() {
            @Override public TransactionStatus execute(Message message, Object arg) {
                return TransactionStatus.CommitTransaction;
            }
        }, null);
        System.out.println("Send transaction message OK, message id is: " + result.getMessageId());
    }

}
