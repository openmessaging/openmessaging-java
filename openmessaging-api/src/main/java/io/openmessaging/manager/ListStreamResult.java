package io.openmessaging.manager;

import io.openmessaging.common.Response;
import java.util.List;

public interface ListStreamResult extends Response {
    List<String> streams();
}
