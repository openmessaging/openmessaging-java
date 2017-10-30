package io.openmessaging.samples.consumer;

import io.openmessaging.ResourceManager;
import io.openmessaging.Message;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.MessagingAccessPointFactory;
import io.openmessaging.OMS;
import io.openmessaging.consumer.PullConsumer;

public class PullConsumerApp {
    public static void main(String[] args) {
        final MessagingAccessPoint messagingAccessPoint = MessagingAccessPointFactory
            .getMessagingAccessPoint("oms:rocketmq://localhost:10911/us-east:namespace");
        messagingAccessPoint.startup();
        System.out.println("MessagingAccessPoint startup OK");
        ResourceManager resourceManager = messagingAccessPoint.getResourceManager();

        resourceManager.createQueue("NS1", "HELLO_QUEUE", OMS.newKeyValue());
        //PullConsumer only can pull messages from one queue.
        final PullConsumer pullConsumer = messagingAccessPoint.createPullConsumer("HELLO_QUEUE");

        pullConsumer.startup();

        //Poll one message from queue.
        Message message = pullConsumer.poll();

        //Acknowledges the consumed message
        pullConsumer.ack(message.sysHeaders().getString(Message.BuiltinKeys.MessageId));

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                pullConsumer.shutdown();
                messagingAccessPoint.shutdown();
            }
        }));
    }
}
