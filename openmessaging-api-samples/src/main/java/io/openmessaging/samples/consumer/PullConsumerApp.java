package io.openmessaging.samples.consumer;

import io.openmessaging.Message;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.ResourceManager;
import io.openmessaging.OMS;
import io.openmessaging.consumer.PullConsumer;
import io.openmessaging.exception.OMSResourceNotExistException;

public class PullConsumerApp {
    public static void main(String[] args) throws OMSResourceNotExistException {
        final MessagingAccessPoint messagingAccessPoint =
            OMS.getMessagingAccessPoint("oms:rocketmq://alice@rocketmq.apache.org/us-east:default_space");

        messagingAccessPoint.startup();

        ResourceManager resourceManager = messagingAccessPoint.resourceManager();
        resourceManager.createQueue( "HELLO_QUEUE", OMS.newKeyValue());

        //PullConsumer only can pull messages from one queue.
        final PullConsumer pullConsumer = messagingAccessPoint.createPullConsumer();
        pullConsumer.attachQueue("HELLO_QUEUE");
        pullConsumer.startup();

        //Pull one message from queue.
        Message message = pullConsumer.receive();

        //Acknowledges the consumed message
        pullConsumer.ack(message.sysHeaders().getString(Message.BuiltinKeys.MessageId));

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                pullConsumer.shutdown();
                messagingAccessPoint.shutdown();
            }
        }));
    }
}
