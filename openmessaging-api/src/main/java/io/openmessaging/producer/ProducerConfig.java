package io.openmessaging.producer;

public interface ProducerConfig {
    /**
     * Set the producer instance with an unique id.
     */
    void setProducerId();

    /**
     * Obtain the group id and this id represents the unique consumer id of a consumer instance.
     *
     * @return group id
     */
    String getProducerId();

}
