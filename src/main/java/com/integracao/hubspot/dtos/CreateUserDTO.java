package com.integracao.hubspot.dtos;

import com.integracao.hubspot.validations.PasswordConstraint;
import jakarta.validation.constraints.NotBlank;

/**
* The Record CreateUserDTO
*
* @author Miguel Vilela Moraes Ribeiro
* @since 15/03/2025
*/
public record CreateUserDTO(
        @NotBlank(message = "Username is mandatory")
        String username,
        @PasswordConstraint
        String password) {
}
