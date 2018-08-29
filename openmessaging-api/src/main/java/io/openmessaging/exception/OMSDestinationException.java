package io.openmessaging.exception;

/**
 * The {@code OMSDestinationException} must be thrown when the specified destination does not exist or the destination
 * is not readable or writable
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public class OMSDestinationException extends OMSRuntimeException {
    /**
     * @see OMSRuntimeException#OMSRuntimeException(int, String)
     */
    public OMSDestinationException(int errorCode, String message) {
        super(errorCode, message);
    }

    /**
     * @see OMSRuntimeException#OMSRuntimeException(int, Throwable)
     */
    public OMSDestinationException(int errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    /**
     * @see OMSRuntimeException#OMSRuntimeException(int, String, Throwable)
     */
    public OMSDestinationException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
