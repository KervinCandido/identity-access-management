package br.com.fiap.restaurant.iam.core.usecase.usertype;

import br.com.fiap.restaurant.iam.core.domain.model.UserType;
import br.com.fiap.restaurant.iam.core.domain.roles.ForGettingRoleName;
import br.com.fiap.restaurant.iam.core.domain.roles.UserTypeRoles;
import br.com.fiap.restaurant.iam.core.gateway.LoggedUserGateway;
import br.com.fiap.restaurant.iam.core.gateway.UserTypeGateway;
import br.com.fiap.restaurant.iam.core.usecase.UseCaseBase;

import java.util.Objects;
import java.util.Optional;

public class GetUserTypeByIdUseCase extends UseCaseBase<Long, Optional<UserType>> {

    private final UserTypeGateway userTypeGateway;

    public GetUserTypeByIdUseCase(UserTypeGateway userTypeGateway, LoggedUserGateway loggedUserGateway) {
        super(loggedUserGateway);
        this.userTypeGateway = Objects.requireNonNull(userTypeGateway, "UserTypeGateway cannot be null.");
    }

    @Override
    protected ForGettingRoleName getRequiredRole() {
        return UserTypeRoles.VIEW_USER_TYPE;
    }

    @Override
    protected Optional<UserType> doExecute(Long id) {
        return userTypeGateway.findById(id);
    }
}
