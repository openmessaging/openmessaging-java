package io.openmessaging.samples.routing;

import io.openmessaging.KeyValue;
import io.openmessaging.common.BaseResult;
import io.openmessaging.manager.QueueConfig;
import io.openmessaging.manager.ListQueueResult;
import io.openmessaging.manager.ResourceManager;
import io.openmessaging.manager.ListRoutingResult;
import io.openmessaging.manager.RoutingStrategy;
import io.openmessaging.manager.ListStreamResult;
import java.util.List;

public class ResourceManagerImpl implements ResourceManager {
    @Override
    public BaseResult createNamespace(String nsName) {
        return null;
    }

    @Override
    public BaseResult setNamespaceAttributes(String nsName, KeyValue attributes) {
        return null;
    }

    @Override
    public KeyValue getNamespaceAttributes(String nsName) {
        return null;
    }

    @Override
    public BaseResult deleteNamespace(String nsName) {
        return null;
    }

    @Override
    public List<String> listNamespaces() {
        return null;
    }

    @Override
    public BaseResult createQueue(String queueName) {
        return null;
    }

    @Override
    public BaseResult createQueue(String queueName, QueueConfig queueConfig) {
        return null;
    }

    @Override
    public BaseResult setQueueConfig(String queueName, QueueConfig queueConfig) {
        return null;
    }

    @Override
    public QueueConfig getQueueConfig(String queueName) {
        return null;
    }

    @Override
    public BaseResult deleteQueue(String queueName) {
        return null;
    }

    @Override
    public ListQueueResult listQueues(String nsName) {
        return null;
    }

    @Override
    public BaseResult createRouting(String routingName, RoutingStrategy routingStrategy) {
        return null;
    }

    @Override
    public RoutingStrategy getRouting(String routingName) {
        return null;
    }

    @Override
    public BaseResult deleteRouting(String routingName) {
        return null;
    }

    @Override
    public ListRoutingResult listRoutings(String nsName) {
        return null;
    }

    @Override
    public ListStreamResult listStreams(String queueName) {
        return null;
    }
}
