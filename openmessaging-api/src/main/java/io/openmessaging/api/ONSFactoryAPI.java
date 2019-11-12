package io.openmessaging.api;

import io.openmessaging.api.batch.BatchConsumer;
import io.openmessaging.api.order.OrderConsumer;
import io.openmessaging.api.order.OrderProducer;
import io.openmessaging.api.transaction.LocalTransactionChecker;
import io.openmessaging.api.transaction.TransactionProducer;
import java.util.Properties;

/**
 * {@link MessagingAccessPoint} is recommended.
 */
@Deprecated
public interface ONSFactoryAPI {

    Producer createProducer(final Properties properties);


    Consumer createConsumer(final Properties properties);


    BatchConsumer createBatchConsumer(final Properties properties);


    OrderProducer createOrderProducer(final Properties properties);


    OrderConsumer createOrderedConsumer(final Properties properties);


    TransactionProducer createTransactionProducer(final Properties properties,
        final LocalTransactionChecker checker);
}
