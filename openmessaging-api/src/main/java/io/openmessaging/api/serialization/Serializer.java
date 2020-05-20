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
package io.openmessaging.api.serialization;

import java.io.Closeable;

/**
 * An interface for converting objects to bytes.
 *
 * @param <T> Type to be serialized from.
 * @version OMS 2.0.0
 * @since OMS 2.0.0
 *
 * A class that implements this interface is expected to have a constructor with no parameter.
 * <p>
 */
public interface Serializer<T> extends Closeable {

    /**
     * Serialize the message body, can also do schema verification before this.
     *
     * @param topic
     * @param t
     * @return
     */
    byte[] serialize(String topic, T t);
}
