package br.com.fiap.restaurant.iam.infra.auth;

import br.com.fiap.restaurant.iam.core.domain.model.User;
import br.com.fiap.restaurant.iam.core.domain.roles.ForGettingRoleName;
import br.com.fiap.restaurant.iam.core.gateway.LoggedUserGateway;
import br.com.fiap.restaurant.iam.infra.mapper.UserMapper;
import br.com.fiap.restaurant.iam.infra.persistence.entity.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoggedUserGatewayAdapter implements LoggedUserGateway {

    private final UserMapper userMapper;

    public LoggedUserGatewayAdapter(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean hasRole(ForGettingRoleName roleName) {
        if (roleName == null) return false;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) return false;

        String expected = roleName.getRoleName();

        return auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(expected::equals);
    }

    @Override
    public Optional<User> getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) return Optional.empty();

        if (auth.getPrincipal() instanceof UserEntity u) {
            return Optional.of(userMapper.toDomain(u));
        }
        return Optional.empty();
    }
}
