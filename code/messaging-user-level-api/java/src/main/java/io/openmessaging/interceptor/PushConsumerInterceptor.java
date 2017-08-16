package io.openmessaging.interceptor;

import io.openmessaging.KeyValue;
import io.openmessaging.PushConsumer;

/**
 * @author vintagewang@apache.org
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface PushConsumerInterceptor {
    PushConsumer constructPushConsumer(KeyValue properties);

    void onMessageBefore(OnMessageBeforeContext context);

    void onMessageAfter(OnMessageAfterContext context);

    interface OnMessageBeforeContext {
    }

    interface OnMessageAfterContext {
    }
}
