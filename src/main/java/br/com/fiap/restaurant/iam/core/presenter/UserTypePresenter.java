package br.com.fiap.restaurant.iam.core.presenter;

import br.com.fiap.restaurant.iam.core.domain.model.Role;
import br.com.fiap.restaurant.iam.core.domain.model.UserType;
import br.com.fiap.restaurant.iam.core.outbound.UserTypeOutput;

import java.util.stream.Collectors;

public class UserTypePresenter {

    private UserTypePresenter() {}

    public static UserTypeOutput toOutput(UserType userType) {
        return new UserTypeOutput (
            userType.getId(),
            userType.getName(),
            userType.getRoles()
                    .stream()
                    .map(Role::name)
                    .collect(Collectors.toUnmodifiableSet())
        );
    }
}
