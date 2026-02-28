package br.com.fiap.restaurant.iam.infra.controller.response;

public record AddressResponse (
        String street,
        String number,
        String city,
        String state,
        String zipCode,
        String complement
) {}
