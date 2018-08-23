package io.openmessaging.consumer;

import io.openmessaging.common.Response;

public interface BindResult extends Response {
    String queueName();
}
