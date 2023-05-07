package br.com.emendes.powerkrtestapi.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDateTime;

/**
 * Record DTO para enviar informações da Tarefa para o cliente no corpo da resposta.
 * @param id da tarefa
 * @param title da tarefa
 * @param description da tarefa
 * @param creationDate da tarefa
 * @param conclusionDate da tarefa, só é enviado se a tarefa estiver com status CONCLUDED
 * @param status da tarefa (OPEN ou CONCLUDED)
 */
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
