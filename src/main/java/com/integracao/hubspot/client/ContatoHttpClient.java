package com.integracao.hubspot.client;

import com.integracao.hubspot.configs.configModels.CustomModelConfig;
import com.integracao.hubspot.dtos.*;
import com.integracao.hubspot.exceptions.HubSpotIntegrationError;
import com.integracao.hubspot.infra.HubSpotTokenCache;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

/**
 * The Class ContatoClient
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 14/03/2025
 */
@Component
public class ContatoHttpClient {

    private final CustomModelConfig customModelConfig;
    private final RestClient restClient;
    private final HubSpotTokenCache hubSpotTokenCache;

    public ContatoHttpClient(CustomModelConfig customModelConfig, RestClient.Builder restClient, HubSpotTokenCache hubSpotTokenCache) {
        this.customModelConfig = customModelConfig;
        this.restClient = restClient.build();
        this.hubSpotTokenCache = hubSpotTokenCache;
    }

    public void createContact(ContatoRecordDTO contactData){
        try {
            HubSpotResponse tokens = hubSpotTokenCache.getToken("123456");

            restClient.post()
                    .uri(customModelConfig.getContatoUrl())
                    .contentType(MediaType.APPLICATION_JSON)
                    .header("Authorization", "Bearer " + tokens.getAccessToken())
                    .body(contactData)
                    .retrieve()
                    .toBodilessEntity();
        } catch (Exception e) {
            throw new HubSpotIntegrationError("Erro ao Criar contato no HubSpot " + e.getMessage());
        }
    }

    public List<HubSpotContactPropertiesDTO> findAllContatos() {
        try {
            HubSpotResponse tokens = hubSpotTokenCache.getToken("123456");

            HubSpotResponseDTO response = restClient.get()
                    .uri(customModelConfig.getContatoUrl())
                    .header("Authorization", "Bearer " + tokens.getAccessToken())
                    .retrieve()
                    .body(HubSpotResponseDTO.class);

            return response.results().stream()
                    .map(HubSpotContactWrapperDTO::properties)
                    .toList();
        } catch (Exception e) {
            throw new HubSpotIntegrationError("Erro ao Buscar contatos no HubSpot " + e.getMessage());
        }
    }
}
