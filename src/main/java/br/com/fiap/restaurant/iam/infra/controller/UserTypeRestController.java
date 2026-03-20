package br.com.fiap.restaurant.iam.infra.controller;

import br.com.fiap.restaurant.iam.core.controller.UserTypeController;
import br.com.fiap.restaurant.iam.infra.controller.mapper.UserTypeRestMapper;
import br.com.fiap.restaurant.iam.infra.controller.request.UserTypeRequest;
import br.com.fiap.restaurant.iam.infra.controller.response.UserTypeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/user-types")
@Tag(name = "Tipos de Usuário", description = "Endpoints para gerenciamento de tipos de usuário")
public class UserTypeRestController {

    private final UserTypeController userTypeController;
    private final UserTypeRestMapper userTypeRestMapper;

    public UserTypeRestController(UserTypeController userTypeController, UserTypeRestMapper userTypeRestMapper) {
        this.userTypeController = userTypeController;
        this.userTypeRestMapper = userTypeRestMapper;
    }

    @Operation(summary = "Criar tipo de usuário", description = "Cria um novo tipo de usuário no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Tipo de usuário criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Requisição inválida."),
            @ApiResponse(responseCode = "403", description = "Acesso negado.")
    })
    @PostMapping
    public ResponseEntity<UserTypeResponse> createUsertype(@RequestBody @Valid UserTypeRequest userTypeRequest, UriComponentsBuilder uriComponentsBuilder) {
        var createUserTypeInput = userTypeRestMapper.toInput(userTypeRequest);
        var createUserTypeOutput = userTypeController.createUserType(createUserTypeInput);
        var uri = uriComponentsBuilder.path("/user-types/{id}").buildAndExpand(createUserTypeOutput.id()).toUri();
        return ResponseEntity.created(uri).body(userTypeRestMapper.toResponse(createUserTypeOutput));
    }

    @Operation(summary = "Buscar tipo de usuário por ID", description = "Retorna um tipo de usuário específico com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipo de usuário encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Tipo de usuário não encontrado."),
            @ApiResponse(responseCode = "403", description = "Acesso negado.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserTypeResponse> getUserTypeById(@PathVariable("id") Long id) {
        return userTypeController.getUserTypeById(id)
                .map(userTypeRestMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Listar todos os tipos de usuário", description = "Retorna uma lista com todos os tipos de usuário cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tipos de usuário listados com sucesso."),
            @ApiResponse(responseCode = "403", description = "Acesso negado.")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserTypeResponse> listUserTypes() {
        return userTypeController.listAllUserTypes().stream().map(userTypeRestMapper::toResponse).toList();
    }

    @Operation(summary = "Atualizar tipo de usuário", description = "Atualiza os dados de um tipo de usuário existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tipo de usuário atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Requisição inválida."),
            @ApiResponse(responseCode = "404", description = "Tipo de usuário não encontrado."),
            @ApiResponse(responseCode = "403", description = "Acesso negado.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUserType(@PathVariable("id") Long id,
                                               @RequestBody @Valid UserTypeRequest userTypeRequest) {
        var updateUserTypeInput = userTypeRestMapper.toUpdateInput(userTypeRequest, id);
        userTypeController.updateUserType(updateUserTypeInput);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Excluir tipo de usuário", description = "Exclui um tipo de usuário do sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tipo de usuário excluído com sucesso."),
            @ApiResponse(responseCode = "404", description = "Tipo de usuário não encontrado."),
            @ApiResponse(responseCode = "403", description = "Acesso negado.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserType(@PathVariable("id") Long id) {
        userTypeController.deleteUserType(id);
        return ResponseEntity.noContent().build();
    }
}
