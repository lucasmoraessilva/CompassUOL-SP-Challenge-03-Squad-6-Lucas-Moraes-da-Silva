package br.com.compassuol.pb.challenge.msnotification.repository;

import br.com.compassuol.pb.challenge.msnotification.model.entity.EmailModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<EmailModel, Integer> {
}
