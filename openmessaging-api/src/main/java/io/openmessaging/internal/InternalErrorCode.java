package io.openmessaging.internal;

/**
 * The internal error code used by {@link MessagingAccessPointAdapter}
 */
public enum InternalErrorCode {
    OMS_DRIVER_URL_UNAVAILABLE("#oms_driver_url_unavailable", "Can't construct a MessagingAccessPoint instance from the given OMS driver URL [%s]."),
    OMS_DRIVER_URL_ILLEGAL("#oms_driver_url_illegal", "The OMS driver URL [%s] is illegal."),
    IMPL_VERSION_ILLEGAL("#impl_version_illegal", "The implementation version [%s] is illegal."),
    SPEC_IMPL_VERSION_MISMATCH("#spec_impl_version_mismatch", "The implementation version [%s] isn't compatible with the specification version [%s].")
    ;

    String refBase = "http://openmessaging.cloud/internal/error-code.html";
    String message;

    InternalErrorCode(String refLoc, String message) {
        this.message = message + "\nFor more information, please visit the URL, " + refBase + refLoc;
    }
}
