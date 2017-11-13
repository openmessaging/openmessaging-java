/*
 * Copyright 2017 OpenMessaging
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package io.openmessaging.api;

import java.io.Serializable;

/**
 * The common instance used for specifying configuration.
 */
public interface Configuration extends Serializable {

    Configuration setInt(String key, int value);

    Configuration setLong(String key, long value);

    Configuration setBoolean(String key, boolean value);

    Configuration setString(String key, String value);

    Integer getInt(String key);

    int getInt(String key, int defaultVal);

    Long getLong(String key);

    long getLong(String key, int defaultVal);

    Boolean getBoolean(String key);

    boolean getBoolean(String key, boolean defaultVal);

    String getString(String key);

    String getString(String key, String defaultVal);

}
