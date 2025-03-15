package com.integracao.hubspot.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * The Class HubSpotResponseRequest
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 13/03/2025
 */
@Data
public class HubSpotResponse {
    @JsonAlias("access_token")
    private String accessToken;
    @JsonAlias("refresh_token")
    private String refreshToken;
    private Instant tokenExpiration = Instant.now().plus(30, ChronoUnit.MINUTES);
}
