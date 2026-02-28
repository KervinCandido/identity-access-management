package br.com.fiap.restaurant.iam.core.usecase.restaurant;

import br.com.fiap.restaurant.iam.core.domain.model.Restaurant;
import br.com.fiap.restaurant.iam.core.domain.roles.ForGettingRoleName;
import br.com.fiap.restaurant.iam.core.domain.roles.RestaurantRoles;
import br.com.fiap.restaurant.iam.core.gateway.LoggedUserGateway;
import br.com.fiap.restaurant.iam.core.gateway.RestaurantGateway;
import br.com.fiap.restaurant.iam.core.usecase.UseCaseBase;

import java.util.Objects;
import java.util.Optional;

public class GetRestaurantByIdUseCase extends UseCaseBase<Long, Optional<Restaurant>> {

    private final RestaurantGateway restaurantGateway;

    public GetRestaurantByIdUseCase(
            LoggedUserGateway loggedUserGateway,
            RestaurantGateway restaurantGateway
    ) {
        super(Objects.requireNonNull(loggedUserGateway, "LoggedUserGateway cannot be null."));
        this.restaurantGateway = Objects.requireNonNull(restaurantGateway, "RestaurantGateway cannot be null.");
    }

    @Override
    protected ForGettingRoleName getRequiredRole() {
        return RestaurantRoles.VIEW_RESTAURANT;
    }

    @Override
    protected Optional<Restaurant> doExecute(Long id) {
        // aqui id NÃO será null, pois UseCaseBase.execute já valida input != null
        return restaurantGateway.findById(id);
    }

    @Override
    protected boolean isPublicAccessAllowed() {
        return true;
    }
}
