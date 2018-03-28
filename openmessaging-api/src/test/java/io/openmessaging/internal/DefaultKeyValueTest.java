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

package io.openmessaging.internal;

import io.openmessaging.KeyValue;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultKeyValueTest {
    private KeyValue keyValue = new DefaultKeyValue();

    @Test
    public void testPutAndGet() throws Exception {
        keyValue.put("IntKey", 123);
        assertThat(keyValue.getInt("IntKey")).isEqualTo(123);

        keyValue.put("StringKey", "HELLO");
        assertThat(keyValue.getString("StringKey")).isEqualTo("HELLO");

        keyValue.put("LongKey", 123L);
        assertThat(keyValue.getLong("LongKey")).isEqualTo(123L);

        keyValue.put("DoubleKey", 1.23);
        assertThat(keyValue.getDouble("DoubleKey")).isEqualTo(1.23);
    }

    @Test
    public void testKeySet() throws Exception {
        keyValue.put("IndexKey", 123);
        assertThat(keyValue.keySet()).contains("IndexKey");
    }

    @Test
    public void testContainsKey() throws Exception {
        keyValue.put("ContainsKey", 123);
        assertThat(keyValue.containsKey("ContainsKey")).isTrue();
    }

}