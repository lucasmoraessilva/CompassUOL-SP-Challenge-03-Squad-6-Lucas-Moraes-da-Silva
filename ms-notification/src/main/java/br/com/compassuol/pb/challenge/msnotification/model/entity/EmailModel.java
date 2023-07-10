package br.com.compassuol.pb.challenge.msnotification.model.entity;

import br.com.compassuol.pb.challenge.msnotification.model.enums.StatusEmailEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_email")
public class EmailModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    private String emailTo;
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String bodyText;

    @Column(columnDefinition = "DATETIME")
    private Instant date;
    private StatusEmailEnum status;
}
