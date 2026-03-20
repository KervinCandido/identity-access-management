package br.com.fiap.restaurant.iam.infra.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta para erros de validação de campos (Bean Validation)")
public record FieldErrorResponse(
        @Schema(description = "Campo que gerou o erro de validação", example = "username")
        String field,
        @Schema(description = "Mensagem de erro de validação", example = "não deve estar em branco")
        String message) {}
