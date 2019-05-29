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

import io.openmessaging.annotation.Optional;
import java.util.List;
import java.util.Set;

/**
 * This interface {@code QueueMetaData} contains methods are used for getting configurations related some certain
 * implementation. but this interface are not mandatory.
 * <p>
 *
 * In order to improve performance, in some scenarios where message persistence is required, some message middleware
 * will store messages on multiple partitions in multi servers.
 * <p>
 *
 * In some scenarios, it is very useful to get the relevant partitions meta data for a queue.
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
@Optional
public interface QueueMetaData {

    /**
     * Set queueName to this Message Queue.
     * @param queueName
     */
    void setQueueName(String queueName);

    /**
     * Set the specified partition.
     * @param partitionId
     */
    void setPartitionId(int partitionId);

    /**
     * Get partition identifier of target queue.
     *
     * @return Partition identifier
     */
    int partitionId();

    /**
     * Queue name
     * <p>
     *
     * @return Queue name.
     */
    String queueName();
}
