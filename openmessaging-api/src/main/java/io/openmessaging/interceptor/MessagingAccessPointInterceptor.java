package io.openmessaging.interceptor;

import io.openmessaging.KeyValue;
import io.openmessaging.MessagingAccessPoint;

/**
 * A {@code MessagingAccessPointInterceptor} is used to wrap {@code MessagingAccessPoint}.
 *
 * @author vintagewang@apache.org
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface MessagingAccessPointInterceptor {
    /**
     * Constructs a new {@code MessagingAccessPoint} instance based on context.
     *
     * @param context the construct context
     * @return a {@code MessagingAccessPoint} instance
     */
    MessagingAccessPoint constructMessagingAccessPoint(ConstructMessagingAccessPointContext context);

    interface ConstructMessagingAccessPointContext {
        /**
         * Returns current {@code MessagingAccessPoint} in this context
         *
         * @return a {@code MessagingAccessPoint} instance
         */
        MessagingAccessPoint messagingAccessPoint();

        /**
         * Returns properties in this context
         *
         * @return the properties
         */
        KeyValue properties();
    }
}
