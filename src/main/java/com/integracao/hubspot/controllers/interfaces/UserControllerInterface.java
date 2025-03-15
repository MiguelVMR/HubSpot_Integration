package com.integracao.hubspot.controllers.interfaces;

import com.integracao.hubspot.dtos.CreateUserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * The Interface UserController
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 15/03/2025
 */
@Tag(name = "Módulo de Usuarios do Sistema")
public interface UserControllerInterface {

    @Operation(summary = "Método que cria um usuário no sistema")
    ResponseEntity<Void> createUser(@RequestBody CreateUserDTO createUserDTO);
}
