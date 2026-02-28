package br.com.fiap.restaurant.iam.core.presenter;

import br.com.fiap.restaurant.iam.core.domain.model.MenuItem;
import br.com.fiap.restaurant.iam.core.outbound.MenuItemOutput;

public class MenuItemPresenter {

    private MenuItemPresenter() {}  // Construtor privado para utilitária

    public static MenuItemOutput toOutput(MenuItem menuItem, Long restantId) {
        return new MenuItemOutput(
                menuItem.getId(),
                menuItem.getName(),
                menuItem.getDescription(),
                menuItem.getPrice(),
                menuItem.getRestaurantOnly(),
                menuItem.getPhotoPath(),
                restantId
        );
    }
}