package br.com.fiap.restaurant.iam.infra.controller;

import br.com.fiap.restaurant.iam.infra.controller.request.AuthRequest;
import br.com.fiap.restaurant.iam.infra.controller.response.JwtBearerTokenResponse;
import br.com.fiap.restaurant.iam.infra.service.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Tag(name = "Autenticação", description = "Endpoints para autenticação de usuários.")
public class AuthController {

    private final AuthenticationService authenticationService;

    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Operation(summary = "Autenticação de usuário", description = "Autentica um usuário e retorna um JWT em caso de sucesso.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Autenticação bem-sucedida."),
        @ApiResponse(responseCode = "400", description = "Dados de requisição inválidos."),
        @ApiResponse(responseCode = "401", description = "Falha na autenticação.")
    })
    @SecurityRequirements
    @PostMapping
    public ResponseEntity<JwtBearerTokenResponse> auth(@RequestBody @Valid AuthRequest authRequest) {
        return  ResponseEntity.ok(new JwtBearerTokenResponse(authenticationService.authentication(authRequest)));
    }
}
