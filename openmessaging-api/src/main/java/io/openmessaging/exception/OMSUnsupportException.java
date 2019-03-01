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

import io.openmessaging.annotation.Optional;

/**
 * The {@code OMSUnsupportException} must be thrown when the specified methods, headers or properties have not been
 * provided by vendors, these methods or headers are usually marked by {@link Optional}.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public class OMSUnsupportException extends OMSRuntimeException {
    /**
     * @see OMSUnsupportException#OMSUnsupportException(int, String)
     */
    public OMSUnsupportException(int errorCode, String message) {
        super(errorCode, message);
    }

    /**
     * @see OMSUnsupportException#OMSUnsupportException(int, Throwable)
     */
    public OMSUnsupportException(int errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    /**
     * @see OMSUnsupportException#OMSUnsupportException(int, String, Throwable)
     */
    public OMSUnsupportException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
