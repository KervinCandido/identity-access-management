package br.com.fiap.restaurant.iam.infra.controller.response;

import java.util.UUID;

public record UserSummaryResponse(UUID id, String name) {}
