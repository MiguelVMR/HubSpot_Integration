package com.integracao.hubspot.repository;

import com.integracao.hubspot.models.WebhookEventModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * The Interface WebhookEventRepository
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 16/03/2025
 */
public interface WebhookEventRepository extends CrudRepository<WebhookEventModel, UUID> {

    Page<WebhookEventModel> findAll(Pageable pageable);
}
