package com.integracao.hubspot.controllers.impl;

import com.integracao.hubspot.controllers.AuthController;
import com.integracao.hubspot.dtos.LoginRequestRecordDTO;
import com.integracao.hubspot.dtos.LoginResponseRecordDTO;
import com.integracao.hubspot.services.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class TokenController
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 15/03/2025
 */
@RestController
final class AuthControllerImpl implements AuthController {

    private final AuthService authService;

    public AuthControllerImpl(AuthService authService) {
        this.authService = authService;
    }

    @Override
    public ResponseEntity<LoginResponseRecordDTO> login(LoginRequestRecordDTO loginRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginRequest));
    }
}
