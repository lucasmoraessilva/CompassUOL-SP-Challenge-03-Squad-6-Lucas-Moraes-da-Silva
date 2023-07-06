package br.com.compassuol.pb.challenge.msproducts.controller;

import br.com.compassuol.pb.challenge.msproducts.business.service.UserService;
import br.com.compassuol.pb.challenge.msproducts.model.entity.ProductModel;
import br.com.compassuol.pb.challenge.msproducts.model.entity.UserModel;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserModel> getUserById(@PathVariable String userId) {
        return ResponseEntity.ok(this.userService.getUserById(userId));
    }

    @PostMapping
    public ResponseEntity<UserModel> addUser(@RequestBody UserModel newUser, HttpServletRequest request) throws URISyntaxException {
        UserModel addedUser = this.userService.addUser(newUser);

        return ResponseEntity.created(new URI(request.getRequestURI() + addedUser.getId())).body(addedUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserModel> updateUserById(@PathVariable String userId, @RequestBody UserModel userToUpdate) {
        UserModel updatedUser = this.userService.updateUserById(userId, userToUpdate);

        if (updatedUser != null) {
            return ResponseEntity.ok(updatedUser);
        }

        return ResponseEntity.notFound().build();
    }
}
