package br.com.compassuol.pb.challenge.msproducts.business.service.impl;

import br.com.compassuol.pb.challenge.msproducts.business.service.UserService;
import br.com.compassuol.pb.challenge.msproducts.model.entity.UserModel;
import br.com.compassuol.pb.challenge.msproducts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserModel addUser(UserModel newUser) {
        return this.userRepository.save(newUser);
    }

    @Override
    public UserModel getUserById(String id) {
        Optional<UserModel> search = this.userRepository.findById(id);

        return search.isPresent() ? search.get() : null;
    }

    @Override
    public UserModel updateUserById(String id, UserModel userToUpdate) {
        UserModel user = this.getUserById(id);

        if(user != null) {
            user.setFirstName(userToUpdate.getFirstName());
            user.setLastName(userToUpdate.getLastName());
            user.setEmail(userToUpdate.getEmail());
            user.setPassword(userToUpdate.getPassword());
            user.setRoles(userToUpdate.getRoles());

            return this.userRepository.save(user);
        }

        return null;
    }
}
