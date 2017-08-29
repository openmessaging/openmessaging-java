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

package io.openmessaging;

import java.util.Set;

/**
 * The {@code KeyValue} class represents a persistent set of attributes,
 * which supports method chaining.
 * <p>
 * A {@code KeyValue} object only allows {@code String} keys and can contain four primitive type
 * as values: {@code int}, {@code long}, {@code double}, {@code String}.
 * <p>
 * The {@code KeyValue} is a replacement of {@code Properties}, with simpler
 * interfaces and reasonable entry limits.
 * <p>
 * A {@code KeyValue} object may be used in concurrent scenarios, so the implementation
 * of {@code KeyValue} should consider concurrent related issues.
 *
 * @author vintagewang@apache.org
 * @author yukon@apache.org
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface KeyValue {
    /**
     * Inserts or replaces {@code int} value for the specified key.
     *
     * @param key the key to be placed into this {@code KeyValue} object
     * @param value the value corresponding to <tt>key</tt>
     */
    KeyValue put(String key, int value);

    /**
     * Inserts or replaces {@code long} value for the specified key.
     *
     * @param key the key to be placed into this {@code KeyValue} object
     * @param value the value corresponding to <tt>key</tt>
     */
    KeyValue put(String key, long value);

    /**
     * Inserts or replaces {@code double} value for the specified key.
     *
     * @param key the key to be placed into this {@code KeyValue} object
     * @param value the value corresponding to <tt>key</tt>
     */
    KeyValue put(String key, double value);

    /**
     * Inserts or replaces {@code String} value for the specified key.
     *
     * @param key the key to be placed into this {@code KeyValue} object
     * @param value the value corresponding to <tt>key</tt>
     */
    KeyValue put(String key, String value);

    /**
     * Searches for the {@code int} property with the specified key in this {@code KeyValue} object.
     * If the key is not found in this property list, zero is returned.
     *
     * @param key the property key
     * @return the value in this {@code KeyValue} object with the specified key value
     * @see #put(String, int)
     */
    int getInt(String key);

    /**
     * Searches for the {@code int} property with the specified key in this {@code KeyValue} object.
     * If the key is not found in this property list, the default value argument is returned.
     *
     * @param key the property key
     * @param defaultValue a default value
     * @return the value in this {@code KeyValue} object with the specified key value
     * @see #put(String, int)
     */
    int getInt(String key, int defaultValue);

    /**
     * Searches for the {@code long} property with the specified key in this {@code KeyValue} object.
     * If the key is not found in this property list, zero is returned.
     *
     * @param key the property key
     * @return the value in this {@code KeyValue} object with the specified key value
     * @see #put(String, long)
     */
    long getLong(String key);

    /**
     * Searches for the {@code long} property with the specified key in this {@code KeyValue} object.
     * If the key is not found in this property list, the default value argument is returned.
     *
     * @param key the property key
     * @param defaultValue a default value
     * @return the value in this {@code KeyValue} object with the specified key value
     * @see #put(String, long)
     */
    long getLong(String key, long defaultValue);

    /**
     * Searches for the {@code double} property with the specified key in this {@code KeyValue} object.
     * If the key is not found in this property list, zero is returned.
     *
     * @param key the property key
     * @return the value in this {@code KeyValue} object with the specified key value
     * @see #put(String, double)
     */
    double getDouble(String key);

    /**
     * Searches for the {@code double} property with the specified key in this {@code KeyValue} object.
     * If the key is not found in this property list, the default value argument is returned.
     *
     * @param key the property key
     * @param defaultValue a default value
     * @return the value in this {@code KeyValue} object with the specified key value
     * @see #put(String, double)
     */
    double getDouble(String key, double defaultValue);

    /**
     * Searches for the {@code String} property with the specified key in this {@code KeyValue} object.
     * If the key is not found in this property list, {@code null} is returned.
     *
     * @param key the property key
     * @return the value in this {@code KeyValue} object with the specified key value
     * @see #put(String, String)
     */
    String getString(String key);

    /**
     * Searches for the {@code String} property with the specified key in this {@code KeyValue} object.
     * If the key is not found in this property list, the default value argument is returned.
     *
     * @param key the property key
     * @param defaultValue a default value
     * @return the value in this {@code KeyValue} object with the specified key value
     * @see #put(String, String)
     */
    String getString(String key, String defaultValue);

    /**
     * Returns a {@link Set} view of the keys contained in this {@code KeyValue} object.
     * <p>
     * The set is backed by the {@code KeyValue}, so changes to the set are
     * reflected in the @code KeyValue}, and vice-versa.
     *
     * @return the key set view of this {@code KeyValue} object.
     */
    Set<String> keySet();

    /**
     * Tests if the specified {@code String} is a key in this {@code KeyValue}.
     *
     * @param key possible key
     * @return <code>true</code> if and only if the specified key is in this {@code KeyValue}, <code>false</code>
     * otherwise.
     */
    boolean containsKey(String key);
}
