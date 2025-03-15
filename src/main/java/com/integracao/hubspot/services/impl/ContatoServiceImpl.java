package com.integracao.hubspot.services.impl;

import com.integracao.hubspot.client.ContatoHttpClient;
import com.integracao.hubspot.dtos.ContatoRecordDTO;
import com.integracao.hubspot.dtos.HubSpotContactPropertiesDTO;
import com.integracao.hubspot.dtos.HubSpotResponse;
import com.integracao.hubspot.infra.HubSpotTokenCache;
import com.integracao.hubspot.services.ContatoService;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The Class ContatoService
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 14/03/2025
 */
@Service
public class ContatoServiceImpl implements ContatoService {
    private final ContatoHttpClient contatoClient;
    private final HubSpotTokenCache hubSpotTokenCache;

    public ContatoServiceImpl(ContatoHttpClient contatoClient, HubSpotTokenCache hubSpotTokenCache) {
        this.contatoClient = contatoClient;
        this.hubSpotTokenCache = hubSpotTokenCache;
    }

    @Override
    public void createContact(ContatoRecordDTO contactData, JwtAuthenticationToken jwtToken) {
        HubSpotResponse token = hubSpotTokenCache.getToken(jwtToken.getName());
        //Vaildaçoes
        contatoClient.createContact(contactData,token.getAccessToken());
    }

    @Override
    public List<HubSpotContactPropertiesDTO> findAllContacts(JwtAuthenticationToken jwtToken) {
        HubSpotResponse token = hubSpotTokenCache.getToken(jwtToken.getName());
        //Vaildaçoes
        return contatoClient.findAllContatos(token.getAccessToken());
    }
}
