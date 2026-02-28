package br.com.fiap.restaurant.iam.core.inbound;

import java.math.BigDecimal;

public record UpdateMenuItemInput(
        Long id,
        String name,
        String description,
        BigDecimal price,
        Boolean restaurantOnly,
        String photoPath
) {}
