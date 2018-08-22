package io.openmessaging.manager;

public class QueueConfig {
    private String queueName;

    private String queuePermission;

    private int readNum;

    private int writeNum;

    private int queueNum;

    public int getQueueNum() {
        return queueNum;
    }

    public void setQueueNum(int queueNum) {
        this.queueNum = queueNum;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getQueuePermission() {
        return queuePermission;
    }

    public void setQueuePermission(String queuePermission) {
        this.queuePermission = queuePermission;
    }

    public int getReadNum() {
        return readNum;
    }

    public void setReadNum(int readNum) {
        this.readNum = readNum;
    }

    public int getWriteNum() {
        return writeNum;
    }

    public void setWriteNum(int writeNum) {
        this.writeNum = writeNum;
    }
}
