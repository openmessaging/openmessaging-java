package io.openmessaging.manager;

import io.openmessaging.common.BaseResult;
import java.util.List;

public interface QueueListResult extends BaseResult {
    List<String> queues();
}
