package br.com.fiap.restaurant.iam.core.usecase.restaurant;

import br.com.fiap.restaurant.iam.core.domain.model.Restaurant;
import br.com.fiap.restaurant.iam.core.domain.pagination.Page;
import br.com.fiap.restaurant.iam.core.domain.pagination.PagedQuery;
import br.com.fiap.restaurant.iam.core.domain.roles.ForGettingRoleName;
import br.com.fiap.restaurant.iam.core.domain.roles.RestaurantRoles;
import br.com.fiap.restaurant.iam.core.gateway.LoggedUserGateway;
import br.com.fiap.restaurant.iam.core.gateway.RestaurantGateway;
import br.com.fiap.restaurant.iam.core.usecase.UseCaseBase;

import java.util.Objects;

public class ListRestaurantsByCuisineTypeUseCase extends UseCaseBase<PagedQuery<String>, Page<Restaurant>> {

    private final RestaurantGateway restaurantGateway;

    public ListRestaurantsByCuisineTypeUseCase(
            LoggedUserGateway loggedUserGateway,
            RestaurantGateway restaurantGateway
    ) {
        super(Objects.requireNonNull(loggedUserGateway, "LoggedUserGateway cannot be null."));
        this.restaurantGateway = Objects.requireNonNull(restaurantGateway, "RestaurantGateway cannot be null.");
    }

    @Override
    protected Page<Restaurant> doExecute(PagedQuery<String> query) {
        // query já foi validado como não-nulo no execute()
        return restaurantGateway.findByCuisineType(query);
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
