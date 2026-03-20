package br.com.fiap.restaurant.iam.infra.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(description = "Resposta contendo os dados de um usuário")
public record UserResponse(
        @Schema(description = "ID do usuário", example = "a1b2c3d4-e5f6-7890-1234-567890abcdef")
        UUID id,
        @Schema(description = "Nome do usuário", example = "John Doe")
        String name,
        @Schema(description = "Nome de usuário para login", example = "john.doe")
        String username,
        @Schema(description = "E-mail do usuário", example = "john.doe@example.com")
        String email,
        AddressResponse address,
        UserTypeResponse userType) {}
