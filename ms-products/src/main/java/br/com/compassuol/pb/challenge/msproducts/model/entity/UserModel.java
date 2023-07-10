package br.com.compassuol.pb.challenge.msproducts.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Check;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="tb_user")
@AllArgsConstructor
public class UserModel {

    @Id
    @Column(name="id", length=36, nullable=false)
    private String id;

    @Column(name="first_name", length=20, nullable = false)
    @Check(constraints="LENGTH(first_name)>=2")
    private String firstName;

    @Column(name="last_name", length=25, nullable=false)
    @Check(constraints="LENGTH(last_name)>=2")
    private String lastName;

    @Column(name="email", length=70, nullable=false, unique=true)
    @Check(constraints="LENGTH(email)>=8")
    private String email;

    @Column(name="password", length=50, nullable=false)
    @Check(constraints="LENGTH(password)>=20")
    private String password;

    @ManyToMany
    @JoinTable(name="tb_user_role", joinColumns=@JoinColumn(name="user_id", columnDefinition="VARCHAR(36)"), inverseJoinColumns=@JoinColumn(name="role_id", columnDefinition="INT"))
    private Set<RoleModel> roles;

    public UserModel() {
        this.id = UUID.randomUUID().toString();
    }
}
