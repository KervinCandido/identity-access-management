package br.com.fiap.restaurant.iam.infra.vo;

public record JwtBearerToken(String type, String token, Long expiresIn, String scope) {}
