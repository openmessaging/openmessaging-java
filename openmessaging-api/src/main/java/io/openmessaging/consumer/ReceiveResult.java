package io.openmessaging.consumer;

import io.openmessaging.Message;
import io.openmessaging.common.Result;

public interface ReceiveResult extends Result {
    /**
     * Received message from bind queue in pull model
     * @return {@link Message} received message
     */
    Message message();
}
