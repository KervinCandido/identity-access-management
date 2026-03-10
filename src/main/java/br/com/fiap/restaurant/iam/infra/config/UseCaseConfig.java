package br.com.fiap.restaurant.iam.infra.config;

import br.com.fiap.restaurant.iam.core.gateway.*;
import br.com.fiap.restaurant.iam.core.usecase.role.ListRolesUseCase;
import br.com.fiap.restaurant.iam.core.usecase.user.*;
import br.com.fiap.restaurant.iam.core.usecase.usertype.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    /* ============================
       ROLES
       ============================ */

    @Bean
    public ListRolesUseCase listRolesUseCase(LoggedUserGateway loggedUserGateway, RoleGateway roleGateway) {
        return new ListRolesUseCase(loggedUserGateway, roleGateway);
    }

    /* ============================
       USER TYPE
       ============================ */

    @Bean
    public CreateUserTypeUseCase createUserTypeUseCase(
            RoleGateway roleGateway,
            UserTypeGateway userGateway,
            LoggedUserGateway loggedUserGateway
    ) {
        return new CreateUserTypeUseCase(roleGateway, userGateway, loggedUserGateway);
    }

    @Bean
    public UpdateUserTypeUseCase updateUserTypeUseCase(
            RoleGateway roleGateway,
            UserTypeGateway userGateway,
            LoggedUserGateway loggedUserGateway
    ) {
        return new UpdateUserTypeUseCase(roleGateway, userGateway, loggedUserGateway);
    }

    @Bean
    public DeleteUserTypeUseCase deleteUserTypeUseCase(
            UserTypeGateway userTypeGateway,
            LoggedUserGateway loggedUserGateway
    ) {
        return new DeleteUserTypeUseCase(userTypeGateway, loggedUserGateway);
    }

    @Bean
    public GetUserTypeByIdUseCase getUserTypeByIdUseCase(LoggedUserGateway loggedUserGateway, UserTypeGateway userTypeGateway) {
        return new GetUserTypeByIdUseCase(userTypeGateway, loggedUserGateway);
    }

    @Bean
    public ListUserTypesUseCase listUserTypesUseCase(UserTypeGateway userTypeGateway, LoggedUserGateway loggedUserGateway) {
        return new ListUserTypesUseCase(userTypeGateway, loggedUserGateway);
    }

    /* ============================
       USER
       ============================ */

    @Bean
    public CreateUserUseCase createUserUseCase(
            UserGateway userGateway,
            UserTypeGateway userTypeGateway,
            LoggedUserGateway loggedUserGateway,
            PasswordHasherGateway passwordHasherGateway
    ) {
        return new CreateUserUseCase(
                userGateway,
                userTypeGateway,
                loggedUserGateway,
                passwordHasherGateway
        );
    }

    @Bean
    public UpdateUserUseCase updateUserUseCase(
            UserGateway userGateway,
            UserTypeGateway userTypeGateway,
            LoggedUserGateway loggedUserGateway
    ) {
        return new UpdateUserUseCase(userGateway, userTypeGateway, loggedUserGateway);
    }

    @Bean
    public DeleteUserUseCase deleteUserUseCase(
            UserGateway userGateway,
            LoggedUserGateway loggedUserGateway
    ) {
        return new DeleteUserUseCase(userGateway, loggedUserGateway);
    }

    @Bean
    public GetUserByIdUseCase getUserByIdUseCase(
            UserGateway userGateway,
            LoggedUserGateway loggedUserGateway
    ) {
        return new GetUserByIdUseCase(userGateway, loggedUserGateway);
    }

    @Bean
    public ListUsersUseCase listUsersUseCase(
            UserGateway userGateway,
            LoggedUserGateway loggedUserGateway
    ) {
        return new ListUsersUseCase(userGateway, loggedUserGateway);
    }
}

