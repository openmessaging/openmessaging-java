package io.openmessaging.internal;

/**
 * @author vintagewang@apache.org
 * @version OMS 1.0
 * @since OMS 1.0
 */
public class DefaultMessageGroupCursor implements MessageGroupCursor {
    private final String value;

    public DefaultMessageGroupCursor(final String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return this.value;
    }
}
