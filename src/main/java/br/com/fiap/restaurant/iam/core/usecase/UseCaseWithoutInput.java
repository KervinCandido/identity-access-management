package br.com.fiap.restaurant.iam.core.usecase;

import br.com.fiap.restaurant.iam.core.gateway.LoggedUserGateway;

public abstract class UseCaseWithoutInput<T> extends UseCase {

    protected UseCaseWithoutInput(LoggedUserGateway loggedUserGateway) {
        super(loggedUserGateway);
    }

    public T execute() {
        validateAccess();
        return doExecute();
    }

    protected abstract T doExecute();
}
