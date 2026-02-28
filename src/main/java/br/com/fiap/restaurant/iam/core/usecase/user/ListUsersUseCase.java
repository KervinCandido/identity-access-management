package br.com.fiap.restaurant.iam.core.usecase.user;

import br.com.fiap.restaurant.iam.core.domain.model.User;
import br.com.fiap.restaurant.iam.core.domain.pagination.Page;
import br.com.fiap.restaurant.iam.core.domain.pagination.PagedQuery;
import br.com.fiap.restaurant.iam.core.domain.roles.ForGettingRoleName;
import br.com.fiap.restaurant.iam.core.domain.roles.UserManagementRoles;
import br.com.fiap.restaurant.iam.core.gateway.LoggedUserGateway;
import br.com.fiap.restaurant.iam.core.gateway.UserGateway;
import br.com.fiap.restaurant.iam.core.usecase.UseCaseBase;

import java.util.Objects;

public class ListUsersUseCase extends UseCaseBase<PagedQuery<Void>, Page<User>> {

    private final UserGateway userGateway;

    public ListUsersUseCase(UserGateway userGateway, LoggedUserGateway loggedUserGateway) {
        super(loggedUserGateway);
        Objects.requireNonNull(loggedUserGateway, "loggedUserGateway cannot be null");
        this.userGateway = userGateway;
    }

    @Override
    protected ForGettingRoleName getRequiredRole() {
        return UserManagementRoles.VIEW_USER;
    }

    @Override
    protected Page<User> doExecute(PagedQuery<Void> input) {
        return userGateway.findAll(input);
    }
}
