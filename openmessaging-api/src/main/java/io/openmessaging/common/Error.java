package io.openmessaging.common;

/**
 * This class defined error code existed in OpenMessaging:
 * <p>
 * 10x: The request was received, continuing process
 * </p>
 * <p>
 * 20x: The request was successfully received, understood, and accepted
 * </p>
 * <p>
 * 30x: Further action needs to be taken in order to complete the request
 * </p>
 * <p>
 * 40x: The request contains bad syntax or cannot be fulfilled
 * </p>
 * <p>
 * 50x: The server failed to fulfill an apparently valid request
 * </p>
 */
public enum Error {
    ERROR_101(101, "Unsupported version"),

    ERROR_200(200, "Success"),

    ERROR_401(401, "Client error"),

    ERROR_402(402, "Namespace not exist"),

    ERROR_403(403, "Queue not exist"),

    ERROR_406(406, "Length exceeds limit"),

    ERROR_407(407, "No permission"),

    ERROR_408(408, "Send message timeout"),

    ERROR_409(409, "Consume message timeout"),

    ERROR_410(410, "Producer status not ready"),

    ERROR_411(411, "ConsumerId repeat"),

    ERROR_412(412, "ProducerId repeat"),

    ERROR_413(413, "Consumer status not ready"),

    ERROR_414(414, "No new message"),

    ERROR_415(415, "Queue is empty"),

    ERROR_501(501, "Server error"),

    ERROR_502(502, "Storage error"),

    ERROR_503(503, "Storage busy"),

    ERROR_504(504, "Service not available"),

    ERROR_505(505, "Flush disk timeout");

    private int errorCode;

    private String errorMessage;

    private String detailErrorMessage;

    Error(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
