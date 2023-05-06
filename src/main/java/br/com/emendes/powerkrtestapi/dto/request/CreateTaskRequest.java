package br.com.emendes.powerkrtestapi.dto.request;

import br.com.emendes.powerkrtestapi.validation.annotation.DateTimeValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CreateTaskRequest(
    @NotBlank(message = "title must not be blank")
    @Size(min = 1, max = 150, message = "name must contain between {min} and {max} characters")
    String title,
    @NotBlank(message = "description must not be blank")
    @Size(min = 1, max = 255, message = "name must contain between {min} and {max} characters")
    String description,
    @NotBlank(message = "creationDate must not be blank")
    @DateTimeValidation(message = "creationDate must be ISO 8601 format")
    String creationDate
) {
}
