package com.integracao.hubspot.dtos;

import java.util.List;

/**
 * The Record HubSpotResponseDTO
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 14/03/2025
 */
public record HubSpotResponseDTO(
        List<HubSpotContactWrapperDTO> results
) {
}