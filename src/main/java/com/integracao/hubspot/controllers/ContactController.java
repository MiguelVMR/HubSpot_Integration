package com.integracao.hubspot.controllers;

import com.integracao.hubspot.dtos.ContatoRecordDTO;
import com.integracao.hubspot.dtos.HubSpotContactPropertiesDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * The Interface ContactControllerInterface
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 14/03/2025
 */
@Tag(name = "Módulo de Contatos")
@RestController
@RequestMapping("/contacts")
public interface  ContactController {

    @Operation(summary = "Endpoint responsável por cadastrar um contato no HubSpot")
    @SecurityRequirement(name = "bearerAuth")
    @PostMapping("/create")
    ResponseEntity<Void> createContact(@RequestBody @Validated ContatoRecordDTO contactData, JwtAuthenticationToken jwtToken);

    @Operation(summary = "Endpoint responsável por listar os contato do HubSpot")
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping
    ResponseEntity<Page<HubSpotContactPropertiesDTO>> listContacts(JwtAuthenticationToken jwtToken, @RequestParam(value = "page", required = false) final Integer page,
                                                                   @RequestParam(value = "size", required = false) final Integer size);
}
