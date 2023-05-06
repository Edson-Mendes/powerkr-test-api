package br.com.emendes.powerkrtestapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CreateUserRequest(
    @NotBlank(message = "name must not be blank")
    @Size(min = 2, max = 100, message = "name must contain between {min} and {max} characters")
    String name,
    @NotBlank(message = "email must not be blank")
    @Email(message = "must be a well formed email")
    String email,
    @NotBlank(message = "password must not be blank")
    @Size(min = 8, max = 20, message = "password must contain between {min} and {max} characters")
    String password
) {
}
