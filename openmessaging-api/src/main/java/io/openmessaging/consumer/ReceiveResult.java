package io.openmessaging.consumer;

import io.openmessaging.Message;
import io.openmessaging.common.BaseResult;

public interface ReceiveResult extends BaseResult {
    /**
     * Received message from bind queue in pull model
     * @return {@link Message} received message
     */
    Message message();
}
