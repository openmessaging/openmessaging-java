package io.openmessaging.api;

public abstract class AsyncConsumeContext extends ConsumeContext {

    /**
     * Asynchronously commit consumption status.
     * @param action if current message consumed success, should pass {@link Action#CommitMessage} otherwise, should pass {@link
     * Action#ReconsumeLater}, and this message will be delivered again.
     */
    public abstract void commit(Action action);

}