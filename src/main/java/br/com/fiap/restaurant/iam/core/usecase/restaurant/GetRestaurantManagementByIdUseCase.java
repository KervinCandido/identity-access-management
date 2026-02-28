package br.com.fiap.restaurant.iam.core.usecase.restaurant;

import br.com.fiap.restaurant.iam.core.domain.model.Restaurant;
import br.com.fiap.restaurant.iam.core.domain.model.User;
import br.com.fiap.restaurant.iam.core.domain.roles.ForGettingRoleName;
import br.com.fiap.restaurant.iam.core.domain.roles.RestaurantRoles;
import br.com.fiap.restaurant.iam.core.exception.OperationNotAllowedException;
import br.com.fiap.restaurant.iam.core.gateway.LoggedUserGateway;
import br.com.fiap.restaurant.iam.core.gateway.RestaurantGateway;
import br.com.fiap.restaurant.iam.core.usecase.UseCaseBase;

import java.util.Objects;
import java.util.Optional;

public class GetRestaurantManagementByIdUseCase extends UseCaseBase<Long, Optional<Restaurant>> {

    private final RestaurantGateway restaurantGateway;

    public GetRestaurantManagementByIdUseCase(
            RestaurantGateway restaurantGateway,
            LoggedUserGateway loggedUserGateway
    ) {
        super(Objects.requireNonNull(loggedUserGateway, "LoggedUserGateway cannot be null."));
        this.restaurantGateway = Objects.requireNonNull(restaurantGateway, "RestaurantGateway cannot be null.");
    }

    @Override
    protected ForGettingRoleName getRequiredRole() {
        return RestaurantRoles.VIEW_RESTAURANT_MANAGEMENT;
    }

    @Override
    protected Optional<Restaurant> doExecute(Long id) {
        // id não chega null aqui se usar execute(), pois UseCaseBase valida input != null.

        var restaurant = restaurantGateway.findByIdWithManagement(id);

        User currentUser = loggedUserGateway.requireCurrentUser();

        if (restaurant.isPresent() && !restaurant.get().canBeManagedBy(currentUser)) {
            throw new OperationNotAllowedException(
                    "Access denied. User is neither ownerId nor employee of the restaurant."
            );
        }

        return restaurant;
    }
}
