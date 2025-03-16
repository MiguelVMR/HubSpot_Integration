package com.integracao.hubspot.controllers.impl;

import com.integracao.hubspot.controllers.UserController;
import com.integracao.hubspot.dtos.CreateUserDTO;
import com.integracao.hubspot.services.UserService;
import com.integracao.hubspot.validations.UserValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class UserController
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 15/03/2025
 */
@RestController
final class UserControllerImpl implements UserController {
    private final UserService userService;
    private final UserValidator userValidator;

    public UserControllerImpl(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @Override
    public ResponseEntity<Object> createUser(@RequestBody @Validated CreateUserDTO createUserDTO, Errors errors) {
        userValidator.validate(createUserDTO, errors);
        if(errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(createUserDTO));
    }
}
