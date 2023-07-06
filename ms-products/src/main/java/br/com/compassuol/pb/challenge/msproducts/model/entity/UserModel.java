package br.com.compassuol.pb.challenge.msproducts.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity(name = "tb_user")
@AllArgsConstructor
public class UserModel {

    @Id
    private String id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @ManyToMany
    @JoinTable(name = "tb_user_role")
    private Set<RoleModel> roles;

    public UserModel() {
        this.id = UUID.randomUUID().toString();
    }
}
