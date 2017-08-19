package io.openmessaging.internal;

import io.openmessaging.KeyValue;
import io.openmessaging.MessagingAccessPoint;
import io.openmessaging.interceptor.ObjectInterceptor;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ObjectInterceptorFactory {
    private static List<ObjectInterceptor> listObjectInterceptor = new CopyOnWriteArrayList<ObjectInterceptor>();

    public static void addObjectInterceptor(ObjectInterceptor objectInterceptor) {
        listObjectInterceptor.add(objectInterceptor);
    }

    public static void removeObjectInterceptor(ObjectInterceptor objectInterceptor) {
        listObjectInterceptor.remove(objectInterceptor);
    }

    public static MessagingAccessPoint wrapMessagingAccessPoint(MessagingAccessPoint messagingAccessPoint) {
        MessagingAccessPoint newMessagingAccessPoint = messagingAccessPoint;

        for (int i = 0; i < listObjectInterceptor.size(); i++) {
            try {
                final MessagingAccessPoint nextMessagingAccessPoint = newMessagingAccessPoint;

                final MessagingAccessPoint thisMessagingAccessPoint = listObjectInterceptor.get(i).constructMessagingAccessPoint(new ObjectInterceptor.ConstructMessagingAccessPointContext() {
                    @Override public MessagingAccessPoint messagingAccessPoint() {
                        return nextMessagingAccessPoint;
                    }

                    @Override public KeyValue properties() {
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
