package io.openmessaging.interceptor;

import io.openmessaging.KeyValue;
import io.openmessaging.Producer;
import io.openmessaging.PushConsumer;

/**
 * @author vintagewang@apache.org
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface ObjectInterceptor {
    PushConsumer constructPushConsumer(ConstructPushConsumerContext context);

    Producer constructProducer(ConstructProducerContext context);

    interface ConstructProducerContext {
        KeyValue properties();
    }

    interface ConstructPushConsumerContext {
        KeyValue properties();
    }
}
