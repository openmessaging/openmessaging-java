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
package io.openmessaging.interceptor;

import io.openmessaging.KeyValue;

/**
 * A {@code Context} is used to transfer user's business data in the interceptor.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface Context {

    /**
     * Returns the attributes of this {@code Context} instance.
     *
     * @return the attributes.
     */
    KeyValue attributes();
}
