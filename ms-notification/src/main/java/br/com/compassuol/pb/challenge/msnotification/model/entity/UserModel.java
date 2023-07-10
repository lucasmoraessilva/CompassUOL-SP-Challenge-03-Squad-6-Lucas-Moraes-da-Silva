package br.com.compassuol.pb.challenge.msnotification.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserModel {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Set<RoleModel> roles;
}
