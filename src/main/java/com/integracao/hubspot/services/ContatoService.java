package com.integracao.hubspot.services;

import com.integracao.hubspot.dtos.ContatoRecordDTO;
import com.integracao.hubspot.dtos.HubSpotContactPropertiesDTO;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;

/**
 * The Interface ContatoService
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 14/03/2025
 */
public interface ContatoService {

    void createContact(ContatoRecordDTO contactData, JwtAuthenticationToken jwtToken);

    List<HubSpotContactPropertiesDTO> findAllContacts(JwtAuthenticationToken jwtToken);
}
