package br.com.fiap.restaurant.iam.core.exception;

public class RestaurantNameIsAlreadyInUseException extends BusinessException {
    public RestaurantNameIsAlreadyInUseException() {
        super("Restaurant name is already in use.");
    }
}
