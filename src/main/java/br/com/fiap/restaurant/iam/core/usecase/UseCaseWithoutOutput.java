package br.com.fiap.restaurant.iam.core.usecase;

import br.com.fiap.restaurant.iam.core.gateway.LoggedUserGateway;

import java.util.Objects;

public abstract class UseCaseWithoutOutput<T> extends UseCase {

    protected UseCaseWithoutOutput(LoggedUserGateway loggedUserGateway) {
        super(loggedUserGateway);
    }

    public void execute(T input) {
        Objects.requireNonNull(input,  "Input cannot be null.");
        validateAccess();
        doExecute(input);
    }

    protected abstract void doExecute(T input);
}
