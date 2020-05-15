package io.openmessaging.api;

import java.util.Set;

public interface TopicPartitionChangeListener {
    /**
     * This method will be invoked in the condition of partition numbers changed, These scenarios occur when the
     * topic is expanded or shrunk.
     *
     * @param topicPartitions
     */
    void onChanged(Set<TopicPartition> topicPartitions);
}