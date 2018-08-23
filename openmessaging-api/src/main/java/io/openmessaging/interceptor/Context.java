package io.openmessaging.interceptor;

import io.openmessaging.KeyValue;

public interface Context {

    /**
     * Returns the attributes of this {@code Context} instance.
     *
     * @return the attributes
     */
    KeyValue attributes();
}
