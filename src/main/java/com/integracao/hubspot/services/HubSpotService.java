package com.integracao.hubspot.services;


import com.integracao.hubspot.dtos.HubSpotResponse;
import com.integracao.hubspot.dtos.WebhookEventDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * The Interface HubSpotService
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 14/03/2025
 */
public interface HubSpotService {
    void geraTokenAcess(String code, String state);
    HubSpotResponse atualizaTokenAcess(String userId);
    void saveWebhookData(List<WebhookEventDTO> data);
    List<WebhookEventDTO> visualizarDadosWebhookSalvos();
}
