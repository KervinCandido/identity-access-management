package br.com.fiap.restaurant.iam.core.inbound;

import java.util.UUID;

public record UpdateUserInput(
    UUID id,
    String name,
    String username,
    String email,
    AddressInput address,
    Long userTypeId
){}

