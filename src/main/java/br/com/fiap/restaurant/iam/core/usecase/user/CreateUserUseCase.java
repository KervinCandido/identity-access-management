package br.com.fiap.restaurant.iam.core.usecase.user;

import br.com.fiap.restaurant.iam.core.domain.model.User;
import br.com.fiap.restaurant.iam.core.domain.model.valueobject.Address;
import br.com.fiap.restaurant.iam.core.domain.roles.UserManagementRoles;
import br.com.fiap.restaurant.iam.core.exception.BusinessException;
import br.com.fiap.restaurant.iam.core.exception.OperationNotAllowedException;
import br.com.fiap.restaurant.iam.core.gateway.*;
import br.com.fiap.restaurant.iam.core.inbound.CreateUserInput;

import java.util.Objects;

public class CreateUserUseCase {

    private final LoggedUserGateway loggedUserGateway;
    private final UserGateway userGateway;
    private final UserTypeGateway userTypeGateway;
    private final PasswordHasherGateway passwordHasherGateway;
    private final EventPublisher<User> createUserEventPublisher;

    public CreateUserUseCase(UserGateway userGateway, UserTypeGateway userTypeGateway,
                             LoggedUserGateway loggedUserGateway, PasswordHasherGateway passwordHasherGateway,
                             EventPublisher<User> createUserEventPublisher) {
        this.loggedUserGateway = Objects.requireNonNull(loggedUserGateway, "loggedUserGateway must not be null");
        this.userGateway = Objects.requireNonNull(userGateway, "userGateway must not be null");
        this.userTypeGateway = Objects.requireNonNull(userTypeGateway, "userTypeGateway must not be null");
        this.passwordHasherGateway = Objects.requireNonNull(passwordHasherGateway, "passwordHasherGateway must not be null");
        this.createUserEventPublisher = Objects.requireNonNull(createUserEventPublisher, "createUserEventPublisher must not be null");
    }

    public User execute(CreateUserInput input) {
        Objects.requireNonNull(input,  "CreateUserInput cannot be null.");
        boolean isAllowed = loggedUserGateway.hasRole(UserManagementRoles.CREATE_USER);

        if (!isAllowed)
            throw new OperationNotAllowedException("The current user does not have permission to perform this action.");

        if (input.password() == null || input.password().trim().isBlank()) {
            throw new BusinessException("Password cannot be blank.");
        }

        if (userGateway.existsUserWithEmail(input.email().strip())) {
            throw new BusinessException("Email is already in use.");
        }

        if (userGateway.existsUserWithUserName(input.username().strip())) {
            throw new BusinessException("UserName is already in use.");
        }

        var userType = userTypeGateway.findById(input.userTypeId())
                .orElseThrow(() -> new BusinessException("User type with ID " + input.userTypeId() + " not found."));

        Address address = input.address() == null ? null : new Address(
                input.address().street(),
                input.address().number(),
                input.address().city(),
                input.address().state(),
                input.address().zipCode(),
                input.address().complement()
        );

        String passwordHash = passwordHasherGateway.hash(input.password().strip());

        var user = new User (
            null,
            input.name().strip(),
            input.username().strip(),
            input.email().strip(),
            address,
            userType,
            passwordHash
        );

        User newUser = userGateway.save(user);
        createUserEventPublisher.publish(newUser);
        return newUser;
    }
}
