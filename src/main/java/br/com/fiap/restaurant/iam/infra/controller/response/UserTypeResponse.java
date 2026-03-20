package br.com.fiap.restaurant.iam.infra.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Resposta contendo os dados de um tipo de usuário")
public record UserTypeResponse(
        @Schema(description = "ID do tipo de usuário", example = "1")
        Long id,
        @Schema(description = "Nome do tipo de usuário", example = "Cliente")
        String name,
        @Schema(description = "Lista de perfis associados ao tipo de usuário", example = "[\"CREATE_ORDER\"]")
        List<String> roles) {}
