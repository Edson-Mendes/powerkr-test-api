package br.com.emendes.powerkrtestapi.dto.response;

import lombok.Builder;

@Builder
public record UserResponse(
    Long id,
    String name,
    String email
) {
}
