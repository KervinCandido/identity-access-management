package br.com.fiap.restaurant.iam.core.usecase.usertype;

import br.com.fiap.restaurant.iam.core.domain.model.UserType;
import br.com.fiap.restaurant.iam.core.domain.roles.ForGettingRoleName;
import br.com.fiap.restaurant.iam.core.domain.roles.UserTypeRoles;
import br.com.fiap.restaurant.iam.core.gateway.LoggedUserGateway;
import br.com.fiap.restaurant.iam.core.gateway.UserTypeGateway;
import br.com.fiap.restaurant.iam.core.usecase.UseCaseWithoutInput;

import java.util.Objects;
import java.util.Set;


public class ListUserTypesUseCase extends UseCaseWithoutInput<Set<UserType>> {

    private final UserTypeGateway userTypeGateway;

    public ListUserTypesUseCase(UserTypeGateway userTypeGateway, LoggedUserGateway loggedUserGateway) {
        super(Objects.requireNonNull(loggedUserGateway, "LoggedUserGateway cannot be null."));
        this.userTypeGateway = Objects.requireNonNull(userTypeGateway, "UserTypeGateway cannot be null.");
    }

    @Override
    protected ForGettingRoleName getRequiredRole() {
        return UserTypeRoles.VIEW_USER_TYPE;
    }

    @Override
    protected Set<UserType> doExecute() {
        return userTypeGateway.findAll();
    }

}
