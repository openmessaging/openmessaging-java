package io.openmessaging.interceptor;

import io.openmessaging.KeyValue;
import io.openmessaging.MessagingAccessPoint;

/**
 * @author vintagewang@apache.org
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface MessagingAccessPointInterceptor {
    MessagingAccessPoint constructMessagingAccessPoint(ConstructMessagingAccessPointContext context);

    interface ConstructMessagingAccessPointContext {
        MessagingAccessPoint messagingAccessPoint();

        KeyValue properties();
    }
}
