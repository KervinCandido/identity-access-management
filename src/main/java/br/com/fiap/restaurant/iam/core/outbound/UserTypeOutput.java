package br.com.fiap.restaurant.iam.core.outbound;

import java.util.Set;

public record UserTypeOutput(Long id, String name, Set<String> roles) {}
