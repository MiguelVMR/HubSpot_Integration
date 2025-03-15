package com.integracao.hubspot.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

/**
 * The Class UserModel
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 14/03/2025
 */
@Table(name = "tb_users")
@Entity(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String login;
    private String password;
}
