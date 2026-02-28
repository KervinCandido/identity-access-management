package br.com.fiap.restaurant.iam.core.usecase.usertype;

import br.com.fiap.restaurant.iam.core.domain.roles.ForGettingRoleName;
import br.com.fiap.restaurant.iam.core.domain.roles.UserTypeRoles;
import br.com.fiap.restaurant.iam.core.exception.BusinessException;
import br.com.fiap.restaurant.iam.core.exception.UserTypeInUseException;
import br.com.fiap.restaurant.iam.core.gateway.LoggedUserGateway;
import br.com.fiap.restaurant.iam.core.gateway.UserTypeGateway;
import br.com.fiap.restaurant.iam.core.usecase.UseCaseBase;

import java.util.Objects;

public class DeleteUserTypeUseCase extends UseCaseBase<Long, Void> {

    private final UserTypeGateway userTypeGateway;

    public DeleteUserTypeUseCase(UserTypeGateway userTypeGateway, LoggedUserGateway loggedUserGateway) {
        super(Objects.requireNonNull(loggedUserGateway, "LoggedUserGateway cannot be null."));
        this.userTypeGateway = Objects.requireNonNull(userTypeGateway, "UserTypeGateway cannot be null.");
    }

    @Override
    protected ForGettingRoleName getRequiredRole() {
        return UserTypeRoles.DELETE_USER_TYPE;
    }

    @Override
    protected Void doExecute(Long id) {
        userTypeGateway.findById(id).orElseThrow(() -> new BusinessException("User type not found."));

        if (userTypeGateway.isInUse(id)) {
            throw new UserTypeInUseException();
        }

        userTypeGateway.delete(id);
        return null;
    }
}
