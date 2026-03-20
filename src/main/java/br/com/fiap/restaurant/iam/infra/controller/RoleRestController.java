package br.com.fiap.restaurant.iam.infra.controller;

import br.com.fiap.restaurant.iam.core.controller.RoleController;
import br.com.fiap.restaurant.iam.core.outbound.RoleOutput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
@Tag(name = "Perfis", description = "Endpoints para gerenciamento de perfis de usuário")
public class RoleRestController {

    private final RoleController roleController;

    public RoleRestController(RoleController roleController) {
        this.roleController = roleController;
    }

    @Operation(summary = "Listar todos os perfis", description = "Retorna uma lista com todos os perfis de usuário disponíveis no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Perfis listados com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = List.class, example = "[\"VIEW_USER\", \"CREATE_USER\"]"))),
            @ApiResponse(responseCode = "403", description = "Acesso negado.")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<String> getAllRoles() {
        return roleController.getAllRoles().stream().map(RoleOutput::name).toList();
    }
}
