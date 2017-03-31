package io.openmessaging.stream;

import io.openmessaging.KeyValue;

/**
 * A {@code Operator} is used to handle the flowing messages in {@code Routing}.
 *
 * There are many kinds of {@code Operator}, expression operator, deduplicator operator,
 * joiner operator, filter Operator, and so on.
 *
 * @author vintagewang@apache.org
 * @author yukon@apache.org
 *
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface Operator {
    /**
     * Returns the properties of this {@code Operator} object.
     *
     * @return the properties
     */
    KeyValue properties();

    /**
     * A expression to represent this {@code Operator} object.
     *
     * @return the expression
     */
    String expression();
}
