package br.com.fiap.restaurant.iam.core.inbound;

import java.util.Set;

public record CreateUserTypeInput(String name, Set<String> roles) {}