package io.openmessaging;

import io.openmessaging.exception.OMSRuntimeException;

/**
 * This class defined OpenMessaging response status code:
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
 * <p>
 * 1000x: OpenMessaging internal status code for create {@link MessagingAccessPoint}
 * </p>
 */
public enum OMSResponseStatus {
    STATUS_1101(1101, "Unsupported Version"),

    STATUS_1200(1200, "Success"),

    STATUS_1400(1400, "Bad Request"),

    STATUS_1401(1401, "Unauthorized"),

    STATUS_1402(1402, "Message body Required"),

    STATUS_1403(1403, "Forbidden"),

    STATUS_1404(1404, "Destination Not Found"),

    STATUS_1405(1405, "Namespace Not Found"),

    STATUS_1406(1406, "Destination Already Exists"),

    STATUS_1407(1407, "Namespace Already Exists"),

    STATUS_1408(1408, "ConsumerId Already Exists"),

    STATUS_1409(1409, "ProducerId Already Exists"),

    STATUS_1410(1410, "Request Timeout"),

    STATUS_1411(1411, "Message Attributes Too Large"),

    STATUS_1412(1412, "Message Header Too Large"),

    STATUS_1413(1413, "Message Body Too Large"),

    STATUS_1414(1414, "No New Message Found"),

    STATUS_1415(1415, "Max Topics Reached"),

    STATUS_1416(1416, "Max Queues Reached"),

    STATUS_1417(1417, "Max Namespaces Reached"),

    STATUS_1418(1418, "Bad Parameter"),

    STATUS_1500(1500, "Server STATUS"),

    STATUS_1501(1501, "Storage Service STATUS"),

    STATUS_1502(1502, "Storage Service Busy"),

    STATUS_1503(1503, "Service Not Available"),

    STATUS_1504(1504, "Flush Disk Timeout"),

    STATUS_10000(10000, "Can't construct a MessagingAccessPoint instance from the given OMS driver URL [%s]."),

    STATUS_10001(10001, "The OMS driver URL [%s] is illegal."),

    STATUS_10002(10002, "The implementation version [%s] is illegal."),

    STATUS_10003(10003, "The implementation version [%s] isn't compatible with the specification version [%s].");

    private int statusCode;

    private String reasonPhrase;

    private String reasonLocation;

    private static final String refBase = "http://openmessaging.cloud/internal/code";

    OMSResponseStatus(int statusCode, String reasonPhrase) {
        this.statusCode = statusCode;

        this.reasonPhrase = reasonPhrase;

        this.reasonLocation = generateReasonLocation(statusCode, reasonPhrase);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getReasonLocation() {
        return reasonLocation;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public static OMSRuntimeException generateException(OMSResponseStatus status, String... messageArgs) {
        return new OMSRuntimeException(status.getStatusCode(), String.format(status.getReasonLocation(), (Object[]) messageArgs));
    }

    public static OMSRuntimeException generateException(OMSResponseStatus status) {
        return new OMSRuntimeException(status.getStatusCode(), status.getReasonLocation());
    }

    public static OMSRuntimeException generateException(int statusCode, String reasonPhrase) {
        return new OMSRuntimeException(statusCode, reasonPhrase);
    }

    public static String generateReasonLocation(int statusCode, String reasonPhrase) {
        return reasonPhrase + "\nFor more information, please visit the URL, " + refBase + "#" + statusCode;
    }
}
