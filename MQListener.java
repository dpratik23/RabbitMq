import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.TextMessage;
import java.util.Map;

@Component
public class MQListener {

    @Value("${mq.destinationQueues}")
    private String[] destinationQueues;

    @Value("#{${response.mapping}}")
    private Map<String, String> responseMapping;

    @JmsListener(destination = "${mq.sourceQueues[0]}")
    public void listenSourceQueue1(Message message) {
        processMessage(message, destinationQueues[0]);
    }

    // Add similar methods for other source queues

    private void processMessage(Message message, String destinationQueue) {
        try {
            String messageText = ((TextMessage) message).getText();
            // Parse the messageText to extract accountId
            String accountId = extractAccountId(messageText);

            // Determine the response file based on accountId
            String responseFile = responseMapping.get(accountId);

            if (responseFile != null) {
                // Load response from resources
                String responseMessage = loadResponseFromFile(responseFile);
                // Send the response to the destination queue
                sendMessage(destinationQueue, responseMessage);
            }
        } catch (Exception e) {
            // Handle exceptions
        }
    }

    private String extractAccountId(String messageText) {
        // XML parsing logic to extract accountId
    }

    private String loadResponseFromFile(String responseFile) {
        // Logic to load the response from the file in the resources folder
    }

    private void sendMessage(String destinationQueue, String responseMessage) {
        // Logic to send the responseMessage to the destinationQueue
    }
}
