package br.com.fiap.restaurant.iam.infra.controller.response;

import java.util.List;

public record UserTypeResponse(Long id, String name, List<String> roles) {}
