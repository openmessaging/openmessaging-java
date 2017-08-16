package io.openmessaging.interceptor;

import io.openmessaging.KeyValue;
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
    PushConsumer constructPushConsumer(ConstructPushConsumerContext context);

    Producer constructProducer(ConstructProducerContext context);

    PullConsumer constructPullConsumer(ConstructPullConsumerContext context);

    StreamingConsumer constructStreamingConsumer(ConstructStreamingConsumerContext context);

    interface ConstructPullConsumerContext {
        KeyValue properties();
    }

    interface ConstructStreamingConsumerContext {
        KeyValue properties();
    }

    interface ConstructProducerContext {
        KeyValue properties();
    }

    interface ConstructPushConsumerContext {
        KeyValue properties();
    }
}
