package example;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.ReceiveMessageResult;
import com.amazonaws.services.sqs.model.SendMessageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple example that publishes messages to a mock SQS queue created by the Gradle LocalStack Plugin and then listens to
 * the published messages and prints them to the console.
 */
public class SqsExampleApplication {
    private static final Logger LOG = LoggerFactory.getLogger(SqsExampleApplication.class);

    public static void main(String... args) throws Exception {
        final AmazonSQS sqsClient = AmazonSQSClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:4566", "us-east-1"))
                .build();

        final String queueUrl = sqsClient.getQueueUrl("example-queue").getQueueUrl();

        LOG.info("Resolved queue 'example-queue' to url: {}", queueUrl);

        for (int i = 1; i <= 5; i++) {
            final String messageText = "Example message " + i;

            LOG.info("Sending message to 'example-queue': {}", messageText);
            SendMessageResult sendMessageResult = sqsClient.sendMessage(queueUrl, messageText);
            LOG.info("Sent message: {}", sendMessageResult.getMessageId());
        }

        Thread.sleep(1_000);

        int receiveCnt = 0;
        while (receiveCnt < 5) {
            LOG.info("Receiving messages from 'example-queue'...");
            ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueUrl);
            receiveMessageRequest.setWaitTimeSeconds(20);

            final ReceiveMessageResult receiveMessageResult = sqsClient.receiveMessage(receiveMessageRequest);
            for (Message message : receiveMessageResult.getMessages()) {
                LOG.info("Received message: {}", message.getBody());
                sqsClient.deleteMessage(queueUrl, message.getReceiptHandle());
                receiveCnt++;
            }
        }
    }
}
