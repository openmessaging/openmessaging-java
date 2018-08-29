package io.openmessaging.consumer;

import io.openmessaging.Message;
import io.openmessaging.common.Result;

/**
 * The result of consume a OMS message from server.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface ReceiveResult extends Result {
    /**
     * Received message from bind queue in pull model
     *
     * @return {@link Message} received message
     */
    Message message();
}
