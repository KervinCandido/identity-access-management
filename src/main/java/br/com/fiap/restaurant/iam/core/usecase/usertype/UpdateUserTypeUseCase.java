package br.com.fiap.restaurant.iam.core.usecase.usertype;

import br.com.fiap.restaurant.iam.core.domain.model.Role;
import br.com.fiap.restaurant.iam.core.domain.model.UserType;
import br.com.fiap.restaurant.iam.core.domain.roles.ForGettingRoleName;
import br.com.fiap.restaurant.iam.core.domain.roles.UserTypeRoles;
import br.com.fiap.restaurant.iam.core.exception.BusinessException;
import br.com.fiap.restaurant.iam.core.exception.InvalidRoleException;
import br.com.fiap.restaurant.iam.core.exception.UserTypeNameIsAlreadyInUseException;
import br.com.fiap.restaurant.iam.core.exception.UserTypeWithoutRolesException;
import br.com.fiap.restaurant.iam.core.gateway.LoggedUserGateway;
import br.com.fiap.restaurant.iam.core.gateway.RoleGateway;
import br.com.fiap.restaurant.iam.core.gateway.UserTypeGateway;
import br.com.fiap.restaurant.iam.core.inbound.UpdateUserTypeInput;
import br.com.fiap.restaurant.iam.core.usecase.UseCaseWithoutOutput;

import java.util.Objects;
import java.util.stream.Collectors;

public class UpdateUserTypeUseCase extends UseCaseWithoutOutput<UpdateUserTypeInput> {

    private final RoleGateway roleGateway;
    private final UserTypeGateway userTypeGateway;

    public UpdateUserTypeUseCase(RoleGateway roleGateway, UserTypeGateway userTypeGateway, LoggedUserGateway loggedUserGateway) {
        super(Objects.requireNonNull(loggedUserGateway, "LoggedUserGateway cannot be null."));
        this.roleGateway = Objects.requireNonNull(roleGateway, "RoleGateway cannot be null.");
        this.userTypeGateway = Objects.requireNonNull(userTypeGateway, "UserTypeGateway cannot be null.");
    }

    @Override
    protected ForGettingRoleName getRequiredRole() {
        return UserTypeRoles.UPDATE_USER_TYPE;
    }

    @Override
    protected void doExecute(UpdateUserTypeInput input) {
        userTypeGateway.findById(input.id())
                .orElseThrow(() -> new BusinessException("User type not found."));

        var roles = roleGateway.getRolesByName(input.roles());
        if (roles.isEmpty()) throw new UserTypeWithoutRolesException();

        if (roles.size() != input.roles().size()) {
            var existingRolesNames = roles.stream()
                    .map(Role::name)
                    .collect(Collectors.toUnmodifiableSet());

            var invalidRoles = input.roles().stream()
                    .filter(role -> !existingRolesNames.contains(role))
                    .collect(Collectors.toUnmodifiableSet());

            throw new InvalidRoleException(invalidRoles);
        }

        var optionalUserType = userTypeGateway.findByName(input.name());
        optionalUserType.ifPresent(found -> {
            if (!found.getId().equals(input.id())) {
                throw new UserTypeNameIsAlreadyInUseException();
            }
        });

        userTypeGateway.save(new UserType(input.id(), input.name(), roles));
    }
}
