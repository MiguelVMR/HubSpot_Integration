package com.integracao.hubspot.controllers.impl;

import com.integracao.hubspot.configs.configModels.CustomModelConfig;
import com.integracao.hubspot.controllers.HubSpotController;
import com.integracao.hubspot.dtos.WebhookEventDTO;
import com.integracao.hubspot.models.WebhookEventModel;
import com.integracao.hubspot.services.HubSpotService;
import com.integracao.hubspot.utils.CustomPageable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * The Class HubSpotController
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 14/03/2025
 */
@RestController
final class HubSpotControllerImpl implements HubSpotController {
    private final CustomModelConfig customModelConfig;
    private final HubSpotService hubSpotService;

    public HubSpotControllerImpl(CustomModelConfig customModelConfig, HubSpotService hubSpotService) {
        this.customModelConfig = customModelConfig;
        this.hubSpotService = hubSpotService;
    }

    @Override
    public ResponseEntity<Map<String, String>> authorize(JwtAuthenticationToken jwtToken) {
        String authUrl = customModelConfig.getAutorizationUrl() + customModelConfig.getHubspotData().getClientId() +
                         "&redirect_uri=" + customModelConfig.getHubspotData().getRedirectUri() +
                         "&scope=" + customModelConfig.getScope() +
                         "&state=" + jwtToken.getName();
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("AutorizationURL", authUrl));
    }

    //Metodo que recebe code do hubspot e gerar o acess e o refresh token
    @Override
    public ResponseEntity<Void> handleOAuthCallback(String code, String state) {
        hubSpotService.generateTokenAcess(code, state);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //Metodo que recebe eventos de contact-create do hubspot
    @Override
    public void createWebhookData(List<WebhookEventDTO> data) {
        hubSpotService.saveWebhookData(data);
    }

    @Override
    public ResponseEntity<Page<WebhookEventModel>> viewDataWebhookSaved(Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK).body(hubSpotService.viewDataWebhookSaved(CustomPageable.getInstance(page,size)));
    }
}
