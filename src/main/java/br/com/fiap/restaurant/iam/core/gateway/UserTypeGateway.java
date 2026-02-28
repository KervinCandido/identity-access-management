package br.com.fiap.restaurant.iam.core.gateway;

import br.com.fiap.restaurant.iam.core.domain.model.UserType;

import java.util.Optional;
import java.util.Set;

public interface UserTypeGateway {
    UserType save(UserType userType);
    boolean existsUserTypeWithName(String name);
    Optional<UserType> findByName(String name);
    Optional<UserType> findById(Long id);
    void delete(Long id);
    boolean isInUse(Long id);
    Set<UserType> findAll();
}
