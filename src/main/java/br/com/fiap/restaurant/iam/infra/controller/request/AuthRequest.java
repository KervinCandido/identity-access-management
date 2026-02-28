package br.com.fiap.restaurant.iam.infra.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequest (
        @NotBlank
        @Size(min = 3, max = 20)
        String username,
        @NotBlank
        @Size(min = 8, max = 100)
        String password) {}
