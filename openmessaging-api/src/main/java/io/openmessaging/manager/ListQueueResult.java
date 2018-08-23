package io.openmessaging.manager;

import io.openmessaging.common.Response;
import java.util.List;

public interface ListQueueResult extends Response {
    List<String> queues();
}
