package io.openmessaging.manager;

import io.openmessaging.common.Result;
import java.util.List;

public interface ListStreamResult extends Result {
    List<String> streams();
}
