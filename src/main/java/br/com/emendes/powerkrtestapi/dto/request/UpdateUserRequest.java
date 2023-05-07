package br.com.emendes.powerkrtestapi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

/**
 * Record DTO para receber dados de atualização de usuário no corpo da requisição.
 * @param name do usuário
 * @param email do usuário
 */
@Builder
public record UpdateUserRequest(
    @NotBlank(message = "name must not be blank")
    @Size(min = 2, max = 100, message = "name must contain between {min} and {max} characters")
    String name,
    @NotBlank(message = "email must not be blank")
    @Email(message = "must be a well formed email")
    @Size(max = 255, message = "email must max {max} characters")
    String email
) {
}
