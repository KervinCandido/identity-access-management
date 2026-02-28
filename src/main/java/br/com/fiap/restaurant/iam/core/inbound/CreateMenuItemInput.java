package br.com.fiap.restaurant.iam.core.inbound;

import java.math.BigDecimal;

public record CreateMenuItemInput (
        String name,
        String description,
        BigDecimal price,
        Boolean restaurantOnly,
        String photoPath,
        Long restaurantId  // <-- Associar o item ao restaurante
) {}
