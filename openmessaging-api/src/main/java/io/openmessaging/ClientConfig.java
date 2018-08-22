package io.openmessaging;

public class ClientConfig {
    private static final KeyValue config = OMS.newKeyValue();

    private String instanceName;

    private String consumerId;

    private String producerId;

    private long sendTimeout;

    private String accessKey;

    private String driverImpl;

    private String accessPoints;

    private String namespace;

    private int operationTimeout = 5000;

    private String region;

    private String consumerGroup;

    private String producerGroup = "__OMS_PRODUCER_DEFAULT_GROUP";

    private int maxRedeliveryTimes = 16;

    private int maxConsumeThreadNums = 64;

    private int minConsumeThreadNums = 20;

    public static KeyValue getConfig() {
        return config;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(String consumerId) {
        this.consumerId = consumerId;
    }

    public String getProducerId() {
        return producerId;
    }

    public void setProducerId(String producerId) {
        this.producerId = producerId;
    }

    public long getSendTimeout() {
        return sendTimeout;
    }

    public void setSendTimeout(long sendTimeout) {
        this.sendTimeout = sendTimeout;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getDriverImpl() {
        return driverImpl;
    }

    public void setDriverImpl(String driverImpl) {
        this.driverImpl = driverImpl;
    }

    public String getAccessPoints() {
        return accessPoints;
    }

    public void setAccessPoints(String accessPoints) {
        this.accessPoints = accessPoints;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public int getOperationTimeout() {
        return operationTimeout;
    }

    public void setOperationTimeout(int operationTimeout) {
        this.operationTimeout = operationTimeout;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getConsumerGroup() {
        return consumerGroup;
    }

    public void setConsumerGroup(String consumerGroup) {
        this.consumerGroup = consumerGroup;
    }

    public String getProducerGroup() {
        return producerGroup;
    }

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }

    public int getMaxRedeliveryTimes() {
        return maxRedeliveryTimes;
    }

    public void setMaxRedeliveryTimes(int maxRedeliveryTimes) {
        this.maxRedeliveryTimes = maxRedeliveryTimes;
    }

    public int getMaxConsumeThreadNums() {
        return maxConsumeThreadNums;
    }

    public void setMaxConsumeThreadNums(int maxConsumeThreadNums) {
        this.maxConsumeThreadNums = maxConsumeThreadNums;
    }

    public int getMinConsumeThreadNums() {
        return minConsumeThreadNums;
    }

    public void setMinConsumeThreadNums(int minConsumeThreadNums) {
        this.minConsumeThreadNums = minConsumeThreadNums;
    }
}
