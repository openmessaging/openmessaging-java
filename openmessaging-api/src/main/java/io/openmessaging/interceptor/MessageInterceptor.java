package io.openmessaging.interceptor;

import io.openmessaging.Message;

/**
 * A {@code Interceptor} is used to intercept send or consume operations.
 * <p>
 * The interceptor is able to view or modify the message being transmitted and collect
 * the send record.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface MessageInterceptor {
    /**
     * Invoked during send or consume operations.
     *
     * @param message the message is actually processing.
     * @param attributes the extensible attributes delivered to the intercept thread.
     */
    void onMessage(Message message, Context attributes);
}
