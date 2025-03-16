package com.integracao.hubspot.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class WebhookEventDTO
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 16/03/2025
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WebhookEventDTO {
    private long eventId;
    private long subscriptionId;
    private long portalId;
    private long appId;
    private long occurredAt;
    private String subscriptionType;
    private int attemptNumber;
    private long objectId;
    private String changeFlag;
    private String changeSource;
    private String sourceId;
}
