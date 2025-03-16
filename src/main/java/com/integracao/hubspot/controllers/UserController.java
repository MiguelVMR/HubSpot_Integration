package com.integracao.hubspot.controllers;

import com.integracao.hubspot.controllers.interfaces.UserControllerInterface;
import com.integracao.hubspot.dtos.CreateUserDTO;
import com.integracao.hubspot.services.UserService;
import com.integracao.hubspot.validations.UserValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
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
    private final UserValidator userValidator;

    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @PostMapping("/users")
    public ResponseEntity<Object> createUser(@RequestBody @Validated CreateUserDTO createUserDTO, Errors errors) {
        userValidator.validate(createUserDTO, errors);
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(createUserDTO));
    }
}
