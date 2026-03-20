package br.com.fiap.restaurant.iam.infra.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
@Schema(description = "Requisição para criar ou atualizar um tipo de usuário")
public class UserTypeRequest {

    @Schema(description = "Nome do tipo de usuário", example = "Administrador")
    @NotBlank
    @Size(min = 3, max = 100)
    private String name;

    @Schema(description = "Lista de perfis associados ao tipo de usuário", example = "[\"CREATE_USER\", \"VIEW_USER\"]")
    @NotEmpty
    private List<String> roles;
}
