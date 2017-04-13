package io.openmessaging.samples.consumer;

import io.openmessaging.Message;
import io.openmessaging.MessageHeader;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.MessagingAccessPointFactory;
import io.openmessaging.PullConsumer;
import io.openmessaging.ResourceManager;
import io.openmessaging.internal.DefaultKeyValue;

public class PullConsumerApp {
    public static void main(String[] args) {
        final MessagingAccessPoint messagingAccessPoint = MessagingAccessPointFactory
            .getMessagingAccessPoint("openmessaging:rocketmq://localhost:10911/namespace");
        messagingAccessPoint.startup();
        System.out.println("MessagingAccessPoint startup OK");
        ResourceManager resourceManager = messagingAccessPoint.createResourceManager();

        resourceManager.createAndUpdateQueue("HELLO_QUEUE", new DefaultKeyValue());
        //PullConsumer only can pull messages from one queue.
        final PullConsumer pullConsumer = messagingAccessPoint.createPullConsumer("HELLO_QUEUE");

        pullConsumer.startup();

        //Poll one message from queue.
        Message message = pullConsumer.poll();

        //Acknowledges the consumed message
        pullConsumer.ack(message.headers().getString(MessageHeader.MESSAGE_ID));

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                messagingAccessPoint.shutdown();
                pullConsumer.shutdown();
            }
        }));
    }
}
