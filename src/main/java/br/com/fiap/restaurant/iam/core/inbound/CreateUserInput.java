package br.com.fiap.restaurant.iam.core.inbound;

public record CreateUserInput (
        String name,
        String username,
        String email,
        String password,
        AddressInput address,
        Long userTypeId
) {}
