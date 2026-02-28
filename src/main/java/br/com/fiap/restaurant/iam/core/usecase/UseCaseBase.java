package br.com.fiap.restaurant.iam.core.usecase;

import br.com.fiap.restaurant.iam.core.gateway.LoggedUserGateway;

import java.util.Objects;

public abstract class UseCaseBase<T, R> extends UseCase {

    protected UseCaseBase(LoggedUserGateway loggedUserGateway) {
        super(loggedUserGateway);
    }

    public R execute(T input) {
        Objects.requireNonNull(input,  "Input cannot be null.");
        validateAccess();
        return doExecute(input);
    }

    protected abstract R doExecute(T input);
}
