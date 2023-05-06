package br.com.emendes.powerkrtestapi.dto.response;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
    String token,
    String type
) {
}
