package io.openmessaging.interceptor;

/**
 * A {@code ProducerInterceptor} is used to intercept send operations of producer.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface ProducerInterceptor {

    /**
     * Called before a message is sent.
     *
     * @param context the context delivered to the intercept thread
     */
    void sendBefore(SendBeforeContext context);

    /**
     * Called after a message is sent.
     *
     * @param context the context delivered to the intercept thread
     */
    void sendAfter(SendAfterContext context);

    interface SendBeforeContext {
    }

    interface SendAfterContext {
    }
}
