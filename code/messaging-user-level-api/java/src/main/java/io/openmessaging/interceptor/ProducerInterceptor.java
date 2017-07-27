package io.openmessaging.interceptor;

/**
 * @author vintagewang@apache.org
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface ProducerInterceptor {
    interface SendBeforeContext {

    }

    interface SendAfterContext {

    }

    void sendBefore(SendBeforeContext context);

    void sendAfter(SendAfterContext context);
}
