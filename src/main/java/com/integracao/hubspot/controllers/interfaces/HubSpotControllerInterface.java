package com.integracao.hubspot.controllers.interfaces;

import com.integracao.hubspot.dtos.WebhookEventDTO;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * The Interface HubSpotControllerInterface
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 14/03/2025
 */
@Tag(name = "Modulo de Integração com o HubSpot")
public interface HubSpotControllerInterface {
    @Operation(summary = "Método que retorna a URL para iniciar fluxo OAuth com o HubSpot.")
    @SecurityRequirement(name = "bearerAuth")
    ResponseEntity<Map<String,String>> authorize(JwtAuthenticationToken jwtToken);

    @Hidden
    ResponseEntity<Void> handleOAuthCallback(@RequestParam("code") String code,@RequestParam("state") String state);

    @Hidden
    void createWebhookData(@RequestBody List<WebhookEventDTO> data);

    @Operation(summary = "Método que retorna os dados coletados pelo webhook contact.creation")
    @SecurityRequirement(name = "bearerAuth")
    ResponseEntity<List<WebhookEventDTO>> visualizarDadosWebhookSalvos();
}
