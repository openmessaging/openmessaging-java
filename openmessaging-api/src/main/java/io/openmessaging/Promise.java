/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package io.openmessaging;

/**
 * Special {@link Future} which is writable.
 * <p>
 * A {@code Promise} can be completed or canceled, cancellation is performed by the {@code cancel} method.
 * Once a computation has completed, the computation cannot be cancelled. If you would like to use a {@code Promise}
 * for the sake of cancellability but not provide a usable result, you can declare type+s of the form
 * {@code Promise<?>} and return {@code null} as a result of the underlying task.
 *
 * @author vintagewang@apache.org
 * @author yukon@apache.org
 * @version OMS 1.0
 * @since OMS 1.0
 */
public interface Promise<V> extends Future<V> {

    /**
     * Attempts to cancel execution of this task.  This attempt will fail if the task has already completed, has already
     * been cancelled, or could not be cancelled for some other reason. If successful, and this task has not started
     * when {@code cancel} is called, this task should never run.  If the task has already started, then the {@code
     * mayInterruptIfRunning} parameter determines whether the thread executing this task should be interrupted in an
     * attempt to stop the task.
     * <p>
     * After this method returns, subsequent calls to {@link #isDone} will always return {@code true}.  Subsequent calls
     * to {@link #isCancelled} will always return {@code true} if this method returned {@code true}.
     *
     * @param mayInterruptIfRunning {@code true} if the thread executing this task should be interrupted; otherwise,
     * in-progress tasks are allowed to complete
     * @return {@code false} if the task could not be cancelled, typically because it has already completed normally;
     * {@code true} otherwise
     */
    boolean cancel(boolean mayInterruptIfRunning);

    /**
     * Set the value to this promise and mark it completed if set successfully.
     *
     * @param value Value
     * @return Whether set is success
     */
    boolean set(V value);

    /**
     * Marks this promise as a failure and notifies all listeners.
     *
     * @param cause the cause
     * @return Whether set is success
     */
    boolean setFailure(Throwable cause);
}
