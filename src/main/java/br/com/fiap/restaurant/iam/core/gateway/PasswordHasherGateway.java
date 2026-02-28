package br.com.fiap.restaurant.iam.core.gateway;

public interface PasswordHasherGateway {
    String hash(String rawPassword);
    boolean matches(String rawPassword, String hashedPassword);
}
