package com.integracao.hubspot.client;

import com.integracao.hubspot.dtos.HubSpotResponse;

/**
 * The Interface HubSpotHttpClient
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 16/03/2025
 */
public interface HubSpotHttpClient {
    HubSpotResponse generateTokenAcess(String code);
    HubSpotResponse updateTokenAcess(HubSpotResponse hubSpotCredencials);
}
