package io.openmessaging.consumer;

public interface ConsumerConfig {

    enum ConsumeStartPoint {
        /**
         * Consume message from where it stopped previously.
         */
        LATEST_MESSAGE,
        /**
         * If the consumer group is created so recently that the earliest message being bind has yet expired, which
         * means the consumer group represents a lately launched business, consuming will start from the very
         * beginning.
         */
        FIRST_MESSAGE,

        /**
         * If the consumer group is created so recently that the earliest message being bind has yet expired, which
         * means the consumer group represents a lately launched business, so user can specified a time as begining to
         * consume message.
         */
        SPECIFIED_TIME
    }

    /**
     * Obtain consumerId represents the unique consumer id of a consumer instance.
     *
     * @return consumer id
     */
    String getConsumerId();

    /**
     * Set the consumer instance with an unique id.
     *
     * @param consumerId
     */
    void setConsumerId(String consumerId);

    /**
     * Set a identifier for a group of consumer groups that consume the same queue
     *
     * @param consumerGroup an identifier for a group consumer
     */
    void setConsumerGroup(String consumerGroup);

    /**
     * Get the group identifier to which the consumer group belongs
     *
     * @return consumer group
     */
    String getConsumerGroup();

    /**
     * Set acknowledge mode for consumer, When set to true, the consumer will automatically ack the server that the
     * message has been received. otherwise, the user needs to manually confirm after processed this message.
     *
     * @param isAck
     */
    void setAutoAckMode(boolean isAck);

    /**
     * Obtain acknowledge mode.
     *
     * @return
     */
    boolean isAutoAckMode();

    /**
     * When a consumer start to consume message, this method is used to specify the starting point of consumption.
     *
     * @param consumeStartPoint consume strategy
     * @param startTime this parameter is used to specify a start time when {@link ConsumeStartPoint#SPECIFIED_TIME}
     * strategy is set
     */
    void setConsumeStartPoint(ConsumeStartPoint consumeStartPoint, long startTime);
}
