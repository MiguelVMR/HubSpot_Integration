package com.integracao.hubspot.controllers;

import com.integracao.hubspot.dtos.LoginRequestRecordDTO;
import com.integracao.hubspot.dtos.LoginResponseRecordDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Interface AuthControllerInterface
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 15/03/2025
 */
@Tag(name = "Módulo de login do sistema")
@RestController
public interface AuthController {

    @Operation(summary = "Endpoint que faz o login do usuário no sistema")
    @PostMapping("/login")
    ResponseEntity<LoginResponseRecordDTO> login(@RequestBody LoginRequestRecordDTO loginRequest);
}
