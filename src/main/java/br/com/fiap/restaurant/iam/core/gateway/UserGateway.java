package br.com.fiap.restaurant.iam.core.gateway;

import br.com.fiap.restaurant.iam.core.domain.model.User;
import br.com.fiap.restaurant.iam.core.domain.pagination.Page;
import br.com.fiap.restaurant.iam.core.domain.pagination.PagedQuery;

import java.util.Optional;
import java.util.UUID;

public interface UserGateway {
    Optional<User> findById(UUID uuid);

    boolean existsUserWithEmail(String email);

    boolean existsUserWithUserName(String userName);

    User save(User user);

    Page<User> findAll(PagedQuery<Void> input);


    void deleteById(UUID id);
}
