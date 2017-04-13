package io.openmessaging.samples.consumer;

import io.openmessaging.IterableConsumer;
import io.openmessaging.Message;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.MessagingAccessPointFactory;
import io.openmessaging.ResourceManager;

public class IterableConsumerApp {
    public static void main(String[] args) {
        final MessagingAccessPoint messagingAccessPoint = MessagingAccessPointFactory
            .getMessagingAccessPoint("openmessaging:rocketmq://localhost:10911/namespace");
        messagingAccessPoint.startup();
        System.out.println("MessagingAccessPoint startup OK");
        ResourceManager resourceManager = messagingAccessPoint.getResourceManager();

        resourceManager.createAndUpdateQueue("HELLO_QUEUE", MessagingAccessPointFactory.newKeyValue());

        final IterableConsumer iterableConsumer = messagingAccessPoint.createIterableConsumer("HELLO_QUEUE");

        iterableConsumer.startup();

        while (iterableConsumer.hasNext()) {
            Message message = iterableConsumer.next();
            System.out.println("Received one message: " + message);
        }

        //All the messages in the queue has been consumed.

        //Now consume the messages in reverse order
        while (iterableConsumer.hasPrevious()) {
            Message message = iterableConsumer.previous();
            System.out.println("Received one message again: " + message);
        }

        //Persist the consume offset.
        iterableConsumer.persist();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                iterableConsumer.shutdown();
                messagingAccessPoint.shutdown();
            }
        }));

    }
}
