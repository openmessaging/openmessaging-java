package io.openmessaging;

/**
 * @author vintagewang@apache.org
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface LocalTransactionBranchExecutor {
    boolean doLocalTransactionBranch(Message message, DoLocalTransactionBranchContext context);

    boolean checkLocalTransactionBranch(Message message, CheckLocalTransactionBranchContext context);

    interface DoLocalTransactionBranchContext {
    }

    interface CheckLocalTransactionBranchContext {
    }
}
