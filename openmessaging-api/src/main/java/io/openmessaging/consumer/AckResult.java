package io.openmessaging.consumer;

import io.openmessaging.common.Response;

public interface AckResult extends Response {
    /**
     * The unique receiptHandle related to the {@link Consumer#ack(String)} instance.
     * @return the message id
     */
    String receiptHandle();
}
