package io.openmessaging.routing;

import io.openmessaging.KeyValue;
import io.openmessaging.PropertyKeys;
import io.openmessaging.ResourceManager;

/**
 * A {@code Operator} is used to handle the flowing messages in {@code Routing}.
 *
 * There are many kinds of {@code Operator}, expression operator, deduplicator operator,
 * joiner operator, filter operator, rpc operator, and so on.
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
     * Changes to the return {@code KeyValue} are not reflected in physical {@code Operator},
     * and use {@link ResourceManager#createAndUpdateOperator(String, String, KeyValue)} to modify.
     * <p>
     * There are some standard properties defined by OMS for {@code Routing}:
     * <ul>
     * <li> {@link PropertyKeys#OPERATOR_NAME}, the unique name of this {@code Operator} object.
     * </ul>
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
