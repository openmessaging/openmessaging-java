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
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.openmessaging.exception;

/**
 * The {@code OMSTimeOutException} must be thrown when a blocking operation times out.
 * <p>
 * Blocking operations for which a timeout is specified need a means to indicate that the timeout has occurred.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public class OMSTimeOutException extends OMSRuntimeException {
    /**
     * @see OMSRuntimeException#OMSRuntimeException(int, String)
     */
    public OMSTimeOutException(int errorCode, String message) {
        super(errorCode, message);
    }

    /**
     * @see OMSRuntimeException#OMSRuntimeException(int, Throwable)
     */
    public OMSTimeOutException(int errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    /**
     * @see OMSRuntimeException#OMSRuntimeException(int, String, Throwable)
     */
    public OMSTimeOutException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
