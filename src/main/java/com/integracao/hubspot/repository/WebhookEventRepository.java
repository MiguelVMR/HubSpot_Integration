package com.integracao.hubspot.repository;

import com.integracao.hubspot.models.WebhookEventModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

/**
 * The Interface WebhookEventRepository
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 16/03/2025
 */
public interface WebhookEventRepository extends CrudRepository<WebhookEventModel, UUID> {

    List<WebhookEventModel> findAll();
}
