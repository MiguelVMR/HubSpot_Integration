package com.integracao.hubspot.configs.configModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class HubspotData
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 13/03/2025
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HubspotData {
    private String clientId;
    private String clientSecret;
    private String redirectUri;
}
