package com.integracao.hubspot.controllers;

import com.integracao.hubspot.configs.configModels.CustomModelConfig;
import com.integracao.hubspot.controllers.interfaces.HubSpotControllerInterface;
import com.integracao.hubspot.dtos.WebhookEventDTO;
import com.integracao.hubspot.services.HubSpotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;

/**
 * The Class HubSpotController
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 14/03/2025
 */
@RestController
@RequestMapping("/oauth")
public class HubSpotController implements HubSpotControllerInterface {
    private final CustomModelConfig customModelConfig;
    private final HubSpotService hubSpotService;
    private final RestClient restClient;

    public HubSpotController(CustomModelConfig customModelConfig, HubSpotService hubSpotService, RestClient.Builder restClient) {
        this.customModelConfig = customModelConfig;
        this.hubSpotService = hubSpotService;
        this.restClient = restClient.build();
    }

    @GetMapping("/authorize")
    public ResponseEntity<Map<String, String>> authorize(JwtAuthenticationToken jwtToken) {
        String authUrl = customModelConfig.getAutorizationUrl() + customModelConfig.getHubspotData().getClientId() +
                         "&redirect_uri=" + customModelConfig.getHubspotData().getRedirectUri() +
                         "&scope=" + customModelConfig.getScope() +
                         "&state=" + jwtToken.getName();
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("AutorizationURL", authUrl));
    }

    //Metodo que recebe code do hubspot e gerar o acess e o refresh token
    @GetMapping("/callback")
    public ResponseEntity<Void> handleOAuthCallback(@RequestParam("code") String code, @RequestParam("state") String state) {
        hubSpotService.geraTokenAcess(code, state);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //Metodo que recebe eventos de contact-create do hubspot
    @PostMapping("webhook-contact-create-data")
    public void createWebhookData(@RequestBody List<WebhookEventDTO> data) {
        System.out.println("INDO SALVAR DADOS WEBHOOK");
        hubSpotService.saveWebhookData(data);
    }

    @GetMapping("webhook-contact-create-data")
    public ResponseEntity<List<WebhookEventDTO>> visualizarDadosWebhookSalvos() {
        return ResponseEntity.status(HttpStatus.OK).body(hubSpotService.visualizarDadosWebhookSalvos());
    }
}
