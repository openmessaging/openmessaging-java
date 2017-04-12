package io.openmessaging;

import io.openmessaging.exception.OMSMessageFormatException;
import io.openmessaging.exception.OMSRuntimeException;

/**
 * A {@code SequenceProducer} is a simple object used to send messages on behalf
 * of a {@code MessagingAccessPoint}.
 * <p>
 * An instance of {@code SequenceProducer} is created by calling the
 * {@link MessagingAccessPoint#createSequenceProducer()} method.
 * <p>
 * It provides a group way to send batch message to a specified destination.
 * A destination can be a {@link MessageHeader#TOPIC} or a {@link MessageHeader#QUEUE}.
 *
 * @author vintagewang@apache.org
 * @author yukon@apache.org
 *
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface SequenceProducer extends MessageFactory, ServiceLifecycle {
    /**
     * Returns the properties of this {@code SequenceProducer} instance.
     * Changes to the return {@code KeyValue} are not reflected in physical {@code Producer},
     * and use {@link ResourceManager#setProducerProperties(String, KeyValue)} to modify.
     *
     * @return the properties
     */
    KeyValue properties();

    /**
     * Sends a message to the specified destination, the destination should be preset to
     * {@link MessageHeader}, other header fields as well.
     * <p>
     * There is no {@code Promise} related. The calling thread doesn't
     * care about the send result and also have no context to get the result.
     * <p>
     * This message can't be consumed util it is committed.
     *
     * @param message a message will be sent
     * @throws OMSMessageFormatException if an invalid message is specified.
     */
    void send(Message message);

    /**
     * Sends a message to the specified destination, using the specified properties, the destination
     * should be preset to {@link MessageHeader}, other header fields as well.
     * <p>
     * There is no {@code Promise} related. The calling thread doesn't
     * care about the send result and also have no context to get the result.
     *
     * @param message a message will be sent
     * @param properties the specified properties
     * @throws OMSMessageFormatException if an invalid message is specified.
     */
    void send(Message message, KeyValue properties);

    /**
     * Commits all the sent messages since last commit or rollback operation.
     *
     * @throws OMSRuntimeException if the commit operation failed
     */
    void commit();

    /**
     * Discards all the sent messages since last commit or rollback operation.
     *
     * @throws OMSRuntimeException if the rollback operation failed
     */
    void rollback();
}
