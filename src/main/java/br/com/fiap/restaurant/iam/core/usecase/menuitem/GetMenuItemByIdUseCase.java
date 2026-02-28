package br.com.fiap.restaurant.iam.core.usecase.menuitem;

import br.com.fiap.restaurant.iam.core.domain.model.MenuItem;
import br.com.fiap.restaurant.iam.core.domain.roles.ForGettingRoleName;
import br.com.fiap.restaurant.iam.core.domain.roles.MenuItemRoles;
import br.com.fiap.restaurant.iam.core.gateway.LoggedUserGateway;
import br.com.fiap.restaurant.iam.core.gateway.MenuItemGateway;
import br.com.fiap.restaurant.iam.core.usecase.UseCaseBase;

import java.util.Objects;
import java.util.Optional;

public class GetMenuItemByIdUseCase extends UseCaseBase<Long, Optional<MenuItem>> {

    private final MenuItemGateway menuItemGateway;

    public GetMenuItemByIdUseCase(MenuItemGateway menuItemGateway, LoggedUserGateway loggedUserGateway) {
        super(loggedUserGateway);
        Objects.requireNonNull(menuItemGateway, "MenuItemGateway cannot be null");
        this.menuItemGateway = menuItemGateway;
    }

    @Override
    protected Optional<MenuItem> doExecute(Long id) {
        return menuItemGateway.findById(id);
    }

    @Override
    protected ForGettingRoleName getRequiredRole() {
        return MenuItemRoles.VIEW_MENU_ITEM;
    }

    @Override
    protected boolean isPublicAccessAllowed() {
        return true;
    }
}
