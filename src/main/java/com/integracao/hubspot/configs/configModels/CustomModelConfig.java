package com.integracao.hubspot.configs.configModels;

import lombok.Data;

/**
 * The Class CustomModelConfig
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 13/03/2025
 */
@Data
public class CustomModelConfig {
    private String autorizationUrl;
    private String tokenUrl;
    private String contatoUrl;
    private String scope;
    private String senhaAdmin;
    private HubspotData hubspotData;
}
