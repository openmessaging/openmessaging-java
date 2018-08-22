package io.openmessaging.samples.consumer;

import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.OMS;
import io.openmessaging.common.BaseResult;
import io.openmessaging.consumer.BindQueueResult;
import io.openmessaging.manager.QueueConfig;
import io.openmessaging.manager.ResourceManager;
import io.openmessaging.consumer.Consumer;
import io.openmessaging.consumer.ReceiveResult;
import io.openmessaging.exception.OMSResourceNotExistException;

public class PullConsumerApp {
    public static void main(String[] args) {
        //Load and start the vendor implementation from a specific OMS driver URL.
        final MessagingAccessPoint messagingAccessPoint =
            OMS.getMessagingAccessPoint("oms:rocketmq://alice@rocketmq.apache.org/us-east");

        //Fetch a ResourceManager to create Queue resource.
        ResourceManager resourceManager = messagingAccessPoint.resourceManager();
        QueueConfig config = new QueueConfig();
        config.setQueueName("NS://HELLO_QUEUE");
        config.setQueuePermission("WR");
        config.setReadNum(4);
        config.setWriteNum(4);
        BaseResult createQueueResult = resourceManager.createQueue("NS://HELLO_QUEUE", config);
        if (createQueueResult.isSuccess()) {
            //Start a PullConsumer to receive messages from the specific queue.
            final Consumer consumer = messagingAccessPoint.createConsumer();
            consumer.startup();

            //Register a shutdown hook to close the opened endpoints.
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    consumer.shutdown();
                }
            }));

            BindQueueResult bindQueueResult = consumer.bindQueue("NS://HELLO_QUEUE");
            if (bindQueueResult.isSuccess()) {
                ReceiveResult receiveResult = consumer.receive(1000);
                if (receiveResult.isSuccess()) {
                    System.out.println("Received message: " + receiveResult.message());
                    //Acknowledge the consumed message
                    consumer.ack(receiveResult.message().headers().getMessageId());
                }
            } else {
                System.out.println("Error: " + bindQueueResult.getError().getErrorCode() + " error message: " + bindQueueResult.getError().getErrorMessage());
            }
        } else {
            System.out.println("Error: " + createQueueResult.getError().getErrorCode() + " error message: " + createQueueResult.getError().getErrorMessage());
        }
    }
}
