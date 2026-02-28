package br.com.fiap.restaurant.iam.core.usecase.restaurant;

import br.com.fiap.restaurant.iam.core.domain.model.User;
import br.com.fiap.restaurant.iam.core.domain.roles.ForGettingRoleName;
import br.com.fiap.restaurant.iam.core.domain.roles.RestaurantRoles;
import br.com.fiap.restaurant.iam.core.exception.BusinessException;
import br.com.fiap.restaurant.iam.core.exception.OperationNotAllowedException;
import br.com.fiap.restaurant.iam.core.gateway.LoggedUserGateway;
import br.com.fiap.restaurant.iam.core.gateway.RestaurantGateway;
import br.com.fiap.restaurant.iam.core.usecase.UseCaseWithoutOutput;

import java.util.Objects;

public class DeleteRestaurantUseCase extends UseCaseWithoutOutput<Long> {

    private final RestaurantGateway restaurantGateway;

    public DeleteRestaurantUseCase(
            LoggedUserGateway loggedUserGateway,
            RestaurantGateway restaurantGateway
    ) {
        super(Objects.requireNonNull(loggedUserGateway, "LoggedUserGateway cannot be null."));
        this.restaurantGateway = Objects.requireNonNull(restaurantGateway, "RestaurantGateway cannot be null.");
    }

    @Override
    protected ForGettingRoleName getRequiredRole() {
        return RestaurantRoles.DELETE_RESTAURANT;
    }

    @Override
    protected void doExecute(Long id) {
        var restaurant = restaurantGateway.findById(id)
                .orElseThrow(() -> new BusinessException("Restaurant not found."));

        User currentUser = loggedUserGateway.requireCurrentUser();
        if (!restaurant.canBeManagedBy(currentUser)) {
            throw new OperationNotAllowedException();
        }

        restaurantGateway.delete(id);
    }
}
