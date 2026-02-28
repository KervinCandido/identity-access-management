package br.com.fiap.restaurant.iam.core.usecase;

import br.com.fiap.restaurant.iam.core.domain.roles.ForGettingRoleName;
import br.com.fiap.restaurant.iam.core.exception.OperationNotAllowedException;
import br.com.fiap.restaurant.iam.core.gateway.LoggedUserGateway;

import java.util.Objects;

public abstract class UseCase {

    protected final LoggedUserGateway loggedUserGateway;

    protected UseCase(LoggedUserGateway loggedUserGateway) {
        Objects.requireNonNull(loggedUserGateway, "LoggedUserGateway cannot be null.");
        this.loggedUserGateway = loggedUserGateway;
    }

    protected void validateAccess() throws OperationNotAllowedException {
        boolean isAllowed = isPublicAccessAllowed() || loggedUserGateway.hasRole(getRequiredRole());
        if (!isAllowed)
            throw new OperationNotAllowedException("The current user does not have permission to perform this action.");
    }

    protected abstract ForGettingRoleName getRequiredRole();

    protected boolean isPublicAccessAllowed() {
        return false;
    }
}
