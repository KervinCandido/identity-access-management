package br.com.fiap.restaurant.iam.core.exception;

public class UserTypeNameIsAlreadyInUseException extends BusinessException {
    public UserTypeNameIsAlreadyInUseException() {
        super("User type name is already in use.");
    }
}
