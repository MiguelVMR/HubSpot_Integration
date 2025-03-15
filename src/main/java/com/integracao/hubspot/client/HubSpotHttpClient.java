package com.integracao.hubspot.client;

import com.integracao.hubspot.configs.configModels.CustomModelConfig;
import com.integracao.hubspot.dtos.HubSpotResponse;
import com.integracao.hubspot.exceptions.HubSpotIntegrationError;
import com.integracao.hubspot.infra.HubSpotTokenCache;
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
public class HubSpotHttpClient {
    private final CustomModelConfig customModelConfig;
    private final RestClient restClient;
    public HubSpotHttpClient(CustomModelConfig customModelConfig, RestClient.Builder restClient) {
        this.customModelConfig = customModelConfig;
        this.restClient = restClient.build();
    }

    public HubSpotResponse geraTokenAcess(String code){
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

    public HubSpotResponse atualizaTokenAcess(HubSpotResponse hubSpotCredencials){
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
