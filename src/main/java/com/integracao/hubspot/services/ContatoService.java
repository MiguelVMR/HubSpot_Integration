package com.integracao.hubspot.services;

import com.integracao.hubspot.dtos.ContatoRecordDTO;
import com.integracao.hubspot.dtos.HubSpotContactPropertiesDTO;

import java.util.List;

/**
 * The Interface ContatoService
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 14/03/2025
 */
public interface ContatoService {

    void createContact(ContatoRecordDTO contactData);

    List<HubSpotContactPropertiesDTO> findAllContacts();
}
