package com.integracao.hubspot.controllers.interfaces;

import com.integracao.hubspot.dtos.ContatoRecordDTO;
import com.integracao.hubspot.dtos.HubSpotContactPropertiesDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * The Interface ContactControllerInterface
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 14/03/2025
 */
@Tag(name = "Módulo de Contatos")
public interface ContactControllerInterface {

    @Operation(summary = "Endpoint responsável por cadastrar um contato no HubSpot")
    @SecurityRequirement(name = "bearerAuth")
    ResponseEntity<Void> createContact(@RequestBody ContatoRecordDTO contactData, JwtAuthenticationToken jwtToken);

    @Operation(summary = "Endpoint responsável por listar os contato do HubSpot")
    @SecurityRequirement(name = "bearerAuth")
    ResponseEntity<List<HubSpotContactPropertiesDTO>> listContacts(JwtAuthenticationToken jwtToken);
}
