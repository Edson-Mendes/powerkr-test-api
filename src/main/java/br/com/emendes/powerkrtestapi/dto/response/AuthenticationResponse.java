package br.com.emendes.powerkrtestapi.dto.response;

import lombok.Builder;

/**
 * Record DTO para enviar JWT de autenticação do usuário no corpo da resposta.
 * @param token JWT
 * @param type tipo do JWT
 */
@Builder
public record AuthenticationResponse(
    String token,
    String type
) {
}
