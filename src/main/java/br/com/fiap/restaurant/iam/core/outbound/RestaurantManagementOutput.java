package br.com.fiap.restaurant.iam.core.outbound;

import java.util.Set;

public record RestaurantManagementOutput (
    Long id,
    String name,
    AddressOutput address,
    String cuisineType,
    Set<OpeningHoursOutput> openingHours,
    Set<MenuItemOutput> menuItems,
    Set<UserSummaryOutput> employees,
    UserSummaryOutput owner
){}
