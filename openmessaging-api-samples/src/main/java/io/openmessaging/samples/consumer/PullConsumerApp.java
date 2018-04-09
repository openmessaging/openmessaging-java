package io.openmessaging.samples.consumer;

import io.openmessaging.Message;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.ResourceManager;
import io.openmessaging.OMS;
import io.openmessaging.consumer.PullConsumer;
import io.openmessaging.exception.OMSResourceNotExistException;

public class PullConsumerApp {
    public static void main(String[] args) throws OMSResourceNotExistException {
        //Load and start the vendor implementation from a specific OMS driver URL.
        final MessagingAccessPoint messagingAccessPoint =
            OMS.getMessagingAccessPoint("oms:rocketmq://alice@rocketmq.apache.org/us-east");
        messagingAccessPoint.startup();

        //Fetch a ResourceManager to create Queue resource.
        ResourceManager resourceManager = messagingAccessPoint.resourceManager();
        resourceManager.createQueue( "NS://HELLO_QUEUE", OMS.newKeyValue());

        //Start a PullConsumer to receive messages from the specific queue.
        final PullConsumer pullConsumer = messagingAccessPoint.createPullConsumer();
        pullConsumer.attachQueue("NS://HELLO_QUEUE");
        pullConsumer.startup();

        //Receive one message from queue.
        Message message = pullConsumer.receive();

        //Acknowledge the consumed message
        pullConsumer.ack(message.sysHeaders().getString(Message.BuiltinKeys.MESSAGE_ID));

        //Register a shutdown hook to close the opened endpoints.
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                pullConsumer.shutdown();
                messagingAccessPoint.shutdown();
            }
        }));
    }
}
