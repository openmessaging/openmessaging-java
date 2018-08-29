package io.openmessaging.manager;

    import io.openmessaging.common.Result;
    import java.util.List;

/**
 * The result is used for {@link ResourceManager} to get all queues under a namespace.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface ListQueueResult extends Result {
    /**
     * @return all queues get from the server.
     */
    List<String> queues();
}
