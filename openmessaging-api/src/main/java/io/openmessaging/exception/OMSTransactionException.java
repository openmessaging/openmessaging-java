package io.openmessaging.exception;

/**
 * The {@code OMSTransactionException} must be thrown when the client execute a transaction error.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public class OMSTransactionException extends OMSRuntimeException {
    /**
     * @see OMSRuntimeException#OMSRuntimeException(int, String)
     */
    public OMSTransactionException(int errorCode, String message) {
        super(errorCode, message);
    }

    /**
     * @see OMSRuntimeException#OMSRuntimeException(int, Throwable)
     */
    public OMSTransactionException(int errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    /**
     * @see OMSRuntimeException#OMSRuntimeException(int, String, Throwable)
     */
    public OMSTransactionException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
