package br.com.emendes.powerkrtestapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthenticationRequest(
    @NotBlank(message = "email must not be blank")
    @Email(message = "must be a well formed email")
    String email,
    @NotBlank(message = "password must not be blank")
    String password
) {
}
