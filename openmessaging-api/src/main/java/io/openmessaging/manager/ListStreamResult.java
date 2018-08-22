package io.openmessaging.manager;

import io.openmessaging.common.BaseResult;
import java.util.List;

public interface ListStreamResult extends BaseResult {
    List<String> streams();
}
