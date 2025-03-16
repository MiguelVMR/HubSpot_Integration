package com.integracao.hubspot.client;

import com.integracao.hubspot.dtos.ContatoRecordDTO;
import com.integracao.hubspot.dtos.HubSpotContactPropertiesDTO;

import java.util.List;

/**
 * The Interface ContatoHttpClient
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 16/03/2025
 */
public interface ContatoHttpClient {
    List<HubSpotContactPropertiesDTO> findAllContatos(String acessToken);
    void createContact(ContatoRecordDTO contactData, String acessToken);
}
