package io.openmessaging.samples.consumer;

import io.openmessaging.ResourceManager;
import io.openmessaging.Message;
import io.openmessaging.consumer.MessageIterator;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.MessagingAccessPointFactory;
import io.openmessaging.OMS;
import io.openmessaging.consumer.Stream;
import io.openmessaging.consumer.StreamingConsumer;

public class StreamingConsumerApp {
    public static void main(String[] args) {
        final MessagingAccessPoint messagingAccessPoint = MessagingAccessPointFactory
            .getMessagingAccessPoint("oms:rocketmq://localhost:10911/us-east:namespace");
        messagingAccessPoint.startup();
        System.out.println("MessagingAccessPoint startup OK");
        ResourceManager resourceManager = messagingAccessPoint.getResourceManager();

        resourceManager.createQueue("NS1", "HELLO_QUEUE", OMS.newKeyValue());

        final StreamingConsumer streamingConsumer = messagingAccessPoint.createStreamingConsumer("HELLO_QUEUE");

        streamingConsumer.startup();

        Stream stream = streamingConsumer.stream(streamingConsumer.streams().get(0));

        MessageIterator messageIterator = stream.begin();

        while (messageIterator.hasNext()) {
            Message message = messageIterator.next();
            System.out.println("Received one message: " + message);
        }

        //All the messages in the queue has been consumed.

        //Now consume the messages in reverse order
        while (messageIterator.hasPrevious()) {
            Message message = messageIterator.previous();
            System.out.println("Received one message again: " + message);
        }

        //Persist the consume offset.
        messageIterator.commit(true);

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                streamingConsumer.shutdown();
                messagingAccessPoint.shutdown();
            }
        }));

    }
}
