package br.com.emendes.powerkrtestapi.validation.validator;

import br.com.emendes.powerkrtestapi.model.TaskStatus;
import br.com.emendes.powerkrtestapi.validation.annotation.TaskStatusValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TaskStatusValidator implements ConstraintValidator<TaskStatusValidation, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if(value == null) return true;
    try {
      TaskStatus.valueOf(value);
      return true;
    } catch (IllegalArgumentException exception) {
      return false;
    }
  }

}
