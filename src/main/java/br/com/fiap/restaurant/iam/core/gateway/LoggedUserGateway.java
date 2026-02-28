package br.com.fiap.restaurant.iam.core.gateway;

import br.com.fiap.restaurant.iam.core.domain.model.User;
import br.com.fiap.restaurant.iam.core.domain.roles.ForGettingRoleName;
import br.com.fiap.restaurant.iam.core.exception.*;

import java.util.Optional;

public interface LoggedUserGateway {

    boolean hasRole(ForGettingRoleName roleName);

    Optional<User> getCurrentUser();

    default User requireCurrentUser() {
        return getCurrentUser()
                .orElseThrow(UserNotAuthenticatedException::new);
    }
}
