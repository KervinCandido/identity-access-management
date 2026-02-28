package br.com.fiap.restaurant.iam.core.exception;

public class UserTypeInUseException extends BusinessException {

    public UserTypeInUseException() {
        super("The user type is in use and cannot be deleted.");
    }
}
