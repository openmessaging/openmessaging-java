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
import io.openmessaging.producer.LocalTransactionExecutor;
import io.openmessaging.producer.Producer;
import io.openmessaging.producer.SendResult;
import java.nio.charset.Charset;

public class TransactionProducerApp {
    public static void main(String[] args) {
        final MessagingAccessPoint messagingAccessPoint =
            OMS.getMessagingAccessPoint("oms:rocketmq://alice@rocketmq.apache.org/us-east");

        final Producer producer = messagingAccessPoint.createProducer();
        messagingAccessPoint.startup();
        producer.startup();

        //Register a shutdown hook to close the opened endpoints.
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                producer.shutdown();
                messagingAccessPoint.shutdown();
            }
        }));

        Message message = producer.createBytesMessage(
            "NS://HELLO_QUEUE", "HELLO_BODY".getBytes(Charset.forName("UTF-8")));

        //Sends a transaction message to the specified destination synchronously.
        SendResult sendResult = producer.send(message, new LocalTransactionExecutor() {
            @Override
            public void execute(final Message message, final ExecutionContext context) {
                //Do some local transaction
                //Then commit this transaction and the message will be delivered.
                context.commit();
            }

            @Override
            public void check(final Message message, final CheckContext context) {
                //The server may lookup the transaction status forwardly associated the specified message
                context.commit();
            }
        }, OMS.newKeyValue());

        System.out.println("Send transaction message OK, message id is: " + sendResult.messageId());
    }
}
