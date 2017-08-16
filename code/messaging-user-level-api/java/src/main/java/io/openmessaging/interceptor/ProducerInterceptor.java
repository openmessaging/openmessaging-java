package io.openmessaging.interceptor;

import io.openmessaging.KeyValue;
import io.openmessaging.Producer;

/**
 * @author vintagewang@apache.org
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface ProducerInterceptor {
    Producer constructProducer(KeyValue properties);

    void sendBefore(SendBeforeContext context);

    void sendAfter(SendAfterContext context);

    interface SendBeforeContext {

    }

    interface SendAfterContext {

    }
}
