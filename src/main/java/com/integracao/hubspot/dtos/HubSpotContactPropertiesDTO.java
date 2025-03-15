package com.integracao.hubspot.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

/**
* The Record HubSpotContactPropertiesDTO
*
* @author Miguel Vilela Moraes Ribeiro
* @since 14/03/2025
*/
public record HubSpotContactPropertiesDTO(
        @JsonProperty("email") String email,
        @JsonProperty("firstname") String firstName,
        @JsonProperty("lastname") String lastName,
        @JsonProperty("createdate") Instant createdAt,
        @JsonProperty("updatedAt") Instant updatedAt
) {
}
