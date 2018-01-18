package io.openmessaging.interceptor;

/**
 * A {@code ProducerInterceptor} is used to intercept send operations of producer.
 *
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface ProducerInterceptor {

    /**
     * Called before a message is sent
     *
     * @param context a context
     */
    void sendBefore(SendBeforeContext context);

    /**
     * Called after a message is sent
     *
     * @param context a context
     */
    void sendAfter(SendAfterContext context);

    interface SendBeforeContext {

    }

    interface SendAfterContext {

    }
}
