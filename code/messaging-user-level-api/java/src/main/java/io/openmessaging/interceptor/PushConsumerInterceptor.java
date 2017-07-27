package io.openmessaging.interceptor;

/**
 * @author vintagewang@apache.org
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface PushConsumerInterceptor {
    interface OnMessageBeforeContext {
    }

    interface OnMessageAfterContext {
    }

    void beforeOnReceived(OnMessageBeforeContext context);

    void afterOnReceived(OnMessageAfterContext context);
}
