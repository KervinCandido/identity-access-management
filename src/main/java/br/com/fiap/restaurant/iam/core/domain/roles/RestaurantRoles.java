package br.com.fiap.restaurant.iam.core.domain.roles;

public enum RestaurantRoles implements ForGettingRoleName {
    CREATE_RESTAURANT,
    UPDATE_RESTAURANT,
    DELETE_RESTAURANT,
    VIEW_RESTAURANT,
    VIEW_RESTAURANT_MANAGEMENT;

    @Override
    public String getRoleName() {
        return name();
    }
}
