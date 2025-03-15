package com.integracao.hubspot.services;

import com.integracao.hubspot.dtos.CreateUserDTO;

/**
* The Interface UserService
*
* @author Miguel Vilela Moraes Ribeiro
* @since 15/03/2025
*/
public interface UserService {
    void createUser(CreateUserDTO createUserDTO);
}
