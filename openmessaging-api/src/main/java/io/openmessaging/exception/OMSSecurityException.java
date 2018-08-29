package io.openmessaging.exception;

/**
 * The {@code OMSSecurityException} must be thrown when the client have no enough authority to operate an resource.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public class OMSSecurityException extends OMSRuntimeException {
    /**
     * @see OMSRuntimeException#OMSRuntimeException(int, String)
     */
    public OMSSecurityException(int errorCode, String message) {
        super(errorCode, message);
    }

    /**
     * @see OMSRuntimeException#OMSRuntimeException(int, Throwable)
     */
    public OMSSecurityException(int errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    /**
     * @see OMSRuntimeException#OMSRuntimeException(int, String, Throwable)
     */
    public OMSSecurityException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
