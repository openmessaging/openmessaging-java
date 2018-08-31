package io.openmessaging.samples.consumer;

import io.openmessaging.Message;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.OMS;
import io.openmessaging.consumer.Consumer;
import io.openmessaging.manager.ResourceManager;

public class PullConsumerApp {
    public static void main(String[] args) {
        //Load and start the vendor implementation from a specific OMS driver URL.
        final MessagingAccessPoint messagingAccessPoint =
            OMS.getMessagingAccessPoint("oms:rocketmq://alice@rocketmq.apache.org/us-east");

        //Fetch a ResourceManager to create Queue resource.
        ResourceManager resourceManager = messagingAccessPoint.resourceManager();
        resourceManager.createQueue("NS://HELLO_QUEUE");

        //Start a PullConsumer to receive messages from the specific queue.
        final Consumer consumer = messagingAccessPoint.createConsumer();
        consumer.start();

        //Register a shutdown hook to close the opened endpoints.
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                consumer.stop();
            }
        }));
        consumer.bindQueue("NS://HELLO_QUEUE");
        Message message = consumer.receive(1000);
        System.out.println("Received message: " + message);
        //Acknowledge the consumed message
        consumer.ack(message.headers().getMessageId());
    }
}
