package io.openmessaging;

public enum ServiceLifeState {

    /**
     * Service just created
     */
    CREATE,

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
