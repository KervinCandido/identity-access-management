package br.com.fiap.restaurant.iam.infra.config;

import br.com.fiap.restaurant.iam.core.controller.*;
import br.com.fiap.restaurant.iam.core.usecase.role.ListRolesUseCase;
import br.com.fiap.restaurant.iam.core.usecase.user.*;
import br.com.fiap.restaurant.iam.core.usecase.usertype.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreControllersConfig {

    @Bean
    public RoleController roleController(ListRolesUseCase listRolesUseCase) {
        return new RoleController(listRolesUseCase);
    }

    @Bean
    public UserTypeController userTypeController(
            CreateUserTypeUseCase createUserTypeUseCase,
            UpdateUserTypeUseCase updateUserTypeUseCase,
            DeleteUserTypeUseCase deleteUserTypeUseCase,
            GetUserTypeByIdUseCase getUserTypeByIdUseCase,
            ListUserTypesUseCase listUserTypesUseCase
    ) {
        return new UserTypeController(
                createUserTypeUseCase,
                updateUserTypeUseCase,
                deleteUserTypeUseCase,
                getUserTypeByIdUseCase,
                listUserTypesUseCase
        );
    }

    @Bean
    public UserController userController(
            CreateUserUseCase createUserUseCase,
            UpdateUserUseCase updateUserUseCase,
            DeleteUserUseCase deleteUserUseCase,
            GetUserByIdUseCase getUserByIdUseCase,
            ListUsersUseCase listUsersUseCase
    ) {
        return new UserController(
                createUserUseCase,
                updateUserUseCase,
                deleteUserUseCase,
                getUserByIdUseCase,
                listUsersUseCase
        );
    }
}


