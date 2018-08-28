package io.openmessaging.manager;

import io.openmessaging.common.Result;
import java.util.List;

public interface ListQueueResult extends Result {
    List<String> queues();
}
