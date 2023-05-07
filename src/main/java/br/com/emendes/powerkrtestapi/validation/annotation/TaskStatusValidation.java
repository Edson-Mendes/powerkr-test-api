package br.com.emendes.powerkrtestapi.validation.annotation;

import br.com.emendes.powerkrtestapi.validation.validator.TaskStatusValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * O elemento anotado deve ser uma {@code String} que pode ser convertido para {@code TaskStatus}<br>
 * Elementos {@code null} são considerados válidos.
 *
 * @author Edson Mendes
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TaskStatusValidator.class)
public @interface TaskStatusValidation {

  String message() default "status must be a valid TaskStatus (OPEN, CONCLUDED)";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
