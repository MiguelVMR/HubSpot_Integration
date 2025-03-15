package com.integracao.hubspot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * The Class RolesModel
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 15/03/2025
 */
@Table(name = "tb_roles")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;


    public enum Values{
        ADMIN(1L),
        BASIC(2L);

        long roleId;

        Values(long roleId) {
            this.roleId = roleId;
        }

        public long getRoleId() {
            return roleId;
        }
    }
}
