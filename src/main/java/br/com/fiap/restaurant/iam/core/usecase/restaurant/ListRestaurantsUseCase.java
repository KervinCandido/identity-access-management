package br.com.fiap.restaurant.iam.core.usecase.restaurant;

import br.com.fiap.restaurant.iam.core.domain.model.Restaurant;
import br.com.fiap.restaurant.iam.core.domain.roles.ForGettingRoleName;
import br.com.fiap.restaurant.iam.core.domain.roles.RestaurantRoles;
import br.com.fiap.restaurant.iam.core.gateway.LoggedUserGateway;
import br.com.fiap.restaurant.iam.core.gateway.RestaurantGateway;
import br.com.fiap.restaurant.iam.core.usecase.UseCaseWithoutInput;

import java.util.List;
import java.util.Objects;

public class ListRestaurantsUseCase extends UseCaseWithoutInput<List<Restaurant>> {

    private final RestaurantGateway restaurantGateway;

    public ListRestaurantsUseCase(LoggedUserGateway loggedUserGateway, RestaurantGateway restaurantGateway) {
        super(Objects.requireNonNull(loggedUserGateway, "LoggedUserGateway cannot be null."));
        this.restaurantGateway = Objects.requireNonNull(restaurantGateway, "RestaurantGateway cannot be null.");
    }

    @Override
    protected List<Restaurant> doExecute() {
        return restaurantGateway.findAll();
    }

    @Override
    protected ForGettingRoleName getRequiredRole() {
        return RestaurantRoles.VIEW_RESTAURANT;
    }

}
