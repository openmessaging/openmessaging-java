package io.openmessaging;

/**
 * The result of sending a OMS message to server
 * with the message id and some properties.
 *
 * @author yukon@apache.org
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface SendResult {
    /**
     * The unique message id related to the {@code SendResult} instance.
     *
     * @return the message id
     */
    String messageId();

    /**
     * Returns the properties of this {@code SendResult} instance.
     *
     * @return the properties
     */
    KeyValue properties();
}
