package br.com.emendes.powerkrtestapi.validation.annotation;

import br.com.emendes.powerkrtestapi.validation.validator.DateTimeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * O elemento anotado deve ser uma {@code String} que pode ser convertido para
 * {@code LocalDateTime} no formato ISO 8601<br>
 * Elementos {@code null} ou {@code blank} são considerados válidos.
 * @author Edson Mendes
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DateTimeValidator.class)
public @interface DateTimeValidation {

  String message() default "Invalid Date Time";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
