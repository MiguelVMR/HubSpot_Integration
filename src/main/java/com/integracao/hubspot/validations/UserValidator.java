package com.integracao.hubspot.validations;

import com.integracao.hubspot.dtos.CreateUserDTO;
import com.integracao.hubspot.services.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * The Class UserValidator
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 16/03/2025
 */
@Component
public class UserValidator implements Validator {
    private final Validator validator;
    final UserService userService;

    public UserValidator(@Qualifier("defaultValidator") Validator validator, UserService userService) {
        this.validator = validator;
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        CreateUserDTO userRecordDto = (CreateUserDTO) o;
        validator.validate(userRecordDto, errors);
        if(!errors.hasErrors()){
            validateUsername(userRecordDto, errors);
        }
    }

    private void validateUsername(CreateUserDTO createUserRecordDTO, Errors errors){
        if(userService.existsByUsername(createUserRecordDTO.username())) {
            errors.rejectValue("username", "usernameConflict", "Error: Username is Already Taken!");
        }
    }
}
