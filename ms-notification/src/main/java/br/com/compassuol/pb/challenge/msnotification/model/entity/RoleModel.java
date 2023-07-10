package br.com.compassuol.pb.challenge.msnotification.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleModel {
    private Integer id;
    private String name;
    private Set<UserModel> users;
}
