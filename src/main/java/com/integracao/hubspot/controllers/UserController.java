package com.integracao.hubspot.controllers;

import com.integracao.hubspot.dtos.CreateUserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Interface UserController
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 15/03/2025
 */
@Tag(name = "Módulo de Usuarios do Sistema")
@RestController
public interface UserController {

    @Operation(summary = "Método que cria um usuário no sistema")
    @PostMapping("/users")
    ResponseEntity<Object> createUser(@RequestBody CreateUserDTO createUserDTO, Errors errors);
}
