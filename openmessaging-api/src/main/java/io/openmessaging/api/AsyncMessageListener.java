package io.openmessaging.api;

/**
 * Async message listener, registered for consume messages by consumer
 */
public interface AsyncMessageListener {

    /**
     * Asynchronously consumer message interface which allow async commit consumption status, implemented by the application,
     * unstable situations such as network jitter may lead to message duplication, and services sensitive to repeated messages
     * need to guarantee idempotent.
     * @param message
     * @param context
     */
    void consume(final Message message, final AsyncConsumeContext context);

}