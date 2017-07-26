package io.openmessaging.interceptor;

/**
 * @author vintagewang@apache.org
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface SendMessageInterceptor {
    void sendMessageBefore(SendMessageBeforeContext context);

    void sendMessageAfter(SendMessageAfterContext context);
}
