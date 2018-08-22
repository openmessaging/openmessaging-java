package io.openmessaging.samples.consumer;

import io.openmessaging.Message;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.OMS;
import io.openmessaging.manager.ResourceManager;
import io.openmessaging.consumer.StreamingIterator;
import io.openmessaging.consumer.StreamingConsumer;
import io.openmessaging.exception.OMSResourceNotExistException;
import io.openmessaging.manager.StreamListResult;
import java.util.List;

public class StreamingConsumerApp {
    public static void main(String[] args) throws OMSResourceNotExistException {
        //Load and start the vendor implementation from a specific OMS driver URL.
        final MessagingAccessPoint messagingAccessPoint =
            OMS.getMessagingAccessPoint("oms:rocketmq://alice@rocketmq.apache.org/us-east");

        //Fetch a ResourceManager to create Queue resource.
        String targetQueue = "NS://HELLO_QUEUE";
        ResourceManager resourceManager = messagingAccessPoint.resourceManager();
        resourceManager.createQueue(targetQueue);

        //Fetch the streams of the target queue.
        StreamListResult streams = resourceManager.listStreams(targetQueue);

        //Start a StreamingConsumer to iterate messages from the specific stream.
        final StreamingConsumer streamingConsumer = messagingAccessPoint.createStreamingConsumer();
        streamingConsumer.startup();

        //Register a shutdown hook to close the opened endpoints.
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                streamingConsumer.shutdown();
            }
        }));

        assert streams.streams().size() != 0;
        StreamingIterator streamingIterator = streamingConsumer.seekToBeginning(streams.streams().get(0));

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
    }
}
