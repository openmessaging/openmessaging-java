package io.openmessaging.manager;

public class RoutingStrategy {
    public RoutingStrategy(String sourceQueue, String destinationQueue, String routingRule, String routingName) {
        this.sourceQueue = sourceQueue;
        this.destinationQueue = destinationQueue;
        this.routingRule = routingRule;
        this.routingName = routingName;
    }

    public RoutingStrategy() {
    }

    private String sourceQueue;

    private String destinationQueue;

    private String routingRule;

    private String routingName;

    public String getRoutingName() {
        return routingName;
    }

    public void setRoutingName(String routingName) {
        this.routingName = routingName;
    }

    public String getSourceQueue() {
        return sourceQueue;
    }

    public void setSourceQueue(String sourceQueue) {
        this.sourceQueue = sourceQueue;
    }

    public String getDestinationQueue() {
        return destinationQueue;
    }

    public void setDestinationQueue(String destinationQueue) {
        this.destinationQueue = destinationQueue;
    }

    public String getRoutingRule() {
        return routingRule;
    }

    public void setRoutingRule(String routingRule) {
        this.routingRule = routingRule;
    }
}
