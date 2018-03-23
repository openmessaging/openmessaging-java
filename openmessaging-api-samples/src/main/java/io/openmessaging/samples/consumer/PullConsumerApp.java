package io.openmessaging.samples.consumer;

import io.openmessaging.Message;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.ResourceManager;
import io.openmessaging.OMS;
import io.openmessaging.consumer.PullConsumer;

public class PullConsumerApp {
    public static void main(String[] args) {
        final MessagingAccessPoint messagingAccessPoint = OMS.getMessagingAccessPoint("oms:rocketmq://localhost:10911/us-east:resourceManager");
        messagingAccessPoint.startup();
        System.out.println("MessagingAccessPoint startup OK");
        ResourceManager resourceManager = messagingAccessPoint.getResourceManager();

        resourceManager.createQueue("NS1", "HELLO_QUEUE", OMS.newKeyValue());
        //PullConsumer only can pull messages from one queue.
        final PullConsumer pullConsumer = messagingAccessPoint.createPullConsumer("HELLO_QUEUE");

        pullConsumer.startup();

        //Pull one message from queue.
        Message message = pullConsumer.pull();

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
