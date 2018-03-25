package io.openmessaging.interceptor;

/**
 * A {@code PushConsumerInterceptor} is used to intercept consume operations of push consumer.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface PushConsumerInterceptor {
    /**
     * Called before a message is consumed.
     *
     * @param context the context delivered to the intercept thread
     */
    void onReceivedBefore(OnMessageBeforeContext context);

    /**
     * Called after a message is consumed.
     *
     * @param context the context delivered to the intercept thread
     */
    void onReceivedAfter(OnMessageAfterContext context);

    interface OnMessageBeforeContext {
    }

    interface OnMessageAfterContext {
    }
}
