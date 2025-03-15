package com.integracao.hubspot.services.impl;

import com.integracao.hubspot.client.ContatoHttpClient;
import com.integracao.hubspot.dtos.ContatoRecordDTO;
import com.integracao.hubspot.dtos.HubSpotContactPropertiesDTO;
import com.integracao.hubspot.services.ContatoService;
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

    public ContatoServiceImpl(ContatoHttpClient contatoClient) {
        this.contatoClient = contatoClient;
    }

    @Override
    public void createContact(ContatoRecordDTO contactData) {
        contatoClient.createContact(contactData);
    }

    @Override
    public List<HubSpotContactPropertiesDTO> findAllContacts() {
        return contatoClient.findAllContatos();
    }
}
