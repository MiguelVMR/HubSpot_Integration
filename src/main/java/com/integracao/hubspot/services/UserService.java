package com.integracao.hubspot.services;

import com.integracao.hubspot.dtos.CreateUserDTO;
import com.integracao.hubspot.models.UserModel;

/**
* The Interface UserService
*
* @author Miguel Vilela Moraes Ribeiro
* @since 15/03/2025
*/
public interface UserService {
    UserModel createUser(CreateUserDTO createUserDTO);

    boolean existsByUsername(String username);

}
