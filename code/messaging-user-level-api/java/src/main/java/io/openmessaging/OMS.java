package io.openmessaging;

import io.openmessaging.internal.DefaultKeyValue;

public class OMS {
    /**
     * Returns a default and internal {@code KeyValue} implementation instance.
     *
     * @return a {@code KeyValue} instance
     */
    public static KeyValue newKeyValue() {
        return new DefaultKeyValue();
    }
}
