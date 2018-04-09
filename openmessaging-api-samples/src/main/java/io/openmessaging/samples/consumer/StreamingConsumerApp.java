package io.openmessaging.samples.consumer;

import io.openmessaging.Message;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.OMS;
import io.openmessaging.ResourceManager;
import io.openmessaging.consumer.StreamingIterator;
import io.openmessaging.consumer.StreamingConsumer;
import io.openmessaging.exception.OMSResourceNotExistException;
import java.util.List;

public class StreamingConsumerApp {
    public static void main(String[] args) throws OMSResourceNotExistException {
        //Load and start the vendor implementation from a specific OMS driver URL.
        final MessagingAccessPoint messagingAccessPoint =
            OMS.getMessagingAccessPoint("oms:rocketmq://alice@rocketmq.apache.org/us-east");
        messagingAccessPoint.startup();

        //Fetch a ResourceManager to create Queue resource.
        ResourceManager resourceManager = messagingAccessPoint.resourceManager();
        String targetQueue = "NS://HELLO_QUEUE";
        resourceManager.createQueue(targetQueue, OMS.newKeyValue());

        //Fetch the streams of the target queue.
        List<String> streams = resourceManager.listStreams(targetQueue);

        //Start a StreamingConsumer to iterate messages from the specific stream.
        final StreamingConsumer streamingConsumer = messagingAccessPoint.createStreamingConsumer();
        streamingConsumer.startup();

        StreamingIterator streamingIterator = streamingConsumer.seekToBeginning(streams.get(0));

        while (streamingIterator.hasNext()) {
            Message message = streamingIterator.next();
            System.out.println("Received one message: " + message);
        }

        //All the messages in the stream has been consumed.
        //Now consume the messages in reverse order
        while (streamingIterator.hasPrevious()) {
            Message message = streamingIterator.previous();
            System.out.println("Received one message again: " + message);
        }

        //Register a shutdown hook to close the opened endpoints.
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                streamingConsumer.shutdown();
                messagingAccessPoint.shutdown();
            }
        }));

    }
}
