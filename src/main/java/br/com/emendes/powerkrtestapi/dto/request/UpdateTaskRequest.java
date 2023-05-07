package br.com.emendes.powerkrtestapi.dto.request;

import br.com.emendes.powerkrtestapi.validation.annotation.DateTimeValidation;
import br.com.emendes.powerkrtestapi.validation.annotation.TaskStatusValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

/**
 * Record DTO para receber dados de atualização de tarefa no corpo da requisição.
 * @param title da tarefa
 * @param description da tarefa
 * @param creationDate da tarefa
 * @param status da tarefa
 */
@Builder
public record UpdateTaskRequest(
    @NotBlank(message = "title must not be blank")
    @Size(min = 1, max = 150, message = "title must contain between {min} and {max} characters")
    String title,
    @NotBlank(message = "description must not be blank")
    @Size(min = 1, max = 255, message = "description must contain between {min} and {max} characters")
    String description,
    @NotBlank(message = "creationDate must not be blank")
    @DateTimeValidation(message = "creationDate must be ISO 8601 format: yyyy-MM-ddTHH:mm:ss")
    String creationDate,
    @NotBlank(message = "status must not be blank")
    @TaskStatusValidation
    String status
) {
}
