package com.integracao.hubspot.dtos;

import lombok.Data;

/**
 * The Class WebHookUpdateData
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 16/03/2025
 */
@Data
public class WebHookUpdateData {
    private Throttling throttling;
    private String targetUrl;

    @Data
    public static class Throttling {
        private int maxConcurrentRequests = 10;
    }
}
