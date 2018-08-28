package io.openmessaging.consumer;

import io.openmessaging.common.Result;

public interface AckResult extends Result {
    /**
     * The unique receiptHandle related to the {@link Consumer#ack(String)} instance.
     * @return the message id
     */
    String receiptHandle();
}
