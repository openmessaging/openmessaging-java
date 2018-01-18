package io.openmessaging.interceptor;

/**
 * A {@code PushConsumerInterceptor} is used to intercept consume operations of push consumer.
 *
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface PushConsumerInterceptor {
    /**
     * Called before a message is consumed
     *
     * @param context a context
     */
    void onMessageBefore(OnMessageBeforeContext context);

    /**
     * Called after a message is consumed
     *
     * @param context a context
     */
    void onMessageAfter(OnMessageAfterContext context);

    interface OnMessageBeforeContext {
    }

    interface OnMessageAfterContext {
    }
}
