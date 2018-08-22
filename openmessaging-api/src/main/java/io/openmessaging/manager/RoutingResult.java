package io.openmessaging.manager;

import io.openmessaging.common.BaseResult;

public interface RoutingResult extends BaseResult {
    RoutingStrategy routing();
}
