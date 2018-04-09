package io.openmessaging.producer;

import io.openmessaging.Message;
import io.openmessaging.exception.OMSRuntimeException;

/**
 * A message sender created through {@link Producer#createBatchMessageSender()}, to send
 * messages in batch manner, and commit or roll back at the appropriate time.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface BatchMessageSender {
    /**
     * Submits a message to this sender
     *
     * @param message a message to be sent
     * @return this batch sender
     */
    BatchMessageSender send(Message message);

    /**
     * Commits all the uncommitted messages in this sender.
     *
     * @throws OMSRuntimeException if the sender fails to commit the messages due to some internal error.
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