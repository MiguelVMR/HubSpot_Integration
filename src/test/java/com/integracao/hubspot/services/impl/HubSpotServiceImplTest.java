package com.integracao.hubspot.services.impl;

import com.integracao.hubspot.client.HubSpotHttpClient;
import com.integracao.hubspot.dtos.HubSpotResponse;
import com.integracao.hubspot.dtos.WebhookEventDTO;
import com.integracao.hubspot.exceptions.WebhookException;
import com.integracao.hubspot.infra.HubSpotTokenCache;
import com.integracao.hubspot.models.WebhookEventModel;
import com.integracao.hubspot.repository.WebhookEventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("HubSpotServiceImpl Unit Tests")
class HubSpotServiceImplTest {

    @Mock
    private HubSpotHttpClient hubSpotClient;

    @Mock
    private HubSpotTokenCache hubSpotTokenCache;

    @Mock
    private WebhookEventRepository webhookEventRepository;

    @InjectMocks
    private HubSpotServiceImpl hubSpotService;

    @BeforeEach
    void setUp() {
        reset(hubSpotClient, hubSpotTokenCache, webhookEventRepository);
    }

    @Test
    @DisplayName("Should generate access token and store in cache successfully")
    void shouldGenerateTokenAndStoreInCache() {
        String code = "9df8b104-7b24-42b4-8977-d019cdaa71c3";
        String state = "c2b03ba0-b267-437b-952d-45581592f7aa";
        HubSpotResponse mockResponse = new HubSpotResponse();

        when(hubSpotClient.generateTokenAcess(code)).thenReturn(mockResponse);

        hubSpotService.generateTokenAcess(code, state);

        verify(hubSpotClient, times(1)).generateTokenAcess(code);
        verify(hubSpotTokenCache, times(1)).saveTokens(state, mockResponse);
    }

    @Test
    @DisplayName("Should throw exception when generateTokenAccess fails")
    void shouldThrowExceptionWhenGenerateTokenFails() {
        String code = "4fa20773-bc92-4e26-9343-1e8ca5505886";
        String state = "4fa20773-bc92-4e26-9343-1e8ca5505881";

        when(hubSpotClient.generateTokenAcess(code)).thenThrow(new RuntimeException("Invalid code"));


        Exception exception = assertThrows(RuntimeException.class, () -> hubSpotService.generateTokenAcess(code, state));
        assertEquals("Invalid code", exception.getMessage());

        verify(hubSpotClient, times(1)).generateTokenAcess(code);
        verify(hubSpotTokenCache, never()).saveTokens(anyString(), any());
    }

    @Test
    @DisplayName("Should update access token successfully")
    void shouldUpdateTokenSuccessfully() {
        String userId = "34fd8098-7af2-4607-bf45-9fbca0cefe0d";
        HubSpotResponse oldToken = new HubSpotResponse();
        HubSpotResponse newToken = new HubSpotResponse();

        when(hubSpotTokenCache.getToken(userId)).thenReturn(oldToken);
        when(hubSpotClient.updateTokenAcess(oldToken)).thenReturn(newToken);
        when(hubSpotTokenCache.updateToken(userId, newToken)).thenReturn(newToken);

        HubSpotResponse result = hubSpotService.updateTokenAcess(userId);

        assertNotNull(result);
        assertEquals(newToken, result);
        verify(hubSpotTokenCache, times(1)).getToken(userId);
        verify(hubSpotClient, times(1)).updateTokenAcess(oldToken);
        verify(hubSpotTokenCache, times(1)).updateToken(userId, newToken);
    }

    @Test
    @DisplayName("Should throw exception when updating token and user ID is invalid")
    void shouldThrowExceptionWhenUserIdIsInvalid() {
        String userId = "2b36cff1-fc93-4247-a52f-1cea901cb7e8";
        when(hubSpotTokenCache.getToken(userId)).thenThrow(new RuntimeException("User ID not found"));

        Exception exception = assertThrows(RuntimeException.class, () -> hubSpotService.updateTokenAcess(userId));
        assertEquals("User ID not found", exception.getMessage());

        verify(hubSpotTokenCache, times(1)).getToken(userId);
        verify(hubSpotClient, never()).updateTokenAcess(any());
        verify(hubSpotTokenCache, never()).updateToken(anyString(), any());
    }

    @Test
    @DisplayName("Should save webhook events successfully")
    void shouldSaveWebhookEventsSuccessfully() {
        WebhookEventDTO eventDTO = new WebhookEventDTO();
        List<WebhookEventDTO> dtoList = Collections.singletonList(eventDTO);

        hubSpotService.saveWebhookData(dtoList);

        verify(webhookEventRepository, times(1)).saveAll(any());
    }

    @Test
    @DisplayName("Should throw exception when saveWebhookData receives empty list")
    void shouldThrowExceptionWhenSavingEmptyWebhookList() {
        List<WebhookEventDTO> emptyList = Collections.emptyList();

        Exception exception = assertThrows(WebhookException.class, () -> {
            hubSpotService.saveWebhookData(emptyList);
        });

        assertEquals("Webhook data list cannot be empty", exception.getMessage());
    }

    @Test
    @DisplayName("Should retrieve paginated webhook events successfully")
    void shouldRetrieveWebhookEventsSuccessfully() {
        PageRequest pageable = PageRequest.of(0, 10);
        Page<WebhookEventModel> mockPage = new PageImpl<>(Collections.emptyList());

        when(webhookEventRepository.findAll(pageable)).thenReturn(mockPage);

        Page<WebhookEventModel> result = hubSpotService.viewDataWebhookSaved(pageable);

        assertNotNull(result);
        assertEquals(mockPage, result);
        verify(webhookEventRepository, times(1)).findAll(pageable);
    }

    @Test
    @DisplayName("Should throw exception when retrieving paginated webhook events fails")
    void shouldThrowExceptionWhenRetrievingWebhookEventsFails() {
        PageRequest pageable = PageRequest.of(0, 10);
        when(webhookEventRepository.findAll(pageable)).thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(RuntimeException.class, () -> hubSpotService.viewDataWebhookSaved(pageable));
        assertEquals("Database error", exception.getMessage());

        verify(webhookEventRepository, times(1)).findAll(pageable);
    }
}