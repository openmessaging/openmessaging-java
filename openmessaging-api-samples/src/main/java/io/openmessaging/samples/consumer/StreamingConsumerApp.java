package io.openmessaging.samples.consumer;

import io.openmessaging.Message;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.OMS;
import io.openmessaging.ResourceManager;
import io.openmessaging.consumer.StreamIterator;
import io.openmessaging.consumer.Stream;
import io.openmessaging.consumer.StreamingConsumer;

public class StreamingConsumerApp {
    public static void main(String[] args) {
        final MessagingAccessPoint messagingAccessPoint = OMS.getMessagingAccessPoint("oms:rocketmq://localhost:10911/us-east:namespace");
        messagingAccessPoint.startup();
        System.out.println("MessagingAccessPoint startup OK");
        ResourceManager resourceManager = messagingAccessPoint.getResourceManager();

        resourceManager.createQueue("NS1", "HELLO_QUEUE", OMS.newKeyValue());

        final StreamingConsumer streamingConsumer = messagingAccessPoint.createStreamingConsumer("HELLO_QUEUE");

        streamingConsumer.startup();

        Stream stream = streamingConsumer.stream(streamingConsumer.streams().get(0));

        StreamIterator streamIterator = stream.begin();

        while (streamIterator.hasNext()) {
            Message message = streamIterator.next();
            System.out.println("Received one message: " + message);
        }

        //All the messages in the queue has been consumed.

        //Now consume the messages in reverse order
        while (streamIterator.hasPrevious()) {
            Message message = streamIterator.previous();
            System.out.println("Received one message again: " + message);
        }

        //Persist the consume offset.
        streamIterator.commit();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                streamingConsumer.shutdown();
                messagingAccessPoint.shutdown();
            }
        }));

    }
}
