package com.integracao.hubspot.controllers.impl;

import com.integracao.hubspot.configs.configModels.CustomModelConfig;
import com.integracao.hubspot.configs.configModels.HubspotData;
import com.integracao.hubspot.services.HubSpotService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class HubSpotControllerImplTest {

    @Mock
    private CustomModelConfig customModelConfig;

    @Mock
    private HubSpotService hubSpotService;

    @InjectMocks
    private HubSpotControllerImpl hubSpotController;

    @Test
    @DisplayName("Should build correct authorization URL")
    void shouldBuildCorrectAuthorizationUrl(){
        HubspotData hubspotData = new HubspotData();
        hubspotData.setClientId("test-client-id");
        hubspotData.setRedirectUri("http://localhost:8080/callback");
        when(customModelConfig.getAutorizationUrl()).thenReturn("https://app.hubspot.com/oauth/authorize?client_id=");
        when(customModelConfig.getScope()).thenReturn("read");
        when(customModelConfig.getHubspotData()).thenReturn(hubspotData);

        Jwt jwt = Jwt.withTokenValue("dummy-token")
                .header("alg", "none")
                .claim("user_name", "testUser")
                .build();
        JwtAuthenticationToken jwtToken = new JwtAuthenticationToken(
                jwt,
                null,
                "testUser");

        String expectedUrl = "https://app.hubspot.com/oauth/authorize?client_id=test-client-id&redirect_uri=http://localhost:8080/callback&scope=read&state=testUser";

        ResponseEntity<Map<String, String>> response = hubSpotController.authorize(jwtToken);

        assertEquals(expectedUrl, response.getBody().get("AutorizationURL"));
    }

}