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

    ERROR_101(101, "VERSION_NOT_SUPPORTED"),

    ERROR_200(200, "SUCCESS"),

    ERROR_401(401, "CLIENT_ERROR"),

    ERROR_408(408, "TIMEOUT"),

    ERROR_501(501, "SERVER_ERROR"),

    ERROR_502(502, "STORAGE_ERROR"),

    ERROR_503(503, "FLUSH_DISK_TIMEOUT"),

    ERROR_504(504, "SERVICE_NOT_AVAILABLE"),

    ERROR_506(506, "RESOURCE_NOT_EXIST");

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
