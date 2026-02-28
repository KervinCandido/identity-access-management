package br.com.fiap.restaurant.iam.core.inbound;

public record AddressInput(String street, String number, String city, String state, String zipCode, String complement) {}
