package br.com.fiap.restaurant.iam.core.usecase.restaurant;

import br.com.fiap.restaurant.iam.core.domain.model.Restaurant;
import br.com.fiap.restaurant.iam.core.domain.pagination.*;
import br.com.fiap.restaurant.iam.core.domain.roles.*;
import br.com.fiap.restaurant.iam.core.gateway.*;
import br.com.fiap.restaurant.iam.core.usecase.UseCaseBase;

import java.util.Objects;

public class ListRestaurantsPagedUseCase extends UseCaseBase<PagedQuery<Void>, Page<Restaurant>> {
    private final RestaurantGateway restaurantGateway;

    public ListRestaurantsPagedUseCase(RestaurantGateway restaurantGateway, LoggedUserGateway loggedUserGateway) {
        super(loggedUserGateway);
        this.restaurantGateway = Objects.requireNonNull(restaurantGateway, "RestaurantGateway cannot be null.");
    }

    @Override
    protected Page<Restaurant> doExecute(PagedQuery<Void> query) {
        return restaurantGateway.findAll(query);
    }

    @Override
    protected ForGettingRoleName getRequiredRole() {
        return RestaurantRoles.VIEW_RESTAURANT;
    }

    @Override
    protected boolean isPublicAccessAllowed() {
        return true;
    }
}
