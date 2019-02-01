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

import java.util.List;

/**
 * This interface {@code QueueMetaData} contains methods are used for getting configurations related some certain
 * implementation. but this interface are not mandatory.
 * <p>
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface QueueMetaData {

    /**
     * In order to improve performance, in some scenarios where message persistence is required, some message middleware
     * will store messages on multiple partitions in multi servers.
     * <p>
     * In some scenarios, it is very useful to get the relevant partitions meta data for a queue.
     */
    interface Partition {
        /**
         * Partition identifier
         *
         * @return Partition identifier
         */
        int partitionId();

        /**
         * The host of the server where the partition is located
         * <p>
         *
         * @return The host of the server where the partition is located
         */
        String partitonHost();
    }

    /**
     * Queue name
     * <p>
     *
     * @return Queue name.
     */
    String queueName();

    /**
     * Get partition list belongs to the {@code queueName}
     *
     * @return List of {@link Partition} belongs to the specified queue.
     */
    List<Partition> partitions();
}
