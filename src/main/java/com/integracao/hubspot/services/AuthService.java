package com.integracao.hubspot.services;

import com.integracao.hubspot.dtos.LoginRequestRecordDTO;
import com.integracao.hubspot.dtos.LoginResponseRecordDTO;

/**
 * The Interface AuthService
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 15/03/2025
 */
public interface AuthService {
    LoginResponseRecordDTO login(LoginRequestRecordDTO loginRequest);
}
