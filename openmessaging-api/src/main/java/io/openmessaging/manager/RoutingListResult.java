package io.openmessaging.manager;

import io.openmessaging.common.BaseResult;
import java.util.List;

public interface RoutingListResult extends BaseResult {
    List<RoutingStrategy> routingStrategyList();
}
