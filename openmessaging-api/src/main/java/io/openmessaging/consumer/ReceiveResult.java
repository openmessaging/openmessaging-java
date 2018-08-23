package io.openmessaging.consumer;

import io.openmessaging.Message;
import io.openmessaging.common.Response;

public interface ReceiveResult extends Response {
    /**
     * Received message from bind queue in pull model
     * @return {@link Message} received message
     */
    Message message();
}
