package br.com.compassuol.pb.challenge.msproducts.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="tb_role")
@AllArgsConstructor
@NoArgsConstructor
public class RoleModel {

    @Id
    @Column(name="id", nullable=false, columnDefinition="INT AUTO_INCREMENT")
    private Integer id;

    @Column(name="name", length=20, nullable=false, unique=true)
    @Check(constraints="LENGTH(name)>=6")
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<UserModel> users;
}
