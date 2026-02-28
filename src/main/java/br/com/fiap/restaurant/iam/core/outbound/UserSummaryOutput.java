package br.com.fiap.restaurant.iam.core.outbound;

import java.util.UUID;

public record UserSummaryOutput(
    UUID id,
    String name
) {}
