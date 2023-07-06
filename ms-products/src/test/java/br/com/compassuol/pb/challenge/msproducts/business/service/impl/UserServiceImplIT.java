package br.com.compassuol.pb.challenge.msproducts.business.service.impl;

import br.com.compassuol.pb.challenge.msproducts.model.entity.UserModel;
import br.com.compassuol.pb.challenge.msproducts.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplIT {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private UserModel user;

    @BeforeEach
    void setUp() {
        this.user = new UserModel();
        this.user.setFirstName("Lucas");
        this.user.setLastName("Silva");
        this.user.setEmail("lucas@gmail.com");
        this.user.setPassword("12345");
        this.user.setRoles(Set.of(1));
    }

    @Test
    void addUserTest() {
        this.userService.addUser(this.user);

        verify(this.userRepository).save(this.user);
    }

    @Test
    void getUserByIdTest() {
        when(this.userRepository.findById(this.user.getId())).thenReturn(Optional.of(this.user));
        UserModel foundUser = this.userService.getUserById(this.user.getId());

        verify(this.userRepository).findById(this.user.getId());
        assertEquals(this.user, foundUser);
    }

    @Test
    void updateUserByIdTest() {
        when(this.userRepository.findById(this.user.getId())).thenReturn(Optional.of(this.user));
        when(this.userRepository.save(this.user)).thenReturn(this.user);

        this.user.setFirstName("Leandro");
        this.user.setLastName("Cabral");
        this.user.setEmail("le.cabral@gmail.com");
        this.user.setPassword("54321");
        this.user.setRoles(Set.of(1));
        UserModel updatedUser = this.userService.updateUserById(this.user.getId(), this.user);

        verify(this.userRepository).findById(this.user.getId());
        assertEquals(this.user, updatedUser);
    }
}