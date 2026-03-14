package br.com.fiap.restaurant.iam.core.usecase.user;

import br.com.fiap.restaurant.iam.core.domain.model.User;
import br.com.fiap.restaurant.iam.core.domain.roles.UserManagementRoles;
import br.com.fiap.restaurant.iam.core.exception.OperationNotAllowedException;
import br.com.fiap.restaurant.iam.core.exception.ResourceNotFoundException;
import br.com.fiap.restaurant.iam.core.gateway.EventPublisher;
import br.com.fiap.restaurant.iam.core.gateway.LoggedUserGateway;
import br.com.fiap.restaurant.iam.core.gateway.UserGateway;

import java.util.Objects;
import java.util.UUID;

public class DeleteUserUseCase {

    private final UserGateway userGateway;
    private final LoggedUserGateway loggedUserGateway;
    private final EventPublisher<User> deleteUserEventPublisher;

    public DeleteUserUseCase(UserGateway userGateway, LoggedUserGateway loggedUserGateway, EventPublisher<User> deleteUserEventPublisher) {
        this.loggedUserGateway = Objects.requireNonNull(loggedUserGateway, "loggedUserGateway must not be null");
        this.deleteUserEventPublisher = Objects.requireNonNull(deleteUserEventPublisher, "deleteUserEventPublisher must not be null");
        this.userGateway = Objects.requireNonNull(userGateway, "userGateway cannot be null");
    }

    public void execute(UUID userId) {
        Objects.requireNonNull(userId,  "userId cannot be null");

        boolean isAllowed = loggedUserGateway.hasRole(UserManagementRoles.DELETE_USER);

        if (!isAllowed)
            throw new OperationNotAllowedException("The current user does not have permission to perform this action.");

        var user = userGateway.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with ID " + userId + " not found."));
        userGateway.deleteById(userId);
        deleteUserEventPublisher.publish(user);
    }
}
