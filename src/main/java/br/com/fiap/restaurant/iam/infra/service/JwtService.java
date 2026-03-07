package br.com.fiap.restaurant.iam.infra.service;

import br.com.fiap.restaurant.iam.core.exception.InvalidCredentialsException;
import br.com.fiap.restaurant.iam.infra.persistence.entity.UserEntity;
import br.com.fiap.restaurant.iam.infra.vo.JwtBearerToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class JwtService {

    public static final String BEARER = "Bearer";
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public JwtService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
    }

    public JwtBearerToken generateToken(UserDetails authentication) {
        var now = Instant.now();
        var expiry = 3600L;

        var scope = authentication.getAuthorities()
                .stream()
                .filter(SimpleGrantedAuthority.class::isInstance) // para n pegar as authorities que o spring usa internamente
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        UserEntity user = (UserEntity) authentication;
        var jti = UUID.randomUUID().toString();

        Instant expiresAt = now.plusSeconds(expiry);
        var claims = JwtClaimsSet.builder()
            .id(jti)
            .issuer("restaurant-identity-access-management")
            .issuedAt(now)
            .expiresAt(expiresAt)
            .subject(user.getId().toString())
            .claim("roles", scope)
            .build();

        var tokenScopeBuilder = new StringBuilder();
        if (scope.contains("VIEW")) tokenScopeBuilder.append("read ");
        if (scope.contains("CREATE") || scope.contains("UPDATE") || scope.contains("DELETE")) tokenScopeBuilder.append("write ");
        var tokenScope = tokenScopeBuilder.toString().trim();

        String token = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new JwtBearerToken(BEARER, token, expiresAt.toEpochMilli(), tokenScope);
    }

    public boolean isValidToken(String token) {
        try {
            Optional<Jwt> jwt = Optional.ofNullable(jwtDecoder.decode(token));
            var invalidTokenException = new InvalidCredentialsException("The token is invalid.");

            var expiresAt = Optional.ofNullable(jwt.orElseThrow(() -> invalidTokenException).getExpiresAt());
            if (expiresAt.orElseThrow(()-> invalidTokenException).isBefore(Instant.now())) {
                throw new InvalidCredentialsException("The token has expired.");
            }
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String getUserId(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        return jwt.getSubject();
    }

    public Collection<GrantedAuthority> getAuthorities(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        return Arrays.stream(jwt.getClaimAsString("roles").split("\\s")).map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }
}
