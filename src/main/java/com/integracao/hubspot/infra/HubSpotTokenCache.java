package com.integracao.hubspot.infra;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.integracao.hubspot.dtos.HubSpotResponse;
import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * The Class HubSpotTokenCache
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 14/03/2025
 */
@Component
public class HubSpotTokenCache {
    private final Cache<String, HubSpotResponse> tokenCache = Caffeine.newBuilder()
            .expireAfterWrite(7, TimeUnit.DAYS)
            .maximumSize(100)
            .build();

    public void saveTokens(String code, HubSpotResponse hubSpotResponse) {
        tokenCache.put(code, hubSpotResponse);
    }

    public HubSpotResponse getToken(String code) {
        return tokenCache.getIfPresent(code);
    }

    public void removeToken(String code) {
        tokenCache.invalidate(code);
    }

    public HubSpotResponse updateToken(String code,HubSpotResponse hubSpotResponse) {
        removeToken(code);
        saveTokens(code, hubSpotResponse);
        return hubSpotResponse;
    }


}
