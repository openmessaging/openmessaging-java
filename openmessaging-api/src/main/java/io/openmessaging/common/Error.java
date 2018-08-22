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
    ERROR_200(200, "Success"),

    ERROR_401(401, "Client error"),

    ERROR_408(408, "Timeout"),

    ERROR_501(501, "Server error");

    private int errorCode;
    private String errorMessage;

    private Error(int errorCode, String errorMessage) {
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
