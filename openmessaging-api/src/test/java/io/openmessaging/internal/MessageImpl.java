package io.openmessaging.internal;

import io.openmessaging.KeyValue;
import io.openmessaging.Message;
import io.openmessaging.exception.OMSMessageFormatException;

public class MessageImpl implements Message {
    @Override
    public Headers headers() {
        return null;
    }

    @Override
    public KeyValue properties() {
        return null;
    }

    @Override
    public byte[] getData() {
        return new byte[0];
    }

    @Override
    public void setData(byte[] data) {

    }
}
