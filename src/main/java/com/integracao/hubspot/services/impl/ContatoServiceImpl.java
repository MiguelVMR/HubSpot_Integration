package com.integracao.hubspot.services.impl;

import com.integracao.hubspot.client.ContatoHttpClient;
import com.integracao.hubspot.dtos.ContatoRecordDTO;
import com.integracao.hubspot.dtos.HubSpotContactPropertiesDTO;
import com.integracao.hubspot.dtos.HubSpotResponse;
import com.integracao.hubspot.exceptions.NotFoundException;
import com.integracao.hubspot.infra.HubSpotTokenCache;
import com.integracao.hubspot.services.ContatoService;
import com.integracao.hubspot.services.HubSpotService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

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
    private final HubSpotService hubSpotService;

    public ContatoServiceImpl(ContatoHttpClient contatoClient, HubSpotTokenCache hubSpotTokenCache, HubSpotService hubSpotService) {
        this.contatoClient = contatoClient;
        this.hubSpotTokenCache = hubSpotTokenCache;
        this.hubSpotService = hubSpotService;
    }

    @Override
    public void createContact(ContatoRecordDTO contactData, JwtAuthenticationToken jwtToken) {
        HubSpotResponse token = hubSpotTokenCache.getToken(jwtToken.getName());
        token = verificaTokenAindaValido(jwtToken, token);
        contatoClient.createContact(contactData,token.getAccessToken());
    }

    @Override
    public Page<HubSpotContactPropertiesDTO> findAllContacts(JwtAuthenticationToken jwtToken, Pageable pageable) {
        HubSpotResponse token = hubSpotTokenCache.getToken(jwtToken.getName());
        token = verificaTokenAindaValido(jwtToken, token);
        List<HubSpotContactPropertiesDTO> allContatos = contatoClient.findAllContatos(token.getAccessToken());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), allContatos.size());
        List<HubSpotContactPropertiesDTO> paginatedList = allContatos.subList(start, end);
        return new PageImpl<>(paginatedList, pageable, allContatos.size());
    }

    private HubSpotResponse verificaTokenAindaValido(JwtAuthenticationToken jwtToken, HubSpotResponse token) {
        if(Objects.isNull(token)) {
            throw new NotFoundException("Token not found");
        }

        if (token.getTokenExpiration().isBefore(Instant.now())){
            token = hubSpotService.atualizaTokenAcess(jwtToken.getName());
        }

        return token;
    }
}
