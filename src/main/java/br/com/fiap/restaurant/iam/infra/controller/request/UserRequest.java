package br.com.fiap.restaurant.iam.infra.controller.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRequest {
    @NotBlank
    @Size(min = 3, max = 255)
    private String name;

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Email
    private String email;

    @Valid
    private AddressRequest address;

    @NotNull
    @Positive
    private Long userTypeId;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;
}
