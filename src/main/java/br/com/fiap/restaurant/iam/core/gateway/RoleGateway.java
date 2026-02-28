package br.com.fiap.restaurant.iam.core.gateway;

import br.com.fiap.restaurant.iam.core.domain.model.Role;

import java.util.Set;

public interface RoleGateway {
    Set<Role> getRolesByName(Set<String> rolesName);
    Set<Role> findAll();
}
