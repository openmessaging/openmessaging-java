package io.openmessaging.producer;

import io.openmessaging.KeyValue;
import io.openmessaging.Message;

/**
 * @author vintagewang@apache.org
 * @author yukon@apache.org
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface BatchMessageSender {
    void send(Message message);

    void send(Message message, KeyValue properties);

    void commit();

    void rollback();

    void close();
}
