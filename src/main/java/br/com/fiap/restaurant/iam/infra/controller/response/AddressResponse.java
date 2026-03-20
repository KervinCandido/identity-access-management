package br.com.fiap.restaurant.iam.infra.controller.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta contendo os dados de um endereço")
public record AddressResponse (
        @Schema(description = "Rua do endereço", example = "Avenida Lins de Vasconcelos")
        String street,
        @Schema(description = "Número do endereço", example = "1222")
        String number,
        @Schema(description = "Cidade do endereço", example = "São Paulo")
        String city,
        @Schema(description = "Estado do endereço", example = "SP")
        String state,
        @Schema(description = "CEP do endereço", example = "01538-001")
        String zipCode,
        @Schema(description = "Complemento do endereço", example = "Apto 101")
        String complement
) {}
