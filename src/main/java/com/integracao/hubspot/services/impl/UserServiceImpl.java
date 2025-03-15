package com.integracao.hubspot.services.impl;

import com.integracao.hubspot.dtos.CreateUserDTO;
import com.integracao.hubspot.exceptions.UserException;
import com.integracao.hubspot.models.RolesModel;
import com.integracao.hubspot.models.UserModel;
import com.integracao.hubspot.repository.RoleRepository;
import com.integracao.hubspot.repository.UserRepository;
import com.integracao.hubspot.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * The Class UserServiceImpl
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 15/03/2025
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void createUser(CreateUserDTO createUserDTO) {
        var role = roleRepository.findByName(RolesModel.Values.BASIC.name().toLowerCase());
        var userDB = userRepository.findByUsername(createUserDTO.username());
        if (userDB.isPresent()) {
            throw new UserException("Username already exists");
        }
        var user =  new UserModel();
        user.setUsername(createUserDTO.username());
        user.setPassword(passwordEncoder.encode(createUserDTO.password()));
        user.setRoles(Set.of(role));
        userRepository.save(user);
    }
}
