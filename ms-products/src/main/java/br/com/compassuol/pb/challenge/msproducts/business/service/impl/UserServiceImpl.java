package br.com.compassuol.pb.challenge.msproducts.business.service.impl;

import br.com.compassuol.pb.challenge.msproducts.business.service.UserService;
import br.com.compassuol.pb.challenge.msproducts.message.broker.UserMessageBroker;
import br.com.compassuol.pb.challenge.msproducts.model.entity.UserModel;
import br.com.compassuol.pb.challenge.msproducts.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserMessageBroker userMessageBroker;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMessageBroker userMessageBroker) {
        this.userRepository = userRepository;
        this.userMessageBroker = userMessageBroker;
    }

    @Override
    public UserModel addUser(UserModel newUser) {
        UserModel savedUser = this.userRepository.save(newUser);

        this.userMessageBroker.emailCreatedUser(savedUser);

        return savedUser;
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

            this.userMessageBroker.emailUpdatedUser(user);

            return this.userRepository.save(user);
        }

        return null;
    }
}
