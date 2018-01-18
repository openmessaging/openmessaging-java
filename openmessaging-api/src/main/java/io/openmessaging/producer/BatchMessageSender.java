package io.openmessaging.producer;

import io.openmessaging.KeyValue;
import io.openmessaging.Message;

/**
 * A message sender created through {@link Producer#createSequenceBatchMessageSender()}, to send
 * messages in batch way, and commit or roll back at the appropriate time.
 *
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface BatchMessageSender {
    /**
     * Submits a message to this sender
     *
     * @param message a message to be sent
     */
    void send(Message message);

    /**
     * Submits a message to this sender, using the specified attributes.
     *
     * @param message a message to be sent
     * @param properties the specified attributes
     */
    void send(Message message, KeyValue properties);

    /**
     * Commits all the uncommitted messages in this sender.
     */
    void commit();

    /**
     * Discards all the uncommitted messages in this sender.
     */
    void rollback();

    /**
     * Close this sender.
     */
    void close();
}
