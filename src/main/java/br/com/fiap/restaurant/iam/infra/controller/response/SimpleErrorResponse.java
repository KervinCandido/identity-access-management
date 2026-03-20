package br.com.fiap.restaurant.iam.infra.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta de erro simples contendo uma mensagem")
public record SimpleErrorResponse(
        @Schema(description = "Mensagem de erro", example = "Erro de negócio")
        String message) {}
