package io.openmessaging.producer;

public interface TransactionalResult extends SendResult {
    /**
     * The unique transactionId id related to the {@code TransactionResult} instance.
     *
     * @return the transactional id
     */
    String transactionId();

    /**
     * Commits a transaction.
     */
    void commit();

    /**
     * Rolls back a transaction.
     */
    void rollback();
}
