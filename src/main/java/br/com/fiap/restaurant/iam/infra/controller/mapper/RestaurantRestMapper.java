package br.com.fiap.restaurant.iam.infra.controller.mapper;

import br.com.fiap.restaurant.iam.core.inbound.CreateRestaurantInput;
import br.com.fiap.restaurant.iam.core.inbound.UpdateMenuItemInput;
import br.com.fiap.restaurant.iam.core.inbound.UpdateOpeningHoursInput;
import br.com.fiap.restaurant.iam.core.inbound.UpdateRestaurantInput;
import br.com.fiap.restaurant.iam.core.outbound.RestaurantManagementOutput;
import br.com.fiap.restaurant.iam.core.outbound.RestaurantPublicOutput;
import br.com.fiap.restaurant.iam.infra.controller.request.MenuItemRequest;
import br.com.fiap.restaurant.iam.infra.controller.request.OpeningHoursRequest;
import br.com.fiap.restaurant.iam.infra.controller.request.RestaurantRequest;
import br.com.fiap.restaurant.iam.infra.controller.response.RestaurantResponse;
import br.com.fiap.restaurant.iam.infra.controller.response.RestaurantSummaryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OpeningHoursRestMapper.class, AddressRestMapper.class, MenuItemRestMapper.class, UserRestMapper.class})
public interface RestaurantRestMapper {

    CreateRestaurantInput toInput(RestaurantRequest request);

    @Mapping(target = "menu", source = "menuItems")
    RestaurantResponse toResponse(RestaurantManagementOutput output);

    @Mapping(target = "menu", source = "menuItems")
    RestaurantSummaryResponse toResponseSummary(RestaurantPublicOutput output);

    @Mapping(target = "id", expression = "java(id)")
    @Mapping(target = "owner", source = "request.ownerId")
    UpdateRestaurantInput toUpdateInput(RestaurantRequest request, Long id);

    @Mapping(target = "id", ignore = true)
    UpdateOpeningHoursInput toUpdateOpeningHoursInput(OpeningHoursRequest request);

    @Mapping(target = "id", ignore = true)
    UpdateMenuItemInput toUpdateMenuItemInput(MenuItemRequest request);
}
