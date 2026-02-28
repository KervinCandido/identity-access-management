package br.com.fiap.restaurant.iam.infra.auth;


import br.com.fiap.restaurant.iam.core.domain.model.User;

public class FakeLoggedUserContext {
    private final User user;

    public FakeLoggedUserContext(User user) {
        this.user = user;
    }

    public User get() { return user; }
}
