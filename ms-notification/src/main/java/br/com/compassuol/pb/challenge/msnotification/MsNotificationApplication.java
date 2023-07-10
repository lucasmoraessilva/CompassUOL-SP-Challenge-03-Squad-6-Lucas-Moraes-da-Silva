package br.com.compassuol.pb.challenge.msnotification;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsNotificationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsNotificationApplication.class, args);
		//MsNotificationApplication.oia();
	}

	/*public static void oia() {
		UserMessageBrokerListener listener = new UserRabbitMQBrokerListener();
		listener.emailCreatedUser();
	}*/
}
