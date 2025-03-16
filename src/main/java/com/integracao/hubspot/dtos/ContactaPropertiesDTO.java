package com.integracao.hubspot.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * The record ContactaProperties
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 14/03/2025
 */
public record ContactaPropertiesDTO(
        @Email(message = "Email must be in the expected format")
        @NotBlank(message = "Email is mandatory")
        String email,
        @NotBlank(message = "FirstName is mandatory")
        String firstname,
        @NotBlank(message = "Last is mandatory")
        String lastname
){
}
