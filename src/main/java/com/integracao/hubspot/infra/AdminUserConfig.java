package com.integracao.hubspot.infra;

import com.integracao.hubspot.configs.configModels.CustomModelConfig;
import com.integracao.hubspot.models.RolesModel;
import com.integracao.hubspot.models.UserModel;
import com.integracao.hubspot.repository.RoleRepository;
import com.integracao.hubspot.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * The Class AdminUserConfig
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 15/03/2025
 */
@Configuration
public class AdminUserConfig {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CustomModelConfig customModelConfig;

    public AdminUserConfig(RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserRepository userRepository, CustomModelConfig customModelConfig) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.customModelConfig = customModelConfig;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void run(){
        var roleAdmin = roleRepository.findByName(RolesModel.Values.ADMIN.name().toLowerCase());
        var userAdmin = userRepository.findByUsername("admin");

        userAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("Usuario admin ja existe");
                },
                () ->{
                    var user = new UserModel();
                    user.setUsername("admin");
                    user.setRoles(Set.of(roleAdmin));
                    user.setPassword(passwordEncoder.encode(customModelConfig.getSenhaAdmin()));
                    userRepository.save(user);
                }
        );
    }
}
