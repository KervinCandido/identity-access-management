package br.com.fiap.restaurant.iam.infra.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Requisição de autenticação de usuário", name = "AuthRequest")
public record AuthRequest (
        @Schema(description = "Nome de usuário", example = "john.doe")
        @NotBlank
        @Size(min = 3, max = 20)
        String username,
        @Schema(description = "Senha do usuário", example = "password123")
        @NotBlank
        @Size(min = 8, max = 100)
        String password) {}
