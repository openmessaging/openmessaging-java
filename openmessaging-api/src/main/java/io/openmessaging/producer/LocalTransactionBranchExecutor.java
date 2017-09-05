package io.openmessaging.producer;

import io.openmessaging.Message;

/**
 * @author vintagewang@apache.org
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface LocalTransactionBranchExecutor {
    void doLocalTransactionBranch(Message message, DoLocalTransactionBranchContext context, Object arg);

    void checkLocalTransactionBranch(Message message, CheckLocalTransactionBranchContext context);

    interface DoLocalTransactionBranchContext {
        void commit();

        void rollback();
    }

    interface CheckLocalTransactionBranchContext {
        void commit();

        void rollback();
    }
}
