package br.com.compassuol.pb.challenge.msnotification.message.impl;

import br.com.compassuol.pb.challenge.msnotification.business.service.EmailService;
import br.com.compassuol.pb.challenge.msnotification.message.UserMessageListener;
import br.com.compassuol.pb.challenge.msnotification.model.entity.UserModel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

@Component
public class UserRabbitMQListener implements UserMessageListener {

    private EmailService emailService;

    @Autowired
    public UserRabbitMQListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "email-new-user-queue")
    @Override
    public void createdUser(String userInJsonFormat) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            UserModel createdUser = mapper.readValue(userInJsonFormat, UserModel.class);

            this.emailService.sendEmailCreatedUser(createdUser);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @RabbitListener(queues = "email-update-user-queue")
    @Override
    public void updatedUser(String userInJsonFormat) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            UserModel updatedUser = mapper.readValue(userInJsonFormat, UserModel.class);

            this.emailService.sendEmailUpdatedUser(updatedUser);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @RabbitListener(queues = "email-delete-user-queue")
    @Override
    public void deletedUser(String userInJsonFormat) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            UserModel deletedUser = mapper.readValue(userInJsonFormat, UserModel.class);

            this.emailService.sendEmailDeletedUser(deletedUser);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
