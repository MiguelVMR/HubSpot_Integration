package com.integracao.hubspot.exceptions;

public class HubSpotIntegrationError extends RuntimeException {
    public HubSpotIntegrationError(String message) {
        super(message);
    }
}
