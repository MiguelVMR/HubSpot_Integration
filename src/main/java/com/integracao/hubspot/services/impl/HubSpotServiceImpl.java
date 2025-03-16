package com.integracao.hubspot.services.impl;

import com.integracao.hubspot.client.HubSpotHttpClient;
import com.integracao.hubspot.dtos.HubSpotResponse;
import com.integracao.hubspot.dtos.WebhookEventDTO;
import com.integracao.hubspot.infra.HubSpotTokenCache;
import com.integracao.hubspot.models.WebhookEventModel;
import com.integracao.hubspot.repository.WebhookEventRepository;
import com.integracao.hubspot.services.HubSpotService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * The Class HubSpotServiceImpl
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 14/03/2025
 */
@Service
public class HubSpotServiceImpl implements HubSpotService {

    private final HubSpotHttpClient hubSpotClient;
    private final HubSpotTokenCache hubSpotTokenCache;
    private final WebhookEventRepository webhookEventRepository;

    public HubSpotServiceImpl(HubSpotHttpClient hubSpotClient, HubSpotTokenCache hubSpotTokenCache, WebhookEventRepository webhookEventRepository) {
        this.hubSpotClient = hubSpotClient;
        this.hubSpotTokenCache = hubSpotTokenCache;
        this.webhookEventRepository = webhookEventRepository;
    }

    @Override
    public void geraTokenAcess(String code, String state) {
        HubSpotResponse hubSpotResponse = hubSpotClient.geraTokenAcess(code);
        hubSpotTokenCache.saveTokens(state, hubSpotResponse);
    }

    @Override
    public HubSpotResponse atualizaTokenAcess(String userId) {
        HubSpotResponse hubSpotCredencials = hubSpotTokenCache.getToken(userId);
        HubSpotResponse hubSpotResponse = hubSpotClient.atualizaTokenAcess(hubSpotCredencials);
        return hubSpotTokenCache.updateToken(userId, hubSpotResponse);
    }

    @Override
    public void saveWebhookData(List<WebhookEventDTO> data) {
        List<WebhookEventModel> webhookEventModels = new ArrayList<>();

        for (WebhookEventDTO webhookEventDTO : data) {
            WebhookEventModel webhookEventModel = new WebhookEventModel();
            BeanUtils.copyProperties(webhookEventDTO, webhookEventModel);
            webhookEventModels.add(webhookEventModel);
        }

        webhookEventRepository.saveAll(webhookEventModels);
    }

    @Override
    public List<WebhookEventDTO> visualizarDadosWebhookSalvos() {
        List<WebhookEventModel> webhookEventModels = webhookEventRepository.findAll();
        List<WebhookEventDTO> webhookEventDTOs = new ArrayList<>();
        for (WebhookEventModel webhookEventModel : webhookEventModels) {
            WebhookEventDTO webhookEventModelDTO = new WebhookEventDTO();
            BeanUtils.copyProperties(webhookEventModel, webhookEventModelDTO);
            webhookEventDTOs.add(webhookEventModelDTO);
        }

        return webhookEventDTOs;
    }


}
