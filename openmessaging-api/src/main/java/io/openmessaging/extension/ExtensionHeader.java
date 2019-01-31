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

import io.openmessaging.Message;

/**
 * <p>
 * The {@code ExtensionHeader} interface contains extended properties for common implementations in current messaging
 * and streaming field, such as the queue-based partitioning implementation, but the related properties in this
 * interface are not mandatory.
 * </p>
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface ExtensionHeader {
    /**
     * The {@code PARTITION}  in extension header field contains the partition of target destination which the message
     * is being sent.
     * <p>
     *
     * When a {@link Message} is set with this value, this message will be delivered to specified partition, but the
     * premise is that the implementation of the server side is dependent on the partition or a queue-like storage
     * mechanism.
     * <p>
     *
     * @param partition The specified partition will be sent to.
     */
    void setPartition(int partition);

    /**
     * This method will return the partition of this message belongs.
     * <p>
     *
     * @return The {@code PARTITION} to which the message belongs
     */
    int getPartiton();

    /**
     * This method is only called by the server. and {@Code OFFSET} represents this message offset in partition.
     * <p>
     *
     * @param offset The offset in the current partition, used to quickly get this message in the queue
     */
    void setOffset(long offset);

    /**
     * This method will return the {@Code OFFSET}  in the partition to which the message belongs to, but the premise is
     * that the implementation of the server side is dependent on the partition or a queue-like storage mechanism.
     *
     * @return The offset of the partition to which the message belongs.
     */
    long getOffset();
}
