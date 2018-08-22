package io.openmessaging.manager;

import io.openmessaging.common.BaseResult;
import java.util.List;

public interface ListRoutingResult extends BaseResult {
    List<RoutingStrategy> routingStrategyList();
}
