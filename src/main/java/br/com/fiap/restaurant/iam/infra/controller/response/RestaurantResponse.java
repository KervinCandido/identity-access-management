package br.com.fiap.restaurant.iam.infra.controller.response;


import br.com.fiap.restaurant.iam.infra.controller.request.OpeningHoursRequest;

import java.util.List;

public record RestaurantResponse (
        Long id,
        String name,
        String cuisineType,
        AddressResponse address,
        UserSummaryResponse owner,
        List<OpeningHoursRequest> openingHours,
        List<MenuItemResponse> menu,
        List<UserSummaryResponse> employees
){}
