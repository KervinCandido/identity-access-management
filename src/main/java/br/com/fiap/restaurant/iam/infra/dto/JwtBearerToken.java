package br.com.fiap.restaurant.iam.infra.dto;

public record JwtBearerToken(String type, String token, Long expiresIn, String scope) {}
