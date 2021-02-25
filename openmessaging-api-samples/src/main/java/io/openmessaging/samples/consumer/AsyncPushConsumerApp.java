package io.openmessaging.samples.consumer;

import io.openmessaging.api.Action;
import io.openmessaging.api.AsyncConsumeContext;
import io.openmessaging.api.AsyncMessageListener;
import io.openmessaging.api.Consumer;
import io.openmessaging.api.Message;
import io.openmessaging.api.MessagingAccessPoint;
import io.openmessaging.api.OMS;
import io.openmessaging.api.OMSBuiltinKeys;

import java.util.Properties;

public class AsyncPushConsumerApp {

    public static void main(String[] args) {
        //Load and start the vendor implementation from a specific OMS driver URL.
        final MessagingAccessPoint messagingAccessPoint =
                OMS.builder()
                        .region("Shanghai")
                        .endpoint("127.0.0.1:9876")
                        .schemaRegistryUrl("http://localhost:1234")
                        .driver("rocketmq")
                        .withCredentials(new Properties())
                        .build();

        Properties properties = new Properties();
        properties.setProperty(OMSBuiltinKeys.DESERIALIZER, "io.openmessaging.openmeta.impl.Deserializer");

        final Consumer consumer = messagingAccessPoint.createConsumer(properties);
        consumer.start();

        //Register a shutdown hook to close the opened endpoints.
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                consumer.shutdown();
            }
        }));

        //Consume messages from a simple queue.
        String topic = "NS://HELLO_TOPIC";

        consumer.subscribe(topic, "*", new AsyncMessageListener() {
            @Override
            public void consume(Message message, final AsyncConsumeContext context) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        context.commit(Action.CommitMessage);
                    }
                });
            }
        });

        consumer.shutdown();
    }

}