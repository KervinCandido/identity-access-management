package br.com.fiap.restaurant.iam.infra.controller.response;

import java.util.UUID;

public record UserResponse(UUID id, String name, String username, String email, AddressResponse address, UserTypeResponse userType) {}
