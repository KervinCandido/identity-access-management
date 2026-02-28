package br.com.fiap.restaurant.iam.infra.controller.mapper;

import br.com.fiap.restaurant.iam.core.inbound.CreateMenuItemInput;
import br.com.fiap.restaurant.iam.core.inbound.UpdateMenuItemInput;
import br.com.fiap.restaurant.iam.core.outbound.MenuItemOutput;
import br.com.fiap.restaurant.iam.infra.controller.request.MenuItemRequest;
import br.com.fiap.restaurant.iam.infra.controller.response.MenuItemResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MenuItemRestMapper {
    MenuItemResponse toResponse(MenuItemOutput output);

    @Mapping(target = "restaurantId", expression = "java(restaurantId)")
    CreateMenuItemInput toCreateInput(MenuItemRequest menuItemRequest, Long restaurantId);

    @Mapping(target = "id", expression = "java(id)")
    UpdateMenuItemInput toUpdateInput(MenuItemRequest menuItemRequest, Long id);
}
