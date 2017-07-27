package io.openmessaging.samples.consumer;

import io.openmessaging.Stream;
import io.openmessaging.Message;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.MessagingAccessPointFactory;
import io.openmessaging.OMS;
import io.openmessaging.CloudResourceManager;
import io.openmessaging.StreamingConsumer;

public class StreamingConsumerApp {
    public static void main(String[] args) {
        final MessagingAccessPoint messagingAccessPoint = MessagingAccessPointFactory
            .getMessagingAccessPoint("openmessaging:rocketmq://localhost:10911/namespace");
        messagingAccessPoint.startup();
        System.out.println("MessagingAccessPoint startup OK");
        CloudResourceManager resourceManager = messagingAccessPoint.getResourceManager();

        resourceManager.createAndUpdateQueue("HELLO_QUEUE", OMS.newKeyValue());

        final StreamingConsumer streamingConsumer = messagingAccessPoint.createStreamingConsumer("HELLO_QUEUE");

        streamingConsumer.startup();

        Stream partitionIterator = streamingConsumer.stream(streamingConsumer.streams().get(0));

        while (partitionIterator.hasNext()) {
            Message message = partitionIterator.next();
            System.out.println("Received one message: " + message);
        }

        //All the messages in the queue has been consumed.

        //Now consume the messages in reverse order
        while (partitionIterator.hasPrevious()) {
            Message message = partitionIterator.previous();
            System.out.println("Received one message again: " + message);
        }

        //Persist the consume offset.
        partitionIterator.persist();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                streamingConsumer.shutdown();
                messagingAccessPoint.shutdown();
            }
        }));

    }
}
