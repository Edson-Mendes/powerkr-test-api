package br.com.emendes.powerkrtestapi.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record TaskResponse(
    Long id,
    String title,
    String description,
    LocalDateTime creationDate,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    LocalDateTime conclusionDate,
    String status
) {
}
