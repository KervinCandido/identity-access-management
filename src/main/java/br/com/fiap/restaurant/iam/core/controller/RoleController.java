package br.com.fiap.restaurant.iam.core.controller;

import br.com.fiap.restaurant.iam.core.outbound.RoleOutput;
import br.com.fiap.restaurant.iam.core.presenter.RolePresenter;
import br.com.fiap.restaurant.iam.core.usecase.role.ListRolesUseCase;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class RoleController {

    private final ListRolesUseCase listRolesUseCase;

    public RoleController(ListRolesUseCase listRolesUseCase) {
        Objects.requireNonNull(listRolesUseCase, "GetAllRolesUseCase cannot be null.");
        this.listRolesUseCase = listRolesUseCase;
    }

    public Set<RoleOutput> getAllRoles() {
        var roles = listRolesUseCase.execute();
        return roles.stream().map(RolePresenter::toOutput).collect(Collectors.toSet());
    }
}
