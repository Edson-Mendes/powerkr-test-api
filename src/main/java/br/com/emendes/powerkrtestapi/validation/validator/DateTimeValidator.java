package br.com.emendes.powerkrtestapi.validation.validator;

import br.com.emendes.powerkrtestapi.validation.annotation.DateTimeValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class DateTimeValidator implements ConstraintValidator<DateTimeValidation, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if (value == null || value.isBlank()) return true;

    try {
      LocalDateTime.parse(value);
      return true;
    } catch (DateTimeParseException ex){
      return false;
    }
  }

}
