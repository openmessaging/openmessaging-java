package io.openmessaging.manager;

import io.openmessaging.common.BaseResult;
import java.util.List;

public interface StreamListResult extends BaseResult {
    List<String> streams();
}
