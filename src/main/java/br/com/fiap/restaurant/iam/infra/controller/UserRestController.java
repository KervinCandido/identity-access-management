package br.com.fiap.restaurant.iam.infra.controller;

import br.com.fiap.restaurant.iam.core.controller.UserController;
import br.com.fiap.restaurant.iam.core.domain.pagination.Page;
import br.com.fiap.restaurant.iam.infra.controller.mapper.UserRestMapper;
import br.com.fiap.restaurant.iam.infra.controller.request.UserRequest;
import br.com.fiap.restaurant.iam.infra.controller.response.UserResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
public class UserRestController {

    private final UserController userController;
    private final UserRestMapper userRestMapper;

    public UserRestController(UserController userController, UserRestMapper userRestMapper) {
        this.userController = userController;
        this.userRestMapper = userRestMapper;
    }

    @Operation(summary = "Criar usuário", description = "Cria um novo usuário no sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Requisição inválida."),
            @ApiResponse(responseCode = "403", description = "Acesso negado.")
    })
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest, UriComponentsBuilder uriComponentsBuilder) {
        var createUserInput = userRestMapper.toInput(userRequest);
        var createUserOutput = userController.create(createUserInput);
        var uri = uriComponentsBuilder.path("/user/{id}").buildAndExpand(createUserOutput.id()).toUri();
        return ResponseEntity.created(uri).body(userRestMapper.toResponse(createUserOutput));
    }

    @Operation(summary = "Atualizar usuário", description = "Atualiza os dados de um usuário existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário atualizado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Requisição inválida."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado."),
            @ApiResponse(responseCode = "403", description = "Acesso negado.")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable("id") UUID id, @RequestBody @Valid UserRequest userRequest) {
        var updateUserInput = userRestMapper.toUpdateInput(userRequest, id);
        userController.update(updateUserInput);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Excluir usuário", description = "Exclui um usuário do sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário excluído com sucesso."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado."),
            @ApiResponse(responseCode = "403", description = "Acesso negado.")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") UUID id) {
        userController.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar usuário por ID", description = "Retorna um usuário específico com base no seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado."),
            @ApiResponse(responseCode = "403", description = "Acesso negado.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> findById(@PathVariable("id") UUID id) {
        return userController.findById(id).map(userRestMapper::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista paginada com todos os usuários cadastrados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuários listados com sucesso."),
            @ApiResponse(responseCode = "403", description = "Acesso negado.")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UserResponse> findAll(
            @Parameter(description = "Número da página", example = "0") @RequestParam(value = "pageNumber", defaultValue = "0") int pageNumber,
            @Parameter(description = "Tamanho da página", example = "10") @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        var page = userController.findAll(pageNumber, pageSize);
        return page.mapItems(userRestMapper::toResponse);
    }
}
