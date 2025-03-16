package com.integracao.hubspot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * The Class WebhookEventModel
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 16/03/2025
 */
@Table(name = "tb_webhook_event")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WebhookEventModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "webhook_event_id")
    private UUID webhookEventId;
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
