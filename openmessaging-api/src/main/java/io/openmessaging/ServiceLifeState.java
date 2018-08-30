package io.openmessaging;

/**
 * A collection of all service states.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public enum ServiceLifeState {

    /**
     * Service just created
     */
    CREATED,

    /**
     * Service in starting
     */
    STARTING,

    /**
     * Service in running
     */
    RUNNING,

    /**
     * Service has been shutdown
     */
    SHUTDOWN,
}
