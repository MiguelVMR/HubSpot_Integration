package com.integracao.hubspot.repository;

import com.integracao.hubspot.models.RolesModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Interface RoleRepository
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 15/03/2025
 */
public interface RoleRepository extends JpaRepository<RolesModel,Long> {
    RolesModel findByName(String name);
}
