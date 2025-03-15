package com.integracao.hubspot.services.impl;

import com.integracao.hubspot.dtos.LoginRequestRecordDTO;
import com.integracao.hubspot.dtos.LoginResponseRecordDTO;
import com.integracao.hubspot.exceptions.LoginException;
import com.integracao.hubspot.models.RolesModel;
import com.integracao.hubspot.repository.UserRepository;
import com.integracao.hubspot.services.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.stream.Collectors;

/**
 * The Class AuthServiceImpl
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 15/03/2025
 */
@Service
public class AuthServiceImpl implements AuthService {
    private final JwtEncoder jwtEncoder;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(JwtEncoder jwtEncoder, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public LoginResponseRecordDTO login(LoginRequestRecordDTO loginRequest) {
        var user = userRepository.findByUsername(loginRequest.username());
        if(user.isEmpty() || !user.get().isLoginCorrect(loginRequest, passwordEncoder)) {
            throw new LoginException("Username or password incorrect");
        }
        var now = Instant.now();

        var scopes = user.get().getRoles()
                .stream()
                .map(RolesModel::getName)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("hubspotIntegrationAPI")
                .subject(user.get().getUserId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(300L))
                .claim("scope", scopes)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new LoginResponseRecordDTO(jwtValue,300L);
    }
}
