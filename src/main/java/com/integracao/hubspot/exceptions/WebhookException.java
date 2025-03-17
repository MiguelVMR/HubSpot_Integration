package com.integracao.hubspot.exceptions;

public class WebhookException extends RuntimeException {
    public WebhookException(String message) {
        super(message);
    }
}
