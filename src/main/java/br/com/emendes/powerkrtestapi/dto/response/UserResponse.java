package br.com.emendes.powerkrtestapi.dto.response;

import lombok.Builder;

/**
 * Record DTO para enviar informações do Usuário para o cliente no corpo da resposta.
 * @param id do usuário
 * @param name do usuário
 * @param email do usuário
 */
@Builder
public record UserResponse(
    Long id,
    String name,
    String email
) {
}
