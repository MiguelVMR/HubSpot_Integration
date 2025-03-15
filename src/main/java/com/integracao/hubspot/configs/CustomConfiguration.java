package com.integracao.hubspot.configs;

import com.integracao.hubspot.configs.configModels.CustomModelConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The Class CustomConfiguration
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 13/03/2025
 */
@Configuration
public class CustomConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "custom")
    public CustomModelConfig customModelConfig() {
        return new CustomModelConfig();
    }
}
