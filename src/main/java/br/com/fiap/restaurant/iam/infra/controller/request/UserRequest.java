package br.com.fiap.restaurant.iam.infra.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(description = "Requisição para criar ou atualizar um usuário")
public class UserRequest {
    @Schema(description = "Nome do usuário", example = "John Doe")
    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @Schema(description = "Nome de usuário para login", example = "john.doe")
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @Schema(description = "E-mail do usuário", example = "john.doe@example.com")
    @NotBlank
    @Email
    private String email;

    @Valid
    private AddressRequest address;

    @Schema(description = "ID do tipo de usuário", example = "1")
    @NotNull
    @Positive
    private Long userTypeId;

    @Schema(description = "Senha do usuário", example = "password123")
    @NotBlank
    @Size(min = 8, max = 100)
    private String password;
}
