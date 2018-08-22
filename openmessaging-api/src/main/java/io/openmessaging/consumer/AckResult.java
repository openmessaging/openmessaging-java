package io.openmessaging.consumer;

import io.openmessaging.common.BaseResult;

public interface AckResult extends BaseResult {
    /**
     * The unique receiptHandle related to the {@link Consumer#ack(String)} instance.
     * @return the message id
     */
    String receiptHandle();
}
