package com.integracao.hubspot.infra;

import com.integracao.hubspot.configs.configModels.CustomModelConfig;
import com.integracao.hubspot.dtos.WebHookUpdateData;
import com.integracao.hubspot.models.RolesModel;
import com.integracao.hubspot.models.UserModel;
import com.integracao.hubspot.repository.RoleRepository;
import com.integracao.hubspot.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.util.Set;

/**
 * The Class AdminUserConfig
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 15/03/2025
 */
@Configuration
public class InitialConfig {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final CustomModelConfig customModelConfig;
    private final RestClient restClient;

    public InitialConfig(RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserRepository userRepository, CustomModelConfig customModelConfig, RestClient.Builder restClient) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.customModelConfig = customModelConfig;
        this.restClient = restClient.build();
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

    //Metodo que atualiza configurações do webhook com a url atual do ngrok (Muda toda vez que inicia a aplicação)
    @EventListener(ApplicationReadyEvent.class)
    public void updateWebhook(){
        WebHookUpdateData dados = new WebHookUpdateData();
        dados.setThrottling(new WebHookUpdateData.Throttling());
        dados.setTargetUrl(customModelConfig.getNgrokURL() + "/hubspot/webhook-contact-create-data");
         restClient.put()
                .uri(customModelConfig.getWebhookUrl())
                .contentType(MediaType.APPLICATION_JSON)
                .body(dados)
                .retrieve()
                .toBodilessEntity();
    }

}
