import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class QueueMappingConfig {

    @Value("${mq.sourceQueues}")
    private String[] sourceQueues;

    @Value("${mq.destinationQueues}")
    private String[] destinationQueues;

    private Map<String, String> queueMapping;

    @PostConstruct
    public void initializeMapping() {
        if (sourceQueues.length != destinationQueues.length) {
            throw new IllegalArgumentException("Source queues count must match destination queues count.");
        }
        
        queueMapping = new HashMap<>();
        for (int i = 0; i < sourceQueues.length; i++) {
            queueMapping.put(sourceQueues[i], destinationQueues[i]);
        }
    }

    public String getDestinationQueue(String sourceQueue) {
        return queueMapping.get(sourceQueue);
    }
}
