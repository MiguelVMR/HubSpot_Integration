package com.integracao.hubspot.services.impl;

import com.integracao.hubspot.client.HubSpotHttpClient;
import com.integracao.hubspot.dtos.HubSpotResponse;
import com.integracao.hubspot.infra.HubSpotTokenCache;
import com.integracao.hubspot.services.HubSpotService;
import org.springframework.stereotype.Service;

/**
 * The Class HubSpotServiceImpl
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 14/03/2025
 */
@Service
public class HubSpotServiceImpl implements HubSpotService {

    private final HubSpotHttpClient hubSpotClient;
    private final HubSpotTokenCache hubSpotTokenCache;

    public HubSpotServiceImpl(HubSpotHttpClient hubSpotClient, HubSpotTokenCache hubSpotTokenCache) {
        this.hubSpotClient = hubSpotClient;
        this.hubSpotTokenCache = hubSpotTokenCache;
    }

    @Override
    public void geraTokenAcess(String code, String state) {
        HubSpotResponse hubSpotResponse = hubSpotClient.geraTokenAcess(code);
        hubSpotTokenCache.saveTokens(state, hubSpotResponse);
    }

    @Override
    public HubSpotResponse atualizaTokenAcess(String userId) {
        HubSpotResponse hubSpotCredencials = hubSpotTokenCache.getToken(userId);
        HubSpotResponse hubSpotResponse = hubSpotClient.atualizaTokenAcess(hubSpotCredencials);
        return hubSpotTokenCache.updateToken(userId, hubSpotResponse);
    }


}
