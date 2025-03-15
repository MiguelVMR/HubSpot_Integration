package com.integracao.hubspot.configs.configModels;

import lombok.Data;

/**
 * The Class HubspotData
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 13/03/2025
 */
@Data
public class HubspotData {
    private String clientId;
    private String clientSecret;
    private String redirectUri;
}
