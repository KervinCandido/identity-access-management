package br.com.fiap.restaurant.iam.core.usecase.role;

import br.com.fiap.restaurant.iam.core.domain.model.Role;
import br.com.fiap.restaurant.iam.core.domain.roles.ForGettingRoleName;
import br.com.fiap.restaurant.iam.core.domain.roles.RoleRoles;
import br.com.fiap.restaurant.iam.core.gateway.LoggedUserGateway;
import br.com.fiap.restaurant.iam.core.gateway.RoleGateway;
import br.com.fiap.restaurant.iam.core.usecase.UseCaseWithoutInput;

import java.util.Objects;
import java.util.Set;

public class ListRolesUseCase extends UseCaseWithoutInput<Set<Role>> {

    private final RoleGateway roleGateway;

    public ListRolesUseCase(LoggedUserGateway loggedUserGateway, RoleGateway roleGateway) {
        super(loggedUserGateway);
        Objects.requireNonNull(roleGateway, "RoleGateway cannot be null.");
        this.roleGateway = roleGateway;
    }

    @Override
    protected Set<Role> doExecute() {
        return roleGateway.findAll();
    }

    @Override
    protected ForGettingRoleName getRequiredRole() {
        return RoleRoles.VIEW_ROLE;
    }
}
