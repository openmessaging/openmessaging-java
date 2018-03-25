package io.openmessaging.producer;

import io.openmessaging.Message;

/**
 * Each executor will be associated with a transactional message, can be used to execute local transaction
 * branch and submit the transaction status(commit or rollback).
 * <p>
 *
 * The associated message will be exposed to consumer when the local transaction has been committed, or be
 * discarded if local transaction has been rolled back.
 *
 * <p>
 * If the executor don't submit the transaction status for a long time, the server may lookup it forwardly through
 * {@link LocalTransactionBranchExecutor#checkLocalTransactionBranch(Message, CheckLocalTransactionBranchContext)}
 *
 * @version OMS 1.0.0
 * @since OMS 1.0.0
 */
public interface LocalTransactionBranchExecutor {
    /**
     * Executes the local transaction branch after the message is sent successfully, and submits the
     * status whether the transaction was successfully committed or rolled back.
     *
     * @param message the associated message
     * @param context the execution context
     */
    void doLocalTransactionBranch(Message message, DoLocalTransactionBranchContext context);

    /**
     * Checks the status of the local transaction branch.
     *
     * @param message the associated message
     * @param context the check context
     */
    void checkLocalTransactionBranch(Message message, CheckLocalTransactionBranchContext context);

    interface DoLocalTransactionBranchContext {
        /**
         * Commits a transaction.
         */
        void commit();

        /**
         * Rolls back a transaction.
         */
        void rollback();
    }

    interface CheckLocalTransactionBranchContext {
        /**
         * Commits a transaction.
         */
        void commit();

        /**
         * Rolls back a transaction.
         */
        void rollback();
    }
}
