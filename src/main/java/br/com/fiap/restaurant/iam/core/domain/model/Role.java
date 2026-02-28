package br.com.fiap.restaurant.iam.core.domain.model;

import br.com.fiap.restaurant.iam.core.exception.BusinessException;

import java.util.Objects;

public record Role(Long id, String name) {

    public Role {
        Objects.requireNonNull(name, "Role name cannot be null.");
        if (name.isBlank()) throw new BusinessException("Role name cannot be blank.");
    }
}
