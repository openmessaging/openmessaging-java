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
package io.openmessaging.extension;

/**
 * <p>
 * This interface contains some methods are used for getting configurations related implementation. but this interface
 * are not mandatory.
 * </p>
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface ExtensionClient {

    /**
     * This method used for getting the related queue's meta data.
     * <p>
     *
     * @param queueName Queue name, message destination.
     * @return {@link QueueMetaData} Queue config in the server
     */
    QueueMetaData getQueueMetaData(String queueName);
}
