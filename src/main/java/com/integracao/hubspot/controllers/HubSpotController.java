package com.integracao.hubspot.controllers;

import com.integracao.hubspot.dtos.WebhookEventDTO;
import com.integracao.hubspot.models.WebhookEventModel;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * The Interface HubSpotControllerInterface
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 14/03/2025
 */
@Tag(name = "Modulo de Integração com o HubSpot")
@RestController
@RequestMapping("/hubspot")
public interface HubSpotController {
    @Operation(summary = "Método que retorna a URL para iniciar fluxo OAuth com o HubSpot.")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/authorize")
    ResponseEntity<Map<String,String>> authorize(JwtAuthenticationToken jwtToken);

    @Hidden
    @GetMapping("/callback")
    ResponseEntity<Void> handleOAuthCallback(@RequestParam("code") String code,@RequestParam("state") String state);

    @Hidden
    @PostMapping("webhook-contact-create-data")
    void createWebhookData(@RequestBody List<WebhookEventDTO> data);

    @Operation(summary = "Método que retorna os dados coletados pelo webhook contact.creation")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("webhook-contact-create-data")
    ResponseEntity<Page<WebhookEventModel>> viewDataWebhookSaved(@RequestParam(value = "page", required = false)final Integer page,
                                                                 @RequestParam(value = "size", required = false) final Integer size);
}
