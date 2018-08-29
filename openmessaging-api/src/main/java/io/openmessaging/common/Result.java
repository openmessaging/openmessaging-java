package io.openmessaging.common;

import jdk.nashorn.internal.ir.annotations.Immutable;

@Immutable
public interface Result {

    /**
     * Get execution error.
     *
     * @return operate result @see {@link Error}
     */
    Error getError();

    /**
     * When a remoting call return，OMS urged to set the response code to represent current remoting call's result.
     *
     * @param error operate result @see {@link Error}.
     */
    void setError(Error error);

    /**
     * Determine whether this remote calling is successful.
     *
     * @return if this remote calling has no exception throws, this method will  return true, otherwise return false.
     */
    boolean isSuccess();

}
