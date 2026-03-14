package br.com.fiap.restaurant.iam.core.usecase.user;

import br.com.fiap.restaurant.iam.core.domain.model.User;
import br.com.fiap.restaurant.iam.core.domain.model.valueobject.Address;
import br.com.fiap.restaurant.iam.core.domain.roles.UserManagementRoles;
import br.com.fiap.restaurant.iam.core.exception.BusinessException;
import br.com.fiap.restaurant.iam.core.exception.OperationNotAllowedException;
import br.com.fiap.restaurant.iam.core.exception.ResourceNotFoundException;
import br.com.fiap.restaurant.iam.core.gateway.EventPublisher;
import br.com.fiap.restaurant.iam.core.gateway.LoggedUserGateway;
import br.com.fiap.restaurant.iam.core.gateway.UserGateway;
import br.com.fiap.restaurant.iam.core.gateway.UserTypeGateway;
import br.com.fiap.restaurant.iam.core.inbound.UpdateUserInput;

import java.util.Objects;
import java.util.UUID;

public class UpdateUserUseCase {

    private final LoggedUserGateway loggedUserGateway;
    private final UserGateway userGateway;
    private final UserTypeGateway userTypeGateway;
    private final EventPublisher<User> updateUserEventPublisher;

    public UpdateUserUseCase(UserGateway userGateway, UserTypeGateway userTypeGateway, LoggedUserGateway loggedUserGateway, EventPublisher<User> updateUserEventPublisher) {
        this.loggedUserGateway = Objects.requireNonNull(loggedUserGateway, "loggedUserGateway cannot be null");
        this.userGateway = Objects.requireNonNull(userGateway, "userGateway cannot be null");
        this.userTypeGateway = Objects.requireNonNull(userTypeGateway, "userTypeGateway cannot be null");
        this.updateUserEventPublisher = Objects.requireNonNull(updateUserEventPublisher, "updateUserEventPublisher cannot be null");
    }

    public void execute(UpdateUserInput input) {
        Objects.requireNonNull(input,  "UpdateUserInput cannot be null.");
        boolean isAllowed = loggedUserGateway.hasRole(UserManagementRoles.UPDATE_USER);

        if (!isAllowed)
            throw new OperationNotAllowedException("The current user does not have permission to perform this action.");

        UUID id = Objects.requireNonNull(input.id(), "User UUID cannot be null.");
        User currentUser = userGateway.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found."));

        String newName = input.name() == null ? currentUser.getName() : input.name().trim();
        String newEmail = input.email() == null ? currentUser.getEmail() : input.email().trim();
        String newUsername = input.username() == null ? currentUser.getUsername() :  input.username().trim();

        if (newEmail.isBlank()) {
            throw new BusinessException("Email cannot be blank.");
        }

        // Se mudar o email, verificar se já existe outro usuário com esse email
        if (!currentUser.getEmail().equalsIgnoreCase(newEmail)
            && userGateway.existsUserWithEmail(newEmail)) {
            throw new BusinessException("Email " + newEmail + " is already in use.");
        }

        if (!currentUser.getUsername().equals(newUsername) && userGateway.existsUserWithUserName(newUsername)) {
            throw new BusinessException("UserName is already in use.");
        }

        Address newAddress = currentUser.getAddress();
        if (input.address() != null) {
            newAddress = new Address(
                    input.address().street(),
                    input.address().number(),
                    input.address().city(),
                    input.address().state(),
                    input.address().zipCode(),
                    input.address().complement()
            );
        }

        var newUserType = currentUser.getUserType();
        if (input.userTypeId() != null && !Objects.equals(input.userTypeId(), currentUser.getUserType().getId())) {
            newUserType = userTypeGateway.findById(input.userTypeId())
                    .orElseThrow(() -> new BusinessException("User type with ID " + input.userTypeId() + " not found."));
        }

        User updatedUser = new User(
                currentUser.getId(),
                newName,
                newUsername,
                newEmail,
                newAddress,
                newUserType,
                currentUser.getPasswordHash()   // senha não é alterada aqui (fluxo separado para isso)
        );

        User savedUser = userGateway.save(updatedUser);
        updateUserEventPublisher.publish(savedUser);
    }

}
