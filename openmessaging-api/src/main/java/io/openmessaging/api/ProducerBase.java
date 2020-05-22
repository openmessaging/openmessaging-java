package io.openmessaging.api;

/**
 * {@code ProducerBase} is the basic of other {@code Producer} interfaces
 * and is used to define some common methods.
 *
 * @author chengyi (mark.lx@antfin.com) 2020-05-22 11:12
 */
public interface ProducerBase {

    /**
     * Create message builder, used for build message in a fluent way.
     *
     * @param <T> the class of message's payload
     * @return MessageBuilder
     */
    <T> MessageBuilder<T> messageBuilder();
}
