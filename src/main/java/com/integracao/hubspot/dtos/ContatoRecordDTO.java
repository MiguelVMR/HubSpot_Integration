package com.integracao.hubspot.dtos;

import jakarta.validation.Valid;

/**
* The Record ContatoRecordDTO
*
* @author Miguel Vilela Moraes Ribeiro
* @since 14/03/2025
*/
public record ContatoRecordDTO(
        @Valid ContactaPropertiesDTO properties
) {
}
