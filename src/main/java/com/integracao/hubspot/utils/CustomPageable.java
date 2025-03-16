package com.integracao.hubspot.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Objects;
import java.util.Optional;

/**
 * The Class CustomPageable
 *
 * @author Miguel Vilela Moraes Ribeiro
 * @since 16/03/2025
 */
public class CustomPageable extends PageRequest {

    protected CustomPageable(int pageNumber, int pageSize, Sort sort) {
        super(pageNumber, pageSize, sort);
    }

    public static Pageable getInstance(Integer page, Integer size) {
        page = Optional.ofNullable(page).orElse(0);
        size = Optional.ofNullable(size).orElse(10);

            return PageRequest.of(page, size);
    }
}
