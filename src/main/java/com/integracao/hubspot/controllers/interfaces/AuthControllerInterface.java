package com.integracao.hubspot.controllers.interfaces;

import com.integracao.hubspot.dtos.LoginRequestRecordDTO;
import com.integracao.hubspot.dtos.LoginResponseRecordDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * The Interface AuthControllerInterface
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 15/03/2025
 */
@Tag(name = "Módulo de login do sistema")
public interface AuthControllerInterface {

    @Operation(summary = "Endpoint que faz o login do usuário no sistema")
    ResponseEntity<LoginResponseRecordDTO> login(@RequestBody LoginRequestRecordDTO loginRequest);
}
