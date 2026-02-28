package br.com.fiap.restaurant.iam.core.exception;

public class UserCannotBeRestaurantOwnerException extends BusinessException {

    public UserCannotBeRestaurantOwnerException() {
        super("User cannot be restaurant ownerId.");
    }
}
