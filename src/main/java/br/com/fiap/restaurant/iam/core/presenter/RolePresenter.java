package br.com.fiap.restaurant.iam.core.presenter;

import br.com.fiap.restaurant.iam.core.domain.model.Role;
import br.com.fiap.restaurant.iam.core.outbound.RoleOutput;

public class RolePresenter {
    private RolePresenter() {}

    public static RoleOutput toOutput(Role role) {
        return new RoleOutput(role.id(), role.name());
    }
}
