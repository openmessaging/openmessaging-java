package io.openmessaging.manager;

import java.util.List;

/**
 * The result is used for {@link ResourceManager} to get all namespaces under an accountId and an access point.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface ListNameSpaceResult {
    /**
     * @return all namespaces.
     */
    List<String> namespaces();
}
