package br.com.fiap.restaurant.iam.core.inbound;

import java.util.Set;
import java.util.UUID;

public record CreateRestaurantInput (
    String name,
    AddressInput address,
    String cuisineType,
    Set<OpeningHoursInput> openingHours,
    Set<MenuItemInput> menu,
    Set<UUID> employees,
    UUID ownerId
) {}
