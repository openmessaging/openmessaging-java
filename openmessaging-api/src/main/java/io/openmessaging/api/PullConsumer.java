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
package io.openmessaging.api;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface PullConsumer extends Admin {

    interface TopicPartitionChangeListener {
        /**
         * This method will be invoked in the condition of partition numbers changed, These scenarios occur when the
         * topic is expanded or shrunk.
         *
         * @param topicPartitions
         */
        void onChanged(Set<TopicPartition> topicPartitions);
    }

    /**
     * Get metadata about the partitions for a given topic. This method will issue a remote call to the server if it
     * does not already have any metadata about the given topic.
     *
     * @param topic
     * @return
     */
    Set<TopicPartition> topicPartitions(String topic);

    /**
     * Subscribe message in order.
     *
     * @param topic message topic.
     * @param subExpression Subscribe to the filter expression string, which the broker filters based on this
     * expression. <br> eg: "tag1 || tag2 || tag3"<br>, if subExpression is equal to null or *, it means subscribe all
     * messages.
     */
    void subscribe(final String topic, final String subExpression);

    /**
     * Subscribe to messages, which can be filtered using SQL expressions.
     *
     * @param topic message topic
     * @param selector Subscribe to the message selector (can be empty, indicating no filtering), the ONS server filters
     * according to the expression in this selector. Currently supports two expression syntax: {@link
     * ExpressionType#TAG}, {@link ExpressionType#SQL92} Among them, the effect of TAG filtering is consistent with the
     * above interface.
     */
    void subscribe(final String topic, final MessageSelector selector);

    /**
     * Manually assign a list of partitions to this consumer. This interface does not allow for incremental assignment
     * and will replace the previous assignment (if there is one).
     *
     * If auto-commit is enabled, an async commit (based on the old assignment) will be triggered before the new
     * assignment replaces the old one.
     *
     * @param topicPartitions
     */
    void assign(Collection<TopicPartition> topicPartitions);

    /**
     * Register a callback for sensing topic metadata changes.
     *
     * @param topic
     * @param callback
     */
    void registerTopicPartitionChangedListener(String topic, TopicPartitionChangeListener callback);

    /**
     * Fetch data for the topics or partitions specified using assign API. It is an error to not have subscribed to any
     * topics or partitions before polling for data.
     *
     * @param timeout in millisecond
     * @return
     */
    List<Message> poll(long timeout);

    /**
     * Overrides the fetch offsets that the consumer will use on the next {@link #poll(long)} }. If this API is invoked
     * for the same message queue more than once, the latest offset will be used on the next poll(). Note that you may
     * lose data if this API is arbitrarily used in the middle of consumption.
     *
     * @param topicPartition
     * @param offset
     */
    void seek(TopicPartition topicPartition, long offset);

    /**
     * Overrides the fetch offsets with the beginning offset in server that the consumer will use on the next {@link
     * #poll(long)} }.
     *
     * @param topicPartition
     */
    void seekToBeginning(TopicPartition topicPartition);

    /**
     * Overrides the fetch offsets with the end offset in server that the consumer will use on the next {@link
     * #poll(long)} }.
     *
     * @param topicPartition
     */
    void seekToEnd(TopicPartition topicPartition);

    /**
     * Suspend fetching from the requested message queues. Future calls to {@link #poll(long)} will not return any
     * records from these message queues until they have been resumed using {@link #resume(Collection)}.
     *
     * Note that this method does not affect message queue subscription. In particular, it does not cause a group
     * rebalance.
     *
     * @param topicPartitions
     */
    void pause(Collection<TopicPartition> topicPartitions);

    /**
     * Resume specified message queues which have been paused with {@link #pause(Collection)}. New calls to {@link
     * #poll(long)} will return records from these partitions if there are any to be fetched. If the message queues were
     * not previously paused, this method is a no-op.
     *
     * @param topicPartitions
     */
    void resume(Collection<TopicPartition> topicPartitions);

    /**
     * Look up the offsets for the given message queue by timestamp. The returned offset for each message queue is the
     * earliest offset whose timestamp is greater than or equal to the given timestamp in the corresponding message
     * queue.
     *
     * @param topicPartition
     * @param timestamp
     * @return
     */
    Long offsetForTimestamp(TopicPartition topicPartition, Long timestamp);

    /**
     * Get the last committed offset for the given message queue (whether the commit happened by this process or
     * another). This offset will be used as the position for the consumer in the event of a failure.
     *
     * @param topicPartition
     * @return
     */
    Long committed(TopicPartition topicPartition);

    /**
     * Sync commit current consumed offset to server.
     */
    void commitSync();
}
