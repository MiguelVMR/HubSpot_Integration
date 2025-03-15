package com.integracao.hubspot.controllers;

import com.integracao.hubspot.controllers.interfaces.UserControllerInterface;
import com.integracao.hubspot.dtos.CreateUserDTO;
import com.integracao.hubspot.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class UserController
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 15/03/2025
 */
@RestController
public class UserController implements UserControllerInterface {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDTO createUserDTO) {
        userService.createUser(createUserDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
