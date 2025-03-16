package com.integracao.hubspot.repository;

import com.integracao.hubspot.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * The Interface UserRepository
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 15/03/2025
 */
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    Optional<UserModel> findByUsername(String username);

    boolean existsByUsername(String username);
}
