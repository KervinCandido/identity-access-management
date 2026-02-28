package br.com.fiap.restaurant.iam.core.exception;

public class UserNotAuthenticatedException extends BusinessException{
    public UserNotAuthenticatedException() {
        super("User is not authenticated.");
    }
}
