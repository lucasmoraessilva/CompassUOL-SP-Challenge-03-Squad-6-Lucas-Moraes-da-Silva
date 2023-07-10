package br.com.compassuol.pb.challenge.msproducts.message.broker.rabbitmq;

import br.com.compassuol.pb.challenge.msproducts.message.broker.UserMessageBroker;
import br.com.compassuol.pb.challenge.msproducts.model.entity.UserModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class UserRabbitMQBroker implements UserMessageBroker {

    private Logger logger;

    public UserRabbitMQBroker() {
        this.logger = LoggerFactory.getLogger(UserMessageBroker.class);
    }

    @Override
    public void emailCreatedUser(UserModel createdUser) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            byte[] userByte = mapper.writeValueAsBytes(createdUser);
            this.publishMessage("email-user", "new", userByte);
        } catch (JsonProcessingException exception) {
            this.logger.error("Unable to serialize UserModel to JSON.");
        }
    }

    @Override
    public void emailUpdatedUser(UserModel updatedUser) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            byte[] userByte = mapper.writeValueAsBytes(updatedUser);
            this.publishMessage("email-user", "update", userByte);
        } catch (JsonProcessingException exception) {
            this.logger.error("Unable to serialize UserModel to JSON.");
        }
    }

    @Override
    public void emailDeletedUser(UserModel deletedUser) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            byte[] userByte = mapper.writeValueAsBytes(deletedUser);
            this.publishMessage("email-user", "delete", userByte);
        } catch (JsonProcessingException exception) {
            this.logger.error("Unable to serialize UserModel to JSON.");
        }
    }

    private void publishMessage(String exchangeName, String key, byte[] message) {
        try {
            Connection connection = new ConnectionFactory().newConnection();
            Channel channel = connection.createChannel();

            channel.basicPublish(exchangeName, key, null, message);
        } catch (TimeoutException exception) {
            this.logger.error("RabbitMQ Server Timeout");
            this.logger.error("Failed to publish a message - Exchange: " + exchangeName + ", Key: " + key);
        } catch (IOException exception) {
            this.logger.error("Unexpected IO Exception. Failed to connect with RabbitMQ Server.");
            this.logger.error("IO Exception reason: " + exception.getMessage());
        }
    }
}
