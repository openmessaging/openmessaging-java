package io.openmessaging.interceptor;

import io.openmessaging.KeyValue;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.Producer;
import io.openmessaging.PullConsumer;
import io.openmessaging.PushConsumer;
import io.openmessaging.StreamingConsumer;

/**
 * @author vintagewang@apache.org
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface ObjectInterceptor {
    MessagingAccessPoint constructMessagingAccessPoint(ConstructMessagingAccessPointContext context);

    PushConsumer constructPushConsumer(ConstructPushConsumerContext context);

    Producer constructProducer(ConstructProducerContext context);

    PullConsumer constructPullConsumer(ConstructPullConsumerContext context);

    StreamingConsumer constructStreamingConsumer(ConstructStreamingConsumerContext context);

    interface ConstructMessagingAccessPointContext {
        MessagingAccessPoint messagingAccessPoint();

        KeyValue properties();
    }

    interface ConstructPullConsumerContext {
        MessagingAccessPoint messagingAccessPoint();

        KeyValue properties();

        PullConsumer pullConsumer();
    }

    interface ConstructStreamingConsumerContext {
        MessagingAccessPoint messagingAccessPoint();

        KeyValue properties();

        StreamingConsumer streamingConsumer();
    }

    interface ConstructProducerContext {
        MessagingAccessPoint messagingAccessPoint();

        KeyValue properties();

        Producer producer();
    }

    interface ConstructPushConsumerContext {
        MessagingAccessPoint messagingAccessPoint();

        KeyValue properties();

        PushConsumer pushConsumer();
    }
}
