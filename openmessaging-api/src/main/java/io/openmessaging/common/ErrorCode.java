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
public class ErrorCode {
    /**
     * Protocol need to upgrade
     */
    public static final int PROTOCOL_UPGRAGE = 101;

    /**
     * success
     */
    public static final int SUCCESS = 200;

    /**
     * Source not existed or haven't created before
     */
    public static final int NAMESPACE_NOT_EXIST = 301;

    /**
     * Source not existed or haven't created before
     */
    public static final int ROUTING_NOT_EXIST = 302;

    /**
     * Client error occurred
     */
    public static final int CLIENT_ERROR = 401;

    /**
     * Blocking operations for which a timeout is specified need a means to indicate that the timeout has occurred. And
     * this error code must be return when a blocking operation times out.
     */
    public static final int REQUEST_TIMEOUT = 408;

    /**
     * This error code must be return when the provided message is not supported or the attributes are the wrong type.
     */
    public static final int UNSUPPORT_FORMAT = 415;

    /**
     * Server error occurred
     */
    public static final int SERVER_ERROR = 501;

}
