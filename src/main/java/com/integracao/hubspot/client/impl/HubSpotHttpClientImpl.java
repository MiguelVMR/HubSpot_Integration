package com.integracao.hubspot.client.impl;

import com.integracao.hubspot.client.HubSpotHttpClient;
import com.integracao.hubspot.configs.configModels.CustomModelConfig;
import com.integracao.hubspot.dtos.HubSpotResponse;
import com.integracao.hubspot.exceptions.HubSpotIntegrationError;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;

/**
 * The Class HubSpotClient
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 14/03/2025
 */
@Component
final class HubSpotHttpClientImpl implements HubSpotHttpClient {
    private final CustomModelConfig customModelConfig;
    private final RestClient restClient;
    public HubSpotHttpClientImpl(CustomModelConfig customModelConfig, RestClient.Builder restClient) {
        this.customModelConfig = customModelConfig;
        this.restClient = restClient.build();
    }

    @Override
    public HubSpotResponse generateTokenAcess(String code){
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "authorization_code");
        requestBody.add("client_id", customModelConfig.getHubspotData().getClientId());
        requestBody.add("client_secret", customModelConfig.getHubspotData().getClientSecret());
        requestBody.add("redirect_uri", customModelConfig.getHubspotData().getRedirectUri());
        requestBody.add("code", code);

        try {
            return restClient.post()
                    .uri(customModelConfig.getTokenUrl())
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(requestBody)
                    .retrieve()
                    .body(HubSpotResponse.class);
        } catch (Exception e) {
            throw new HubSpotIntegrationError("Erro ao gerar TokenAcess: " + e.getMessage());
        }
    }

    @Override
    public HubSpotResponse updateTokenAcess(HubSpotResponse hubSpotCredencials){
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", "refresh_token");
        requestBody.add("client_id", customModelConfig.getHubspotData().getClientId());
        requestBody.add("client_secret", customModelConfig.getHubspotData().getClientId());
        requestBody.add("refresh_token", hubSpotCredencials.getRefreshToken());

        try {
            return restClient.post()
                    .uri(customModelConfig.getTokenUrl())
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .body(requestBody)
                    .retrieve()
                    .body(HubSpotResponse.class);
        } catch (Exception e) {
            throw new HubSpotIntegrationError("Erro ao atualizar TokenAcess: " + e.getMessage());
        }
    }
}
