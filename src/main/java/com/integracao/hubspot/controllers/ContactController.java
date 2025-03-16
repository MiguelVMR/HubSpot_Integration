package com.integracao.hubspot.controllers;

import com.integracao.hubspot.controllers.interfaces.ContactControllerInterface;
import com.integracao.hubspot.dtos.ContatoRecordDTO;
import com.integracao.hubspot.dtos.HubSpotContactPropertiesDTO;
import com.integracao.hubspot.services.ContatoService;
import com.integracao.hubspot.utils.CustomPageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * The Class ContactController
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 12/03/2025
 */
@RestController
@RequestMapping("/contacts")
public class ContactController implements ContactControllerInterface {
    private final ContatoService contatoService;

    public ContactController(ContatoService contatoService) {
        this.contatoService = contatoService;
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createContact(@RequestBody ContatoRecordDTO contactData, JwtAuthenticationToken jwtToken) {
        contatoService.createContact(contactData,jwtToken);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Page<HubSpotContactPropertiesDTO>> listContacts(JwtAuthenticationToken jwtToken, Integer page, Integer size) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(contatoService.findAllContacts(jwtToken, CustomPageable.getInstance(page,size)));
    }

}
