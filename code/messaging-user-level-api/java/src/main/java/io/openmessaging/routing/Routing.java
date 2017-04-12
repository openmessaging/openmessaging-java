package io.openmessaging.routing;

import io.openmessaging.KeyValue;
import io.openmessaging.PropertyKeys;
import io.openmessaging.ResourceManager;
import java.util.List;

/**
 * A {@code Routing} object is responsible for routing the messages from {@code Topic} to {@code Queue}, with
 * some useful operators to filter or compute the source messages.
 *
 * @author vintagewang@apache.org
 * @author yukon@apache.org
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface Routing {
    /**
     * Returns the properties of this {@code Routing} instance.
     * Changes to the return {@code KeyValue} are not reflected in physical {@code Routing},
     * and use {@link ResourceManager#createAndUpdateRouting(Routing)} (String, KeyValue)} to modify.
     * <p>
     * There are some standard properties defined by OMS for {@code Routing}:
     * <ul>
     * <li> {@link PropertyKeys#SRC_TOPIC}, the source topic of this {@code Routing} object.
     * <li> {@link PropertyKeys#DST_QUEUE}, the destination queue of this {@code Routing} object.
     * <li> {@link PropertyKeys#ROUTING_NAME}, the unique name of this {@code Routing} object.
     * </ul>
     *
     * @return the properties
     */
    KeyValue properties();

    /**
     * Adds a {@code Operator} to the tail of operator pipeline in this {@code Routing} object.
     *
     * @param op a specified operator
     * @return this {@code Routing} object
     */
    Routing addOperator(Operator op);

    /**
     * Removes a specified {@code Operator} from the operator pipeline in this {@code Routing} object.
     *
     * @param op a specified operator
     * @return this {@code Routing} object
     */
    Routing deleteOperator(Operator op);

    /**
     * Returns the operator pipeline in this {@code Routing} object.
     *
     * @return the operator pipeline
     */
    List<Operator> operators();
}
