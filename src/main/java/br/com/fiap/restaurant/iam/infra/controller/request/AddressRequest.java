package br.com.fiap.restaurant.iam.infra.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Requisição para criar ou atualizar um endereço")
public class AddressRequest {

    @Schema(description = "Rua do endereço", example = "Avenida Lins de Vasconcelos")
    @NotBlank
    @Size(min = 1, max = 255)
    private String street;

    @Schema(description = "Número do endereço", example = "1222")
    @NotBlank
    private String number;

    @Schema(description = "Cidade do endereço", example = "São Paulo")
    @NotBlank
    private String city;

    @Schema(description = "Estado do endereço", example = "SP")
    @NotBlank
    private String state;

    @Schema(description = "CEP do endereço", example = "01538-001")
    @NotBlank
    private String zipCode;

    @Schema(description = "Complemento do endereço", example = "Apto 101")
    private String complement;
}
