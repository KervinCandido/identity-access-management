package br.com.fiap.restaurant.iam.infra.publisher.dto;

import br.com.fiap.restaurant.iam.core.domain.model.Role;
import br.com.fiap.restaurant.iam.core.domain.model.User;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public record UserDTO(UUID uuid, Set<String> roles) {

    public UserDTO(User user) {
        this(user.getId(), user.getUserType().getRoles().stream().map(Role::name).collect(Collectors.toSet()));
    }
}
