package br.com.fiap.restaurant.iam.infra.config;


import br.com.fiap.restaurant.iam.core.controller.*;
import br.com.fiap.restaurant.iam.core.usecase.menuitem.*;
import br.com.fiap.restaurant.iam.core.usecase.restaurant.*;
import br.com.fiap.restaurant.iam.core.usecase.role.ListRolesUseCase;
import br.com.fiap.restaurant.iam.core.usecase.user.*;
import br.com.fiap.restaurant.iam.core.usecase.usertype.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreControllersConfig {

    @Bean
    public RestaurantController restaurantController(
            CreateRestaurantUseCase createRestaurantUseCase,
            UpdateRestaurantUseCase updateRestaurantUseCase,
            GetRestaurantByIdUseCase getRestaurantByIdUseCase,
            ListRestaurantsUseCase listRestaurantsUseCase,
            DeleteRestaurantUseCase deleteRestaurantUseCase,
            ListRestaurantsByCuisineTypeUseCase listRestaurantsByCuisineTypeUseCase,
            GetRestaurantManagementByIdUseCase getRestaurantManagementByIdUseCase,
            ListRestaurantsPagedUseCase listRestaurantsPagedUseCase
    ) {
        return new RestaurantController(
                createRestaurantUseCase,
                updateRestaurantUseCase,
                getRestaurantByIdUseCase,
                listRestaurantsUseCase,
                deleteRestaurantUseCase,
                listRestaurantsByCuisineTypeUseCase,
                getRestaurantManagementByIdUseCase,
                listRestaurantsPagedUseCase
        );
    }

    @Bean
    public MenuItemController menuItemController(ListMenuItemsByRestaurantUseCase listMenuItemsByRestaurantUseCase,
                                                 CreateMenuItemUseCase createMenuItemUseCase,
                                                 UpdateMenuItemUseCase updateMenuItemUseCase,
                                                 DeleteMenuItemUseCase deleteMenuItemUseCase,
                                                 GetMenuItemByIdUseCase getMenuItemByIdUseCase) {
        return new MenuItemController(listMenuItemsByRestaurantUseCase, createMenuItemUseCase, updateMenuItemUseCase, deleteMenuItemUseCase, getMenuItemByIdUseCase);
    }

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


