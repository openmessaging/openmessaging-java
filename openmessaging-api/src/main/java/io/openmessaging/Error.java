package io.openmessaging;

import io.openmessaging.exception.OMSRuntimeException;

/**
 * This class defined error code existed in OpenMessaging:
 * <p>
 * 110x: The request was received, continuing process
 * </p>
 * <p>
 * 120x: The request was successfully received, understood, and accepted
 * </p>
 * <p>
 * 130x: Further action needs to be taken in order to complete the request
 * </p>
 * <p>
 * 140x: The request contains bad syntax or cannot be fulfilled
 * </p>
 * <p>
 * 150x: The server failed to fulfill an apparently valid request
 * </p>
 */
public enum Error {
    ERROR_1101(1101, "Unsupported Version"),

    ERROR_1200(1200, "Success"),

    ERROR_1400(1400, "Bad Request"),

    ERROR_1401(1401, "Unauthorized"),

    ERROR_1402(1402, "Message body Required"),

    ERROR_1403(1403, "Forbidden"),

    ERROR_1404(1404, "Destination Not Found"),

    ERROR_1405(1405, "Namespace Not Fount"),

    ERROR_1406(1406, "Destination Already Exists"),

    ERROR_1407(1407, "Namespace Already Exists"),

    ERROR_1408(1408, "ConsumerId Already Exists"),

    ERROR_1409(1409, "ProducerId Already Exists"),

    ERROR_1410(1410, "Request Timeout"),

    ERROR_1411(1411, "Message Attributes Too Large"),

    ERROR_1412(1412, "Message Header Too Large"),

    ERROR_1413(1413, "Message Body Too Large"),

    ERROR_1414(1414, "No New Message Found"),

    ERROR_1415(1415, "Max Topics Reached"),

    ERROR_1416(1416, "Max Queues Reached"),

    ERROR_1417(1417, "Max Namespaces Reached"),

    ERROR_1418(1418, "Bad Parameter"),

    ERROR_1500(1500, "Server ERROR"),

    ERROR_1501(1501, "Storage Service ERROR"),

    ERROR_1502(1502, "Storage Service Busy"),

    ERROR_1503(1503, "Service Not Available"),

    ERROR_1504(1504, "Flush Disk Timeout"),

    ERROR_10000(10000, "Can't construct a MessagingAccessPoint instance from the given OMS driver URL [%s]."),

    ERROR_10001(10001, "The OMS driver URL [%s] is illegal."),

    ERROR_10002(10002, "The implementation version [%s] is illegal."),

    ERROR_10003(10003, "The implementation version [%s] isn't compatible with the specification version [%s].");

    private int errorCode;

    private String errorMessage;

    private String detailErrorMessage;

    private static final String refBase = "http://openmessaging.cloud/internal/code";

    Error(int errorCode, String errorMessage) {
        this.errorCode = errorCode;

        this.errorMessage = errorMessage;

        this.detailErrorMessage = generateDetailErrorMessage(errorCode, errorMessage);
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getDetailErrorMessage() {
        return detailErrorMessage;
    }

    public static OMSRuntimeException generateException(Error error, String... messageArgs) {
        return new OMSRuntimeException(error.getErrorCode(), String.format(error.getDetailErrorMessage(), (Object[]) messageArgs));
    }

    public static OMSRuntimeException generateException(Error error) {
        return new OMSRuntimeException(error.getErrorCode(), error.getDetailErrorMessage());
    }

    public static OMSRuntimeException generateException(int errorCode, String errorMessage) {
        return new OMSRuntimeException(errorCode, errorMessage);
    }

    public static String generateDetailErrorMessage(int errorCode, String errorMessage) {
        return errorMessage + "\nFor more information, please visit the URL, " + refBase + "#" + errorCode;
    }
}
