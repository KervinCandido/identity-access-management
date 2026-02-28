package br.com.fiap.restaurant.iam.core.outbound;

import java.math.BigDecimal;

public record MenuItemOutput(
    Long id,
    String name,
    String description,
    BigDecimal price,
    Boolean restaurantOnly,
    String photoPath,
    Long restaurantId
) {}
