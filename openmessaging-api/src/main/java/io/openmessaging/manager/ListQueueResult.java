package io.openmessaging.manager;

import io.openmessaging.common.BaseResult;
import java.util.List;

public interface ListQueueResult extends BaseResult {
    List<String> queues();
}
