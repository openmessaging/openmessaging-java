/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.openmessaging.internal;

import io.openmessaging.exception.OMSRuntimeException;

/**
 * The internal error code used by {@link MessagingAccessPointAdapter}
 */
public enum InternalErrorCode {
    OMS_DRIVER_UNAVAILABLE("#oms_driver_unavailable", "Can't construct a MessagingAccessPoint instance from the given OMS driver URL [%s]."),
    OMS_DRIVER_URL_ILLEGAL("#oms_driver_url_illegal", "The OMS driver URL [%s] is illegal."),
    IMPL_VERSION_ILLEGAL("#impl_version_illegal", "The implementation version [%s] is illegal."),
    SPEC_IMPL_VERSION_MISMATCH("#spec_impl_version_mismatch", "The implementation version [%s] isn't compatible with the specification version [%s].")
    ;

    String refBase = "http://openmessaging.cloud/internal/error-code";
    String message;

    InternalErrorCode(String refLoc, String message) {
        this.message = message + "\nFor more information, please visit the URL, " + refBase + refLoc;
    }

    public static OMSRuntimeException generateInternalException(InternalErrorCode errorCode, String... messageArgs) {
        return new OMSRuntimeException(errorCode.name(), String.format(errorCode.message, (Object[]) messageArgs));
    }
}
