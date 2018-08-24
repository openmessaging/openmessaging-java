package io.openmessaging.manager;

import io.openmessaging.Message;

public interface QueueConfig {
    /**
     * Make this queue to be a fifo queue, vendors can use the field {@link Message.Headers#getMessageKey()} for
     * sharding and dispatch message
     *
     * @param isFifo if this
     */
    void setFifo(boolean isFifo);

    /**
     * Check if it is an ordered queue.
     *
     * @return if this queue is ordered fifo queue, this method will return true, otherwise false.
     */
    boolean isFifo();
}
