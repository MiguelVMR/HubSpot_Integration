package com.integracao.hubspot.models;

import com.integracao.hubspot.dtos.LoginRequestRecordDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.UUID;

/**
 * The Class UserModel
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 14/03/2025
 */
@Table(name = "tb_users")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID userId;

    @Column(unique = true)
    private String username;

    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "tb_users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RolesModel> roles;

    public boolean isLoginCorrect(LoginRequestRecordDTO loginRequest, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginRequest.password(), this.password);
    }
}
