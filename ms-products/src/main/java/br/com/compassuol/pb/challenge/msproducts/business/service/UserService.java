package br.com.compassuol.pb.challenge.msproducts.business.service;

import br.com.compassuol.pb.challenge.msproducts.model.entity.UserModel;

public interface UserService {
    public UserModel addUser(UserModel newUser);
    public UserModel getUserById(String id);
    public UserModel updateUserById(String id, UserModel userToUpdate);
}
