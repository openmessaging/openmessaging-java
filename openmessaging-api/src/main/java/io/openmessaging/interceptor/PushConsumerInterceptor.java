package io.openmessaging.interceptor;

/**
 * @author vintagewang@apache.org
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface PushConsumerInterceptor {
    void onMessageBefore(OnMessageBeforeContext context);

    void onMessageAfter(OnMessageAfterContext context);

    interface OnMessageBeforeContext {
    }

    interface OnMessageAfterContext {
    }
}
