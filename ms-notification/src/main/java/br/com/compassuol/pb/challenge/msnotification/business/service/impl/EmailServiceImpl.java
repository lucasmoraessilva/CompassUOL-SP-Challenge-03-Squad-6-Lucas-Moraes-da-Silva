package br.com.compassuol.pb.challenge.msnotification.business.service.impl;

import br.com.compassuol.pb.challenge.msnotification.business.service.EmailService;
import br.com.compassuol.pb.challenge.msnotification.model.entity.EmailModel;
import br.com.compassuol.pb.challenge.msnotification.model.entity.UserModel;
import br.com.compassuol.pb.challenge.msnotification.model.enums.StatusEmailEnum;
import br.com.compassuol.pb.challenge.msnotification.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class EmailServiceImpl implements EmailService {

    private JavaMailSender mailSender;
    private EmailRepository emailRepository;
    private Environment environment;

    @Autowired
    public EmailServiceImpl(JavaMailSender mailSender, EmailRepository emailRepository, Environment environment) {
        this.mailSender = mailSender;
        this.emailRepository = emailRepository;
        this.environment = environment;
    }

    private void sendEmail(EmailModel emailDetails) {
        try {
            SimpleMailMessage emailMessage = new SimpleMailMessage();
            emailMessage.setFrom(environment.getProperty("mail-from"));
            emailMessage.setTo(emailDetails.getEmailTo());
            emailMessage.setSubject(emailDetails.getSubject());
            emailMessage.setText(emailDetails.getBodyText());

            this.mailSender.send(emailMessage);

            emailDetails.setStatus(StatusEmailEnum.SENT);
        } catch (MailException exception) {
            emailDetails.setStatus(StatusEmailEnum.ERROR);
        }
    }

    @Override
    public void sendEmailCreatedUser(UserModel userDetails) {
        EmailModel email = new EmailModel();
        email.setDate(Instant.now());
        email.setEmailTo(userDetails.getEmail());
        email.setSubject("Welcome to Challenge 3 System");
        email.setBodyText("Welcome " + userDetails.getFirstName() + "! \n\nYou have been registered in the Challenge 3 System.");

        this.sendEmail(email);

        this.emailRepository.save(email);
    }

    @Override
    public void sendEmailUpdatedUser(UserModel userDetails) {
        EmailModel email = new EmailModel();
        email.setDate(Instant.now());
        email.setEmailTo(userDetails.getEmail());
        email.setSubject("Your data has been updated");
        email.setBodyText("Hello " + userDetails.getFirstName() + "! \n\nYour data has been updated in our system.");

        this.sendEmail(email);

        this.emailRepository.save(email);
    }

    @Override
    public void sendEmailDeletedUser(UserModel userDetails) {
        EmailModel email = new EmailModel();
        email.setDate(Instant.now());
        email.setEmailTo(userDetails.getEmail());
        email.setSubject("Your data has been deleted");
        email.setBodyText("Hello " + userDetails.getFirstName() + "! \n\nYour data has been deleted in our system.");

        this.sendEmail(email);

        this.emailRepository.save(email);
    }
}
