package com.integracao.hubspot.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * The Class RestClientConfig
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 13/12/2024
 */
@Configuration
public class RestClientConfig {

    @Bean
    public RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }
}
