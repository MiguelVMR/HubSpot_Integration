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

    public void saveTokens(String userId, HubSpotResponse hubSpotResponse) {
        tokenCache.put(userId, hubSpotResponse);
    }

    public HubSpotResponse getToken(String userId) {
        return tokenCache.getIfPresent(userId);
    }

    public void removeToken(String userId) {
        tokenCache.invalidate(userId);
    }

    public HubSpotResponse updateToken(String userId,HubSpotResponse hubSpotResponse) {
        removeToken(userId);
        saveTokens(userId, hubSpotResponse);
        return hubSpotResponse;
    }


}
