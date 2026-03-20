package br.com.fiap.restaurant.iam.infra.controller.response;

import br.com.fiap.restaurant.iam.infra.dto.JwtBearerToken;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resposta da autenticação contendo o token JWT")
public record JwtBearerTokenResponse(
        @Schema(description = "Tipo do token", example = "Bearer")
        String type,
        @Schema(description = "Token de acesso JWT", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...")
        String token,
        @Schema(description = "Tempo de expiração do token em segundos", example = "3600")
        Long expiresIn,
        @Schema(description = "Escopo de permissões do token", example = "read write")
        String scope) {
    public JwtBearerTokenResponse(JwtBearerToken authentication) {
        this(authentication.type(), authentication.token(), authentication.expiresIn(),  authentication.scope());
    }
}
