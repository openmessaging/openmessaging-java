package io.openmessaging.internal;

import io.openmessaging.KeyValue;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.interceptor.MessagingAccessPointInterceptor;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MessagingAccessPointInterceptorFactory {
    private static List<MessagingAccessPointInterceptor> listObjectInterceptor = new CopyOnWriteArrayList<MessagingAccessPointInterceptor>();

    public static void addObjectInterceptor(MessagingAccessPointInterceptor objectInterceptor) {
        listObjectInterceptor.add(objectInterceptor);
    }

    public static void removeObjectInterceptor(MessagingAccessPointInterceptor objectInterceptor) {
        listObjectInterceptor.remove(objectInterceptor);
    }

    public static MessagingAccessPoint wrapMessagingAccessPoint(MessagingAccessPoint messagingAccessPoint) {
        MessagingAccessPoint newMessagingAccessPoint = messagingAccessPoint;

        for (int i = 0; i < listObjectInterceptor.size(); i++) {
            try {
                final MessagingAccessPoint nextMessagingAccessPoint = newMessagingAccessPoint;

                final MessagingAccessPoint thisMessagingAccessPoint = listObjectInterceptor.get(i).constructMessagingAccessPoint(
                    new MessagingAccessPointInterceptor.ConstructMessagingAccessPointContext() {
                    @Override
                    public MessagingAccessPoint messagingAccessPoint() {
                        return nextMessagingAccessPoint;
                    }

                    @Override
                    public KeyValue properties() {
                        return nextMessagingAccessPoint.properties();
                    }
                });

                if (thisMessagingAccessPoint != null) {
                    newMessagingAccessPoint = thisMessagingAccessPoint;
                }
            } catch (Throwable e) {
            }
        }

        return newMessagingAccessPoint;
    }
}
