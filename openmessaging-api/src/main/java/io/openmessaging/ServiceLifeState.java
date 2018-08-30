package io.openmessaging;

/**
 * A collection of all service states.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public enum ServiceLifeState {

    /**
     * Service has been initialized.
     */
    INITIALIZED,

    /**
     * Service in starting.
     */
    STARTING,

    /**
     * Service in running.
     */
    STARTED,

    /**
     * Service has been shutdown.
     */
    STOPED,
}
