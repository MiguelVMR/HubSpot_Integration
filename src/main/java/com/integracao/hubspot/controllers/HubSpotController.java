package com.integracao.hubspot.controllers;

import com.integracao.hubspot.configs.configModels.CustomModelConfig;
import com.integracao.hubspot.controllers.interfaces.HubSpotControllerInterface;
import com.integracao.hubspot.services.HubSpotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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

    public HubSpotController(CustomModelConfig customModelConfig, HubSpotService hubSpotService) {
        this.customModelConfig = customModelConfig;
        this.hubSpotService = hubSpotService;
    }

    @GetMapping("/authorize")
    public ResponseEntity<Map<String,String>> authorize(JwtAuthenticationToken jwtToken) {
        String authUrl = customModelConfig.getAutorizationUrl() + customModelConfig.getHubspotData().getClientId() +
                         "&redirect_uri=" + customModelConfig.getHubspotData().getRedirectUri() +
                         "&scope=" + customModelConfig.getScope() +
                         "&state=" + jwtToken.getName();
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("AutorizationURL",authUrl,"NGROK", customModelConfig.getNgrokURL()));
    }

    @GetMapping("/callback")
    public ResponseEntity<Void> handleOAuthCallback(@RequestParam("code") String code, @RequestParam("state") String state){
        hubSpotService.geraTokenAcess(code,state);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
