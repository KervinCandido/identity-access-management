package br.com.fiap.restaurant.iam.core.outbound;

import java.util.UUID;

public record UserOutput(
        UUID id,
        String name,
        String username,
        String email,
        AddressOutput address,
        UserTypeOutput userType
) {}
