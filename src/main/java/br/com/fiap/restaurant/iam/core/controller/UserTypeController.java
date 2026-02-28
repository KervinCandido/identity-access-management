package br.com.fiap.restaurant.iam.core.controller;

import br.com.fiap.restaurant.iam.core.inbound.CreateUserTypeInput;
import br.com.fiap.restaurant.iam.core.inbound.UpdateUserTypeInput;
import br.com.fiap.restaurant.iam.core.outbound.UserTypeOutput;
import br.com.fiap.restaurant.iam.core.presenter.UserTypePresenter;
import br.com.fiap.restaurant.iam.core.usecase.usertype.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class UserTypeController {

    private final CreateUserTypeUseCase createUserTypeUseCase;
    private final UpdateUserTypeUseCase updateUserTypeUseCase;
    private final DeleteUserTypeUseCase deleteUserTypeUseCase;
    private final GetUserTypeByIdUseCase getUserTypeByIdUseCase;
    private final ListUserTypesUseCase listUserTypesUseCase;

    public UserTypeController (
            CreateUserTypeUseCase createUserTypeUseCase,
            UpdateUserTypeUseCase updateUserTypeUseCase,
            DeleteUserTypeUseCase deleteUserTypeUseCase,
            GetUserTypeByIdUseCase getUserTypeByIdUseCase,
            ListUserTypesUseCase listUserTypesUseCase
    ) {
        Objects.requireNonNull(createUserTypeUseCase, "CreateUserTypeUseCase cannot be null.");
        Objects.requireNonNull(updateUserTypeUseCase, "UpdateUserTypeUseCase cannot be null.");
        Objects.requireNonNull(deleteUserTypeUseCase, "DeleteUserTypeUseCase cannot be null.");
        Objects.requireNonNull(getUserTypeByIdUseCase, "GetByIdUserTypeUseCase cannot be null.");
        Objects.requireNonNull(listUserTypesUseCase, "GetAllUserTypesUseCase cannot be null.");
        this.createUserTypeUseCase = createUserTypeUseCase;
        this.updateUserTypeUseCase = updateUserTypeUseCase;
        this.deleteUserTypeUseCase = deleteUserTypeUseCase;
        this.getUserTypeByIdUseCase = getUserTypeByIdUseCase;
        this.listUserTypesUseCase = listUserTypesUseCase;
    }

    public UserTypeOutput createUserType(CreateUserTypeInput input) {
        var userType = createUserTypeUseCase.execute(input);
        return UserTypePresenter.toOutput(userType);
    }

    public void updateUserType(UpdateUserTypeInput input) {
        updateUserTypeUseCase.execute(input);
    }

    public void deleteUserType(Long id) {
        deleteUserTypeUseCase.execute(id);
    }

    public Optional<UserTypeOutput> getUserTypeById(Long id) {
        var result = getUserTypeByIdUseCase.execute(id);
        return result.map(UserTypePresenter::toOutput);
    }

    public List<UserTypeOutput> listAllUserTypes() {
        var userTypes = listUserTypesUseCase.execute();
        return userTypes.stream().map(UserTypePresenter::toOutput).toList();
    }
}
