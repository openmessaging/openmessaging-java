package io.openmessaging.consumer;

import io.openmessaging.common.Result;

public interface BindResult extends Result {
    String queueName();
}
