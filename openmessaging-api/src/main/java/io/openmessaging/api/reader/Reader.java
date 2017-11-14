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

package io.openmessaging.api.reader;

import io.openmessaging.api.Message;
import io.openmessaging.exception.OMSException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * A reader is a `sequential` or `streaming` consumer that can be used for scanning through all the messages
 * currently available in a topic (partition).
 */
public interface Reader extends AutoCloseable {

    /**
     * @return the topic from which this reader is reading from
     */
    String getTopic();

    /**
     * Read the next message in the topic
     *
     * @return the next messasge
     * @throws io.openmessaging.exception.OMSException
     */
    Message readNext() throws OMSException;

    /**
     * Read the next message in the topic.
     *
     * @return the next messasge
     * @throws OMSException
     */
    Message readNext(int timeout, TimeUnit unit) throws OMSException;

    CompletableFuture<Message> readNextAsync();

    /**
     * Asynchronously close the reader and stop the broker to push more messages
     *
     * @return a future that can be used to track the completion of the operation
     */
    CompletableFuture<Void> closeAsync();

}
