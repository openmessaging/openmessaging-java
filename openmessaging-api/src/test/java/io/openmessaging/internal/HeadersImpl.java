package io.openmessaging.internal;

import io.openmessaging.Message;

public class HeadersImpl implements Message.Headers {

    @Override
    public Message.Headers setDestination(String destination) {
        return null;
    }

    @Override
    public Message.Headers setMessageId(String messageId) {
        return null;
    }

    @Override
    public Message.Headers setBornTimestamp(long bornTimestamp) {
        return null;
    }

    @Override
    public Message.Headers setBornHost(String bornHost) {
        return null;
    }

    @Override
    public Message.Headers setStoreTimestamp(long storeTimestamp) {
        return null;
    }

    @Override
    public Message.Headers setStoreHost(String storeHost) {
        return null;
    }

    @Override
    public Message.Headers setDelayTime(long delayTime) {
        return null;
    }

    @Override
    public Message.Headers setExpireTime(long expireTime) {
        return null;
    }

    @Override
    public Message.Headers setPriority(short priority) {
        return null;
    }

    @Override
    public Message.Headers setDurability(short durability) {
        return null;
    }

    @Override
    public Message.Headers setSearchKey(String searchKey) {
        return null;
    }

    @Override
    public Message.Headers setTraceId(String traceId) {
        return null;
    }

    @Override
    public Message.Headers setDeliveryCount(int deliveryCount) {
        return null;
    }

    @Override
    public Message.Headers setTransactionId(String transactionId) {
        return null;
    }

    @Override
    public Message.Headers setCorrelationId(String correlationId) {
        return null;
    }

    @Override
    public Message.Headers setCompression(short compression) {
        return null;
    }

    @Override
    public String getDestination() {
        return null;
    }

    @Override
    public String getMessageId() {
        return null;
    }

    @Override
    public long getBornTimestamp() {
        return 0;
    }

    @Override
    public String getBornHost() {
        return null;
    }

    @Override
    public long getStoreTimestamp() {
        return 0;
    }

    @Override
    public String getStoreHost() {
        return null;
    }

    @Override
    public long getDelayTime() {
        return 0;
    }

    @Override
    public long getExpireTime() {
        return 0;
    }

    @Override
    public short getPriority() {
        return 0;
    }

    @Override
    public short getDurability() {
        return 0;
    }

    @Override
    public String getSearchKey() {
        return null;
    }

    @Override
    public String getTraceId() {
        return null;
    }

    @Override
    public int getDeliveryCount() {
        return 0;
    }

    @Override
    public String getTransactionId() {
        return null;
    }

    @Override
    public String getCorrelationId() {
        return null;
    }

    @Override
    public short getCompression() {
        return 0;
    }
}
