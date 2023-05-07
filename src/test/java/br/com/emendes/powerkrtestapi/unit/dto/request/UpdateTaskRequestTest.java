package br.com.emendes.powerkrtestapi.unit.dto.request;

import br.com.emendes.powerkrtestapi.dto.request.UpdateTaskRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Set;

@DisplayName("Unit tests for UpdateTaskRequest")
class UpdateTaskRequestTest {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Nested
  @DisplayName("Tests for title validation")
  class TitleValidation {

    @ParameterizedTest
    @ValueSource(strings = {
        "Tarefa xpto", "ta",
        "Title With 100 Characters!Title With 100 Characters!Title With 100 Characters!Title With 100 Characters!"
    })
    @DisplayName("Validate title must not return violations when title is valid")
    void validateTitle_MustNotReturnViolations_WhenTitleIsValid(String validTitle) {
      UpdateTaskRequest updateTaskRequest = UpdateTaskRequest.builder()
          .title(validTitle)
          .build();

      Set<ConstraintViolation<UpdateTaskRequest>> actualViolations = validator
          .validateProperty(updateTaskRequest, "title");

      Assertions.assertThat(actualViolations).isEmpty();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    @DisplayName("Validate title must return violations when title is blank")
    void validateTitle_MustReturnViolations_WhenTitleIsBlank(String blankTitle) {
      UpdateTaskRequest updateTaskRequest = UpdateTaskRequest.builder()
          .title(blankTitle)
          .build();

      Set<ConstraintViolation<UpdateTaskRequest>> actualViolations = validator
          .validateProperty(updateTaskRequest, "title");

      List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

      Assertions.assertThat(actualViolations).isNotEmpty();
      Assertions.assertThat(actualMessages).contains("title must not be blank");
    }

    @Test
    @DisplayName("Validate title must return violations when title contains more than 150 characters")
    void validateTitle_MustReturnViolations_WhenTitleContainsMoreThan150Characters() {
      String titleWithMoreThan150Characters = "titletitletitletitletitletitletitletitletitletitle" +
          "titletitletitletitletitletitletitletitletitletitlel" +
          "titletitletitletitletitletitletitletitletitletitlel";

      Assertions.assertThat(titleWithMoreThan150Characters).hasSizeGreaterThan(150);

      UpdateTaskRequest updateTaskRequest = UpdateTaskRequest.builder()
          .title(titleWithMoreThan150Characters)
          .build();

      Set<ConstraintViolation<UpdateTaskRequest>> actualViolations = validator
          .validateProperty(updateTaskRequest, "title");

      List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

      Assertions.assertThat(actualViolations).isNotEmpty();
      Assertions.assertThat(actualMessages).contains("title must contain between 1 and 150 characters");
    }

  }

  @Nested
  @DisplayName("Tests for description validation")
  class DescriptionValidation {

    @Test
    @DisplayName("Validate description must not return violations when description is valid")
    void validateDescription_MustNotReturnViolations_WhenDescriptionIsValid() {
      UpdateTaskRequest updateTaskRequest = UpdateTaskRequest.builder()
          .description("Descrição da tarefa")
          .build();

      Set<ConstraintViolation<UpdateTaskRequest>> actualViolations = validator
          .validateProperty(updateTaskRequest, "description");

      Assertions.assertThat(actualViolations).isEmpty();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    @DisplayName("Validate description must return violations when description is blank")
    void validateDescription_MustReturnViolations_WhenDescriptionIsBlank(String blankDescription) {
      UpdateTaskRequest updateTaskRequest = UpdateTaskRequest.builder()
          .description(blankDescription)
          .build();

      Set<ConstraintViolation<UpdateTaskRequest>> actualViolations = validator
          .validateProperty(updateTaskRequest, "description");

      List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

      Assertions.assertThat(actualViolations).isNotEmpty();
      Assertions.assertThat(actualMessages).contains("description must not be blank");
    }

    @Test
    @DisplayName("Validate description must return violations when description contains more than 255 characters")
    void validateDescription_MustReturnViolations_WhenDescriptionContainsMoreThan100Characters() {
      String descriptionWithMoreThan255Characters = "Descrição da tarefa!Descrição da tarefa!" +
          "Descrição da tarefa!Descrição da tarefa!Descrição da tarefa!Descrição da tarefa!" +
          "Descrição da tarefa!Descrição da tarefa!Descrição da tarefa!Descrição da tarefa!" +
          "Descrição da tarefa!Descrição da tarefa!Descrição da tarefa!Descrição da tarefa!!!!!!!";

      Assertions.assertThat(descriptionWithMoreThan255Characters).hasSizeGreaterThan(255);

      UpdateTaskRequest updateTaskRequest = UpdateTaskRequest.builder()
          .description(descriptionWithMoreThan255Characters)
          .build();

      Set<ConstraintViolation<UpdateTaskRequest>> actualViolations = validator
          .validateProperty(updateTaskRequest, "description");

      List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

      Assertions.assertThat(actualViolations).isNotEmpty();
      Assertions.assertThat(actualMessages).contains("description must contain between 1 and 255 characters");
    }

  }

  @Nested
  @DisplayName("Tests for creationDate validation")
  class CreationDateValidation {

    @Test
    @DisplayName("Validate creationDate must not return violations when creationDate is valid")
    void validateCreationDate_MustNotReturnViolations_WhenCreationDateIsValid() {
      UpdateTaskRequest updateTaskRequest = UpdateTaskRequest.builder()
          .creationDate("2023-05-07T10:00:00")
          .build();

      Set<ConstraintViolation<UpdateTaskRequest>> actualViolations = validator
          .validateProperty(updateTaskRequest, "creationDate");

      Assertions.assertThat(actualViolations).isEmpty();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    @DisplayName("Validate creationDate must return violations when creationDate is blank")
    void validateCreationDate_MustReturnViolations_WhenCreationDateIsBlank(String blankCreationDate) {
      UpdateTaskRequest updateTaskRequest = UpdateTaskRequest.builder()
          .creationDate(blankCreationDate)
          .build();

      Set<ConstraintViolation<UpdateTaskRequest>> actualViolations = validator
          .validateProperty(updateTaskRequest, "creationDate");

      List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

      Assertions.assertThat(actualViolations).isNotEmpty();
      Assertions.assertThat(actualMessages).contains("creationDate must not be blank");
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "07/05/2023 10:00:00", "20230507100000"
    })
    @DisplayName("Validate creationDate must return violations when creationDate is invalid")
    void validateCreationDate_MustReturnViolations_WhenCreationDateIsInvalid(String invalidDateTime) {
      UpdateTaskRequest updateTaskRequest = UpdateTaskRequest.builder()
          .creationDate(invalidDateTime)
          .build();

      Set<ConstraintViolation<UpdateTaskRequest>> actualViolations = validator
          .validateProperty(updateTaskRequest, "creationDate");

      List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

      Assertions.assertThat(actualViolations).isNotEmpty();
      Assertions.assertThat(actualMessages).contains("creationDate must be ISO 8601 format: yyyy-MM-ddTHH:mm:ss");
    }

  }

  @Nested
  @DisplayName("Tests for status validation")
  class StatusValidation {

    @ParameterizedTest
    @ValueSource(strings = {
        "OPEN", "CONCLUDED"
    })
    @DisplayName("Validate status must not return violations when status is valid")
    void validateStatus_MustNotReturnViolations_WhenStatusIsValid(String validStatus) {
      UpdateTaskRequest updateTaskRequest = UpdateTaskRequest.builder()
          .status(validStatus)
          .build();

      Set<ConstraintViolation<UpdateTaskRequest>> actualViolations = validator
          .validateProperty(updateTaskRequest, "status");

      Assertions.assertThat(actualViolations).isEmpty();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    @DisplayName("Validate status must return violations when status is blank")
    void validateStatus_MustReturnViolations_WhenStatusIsBlank(String blankAge) {
      UpdateTaskRequest updateTaskRequest = UpdateTaskRequest.builder()
          .status(blankAge)
          .build();

      Set<ConstraintViolation<UpdateTaskRequest>> actualViolations = validator
          .validateProperty(updateTaskRequest, "status");

      List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

      Assertions.assertThat(actualViolations).isNotEmpty();
      Assertions.assertThat(actualMessages).contains("status must not be blank");
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "open", "concluded", "Open", "Concluded"
    })
    @DisplayName("Validate status must return violations when status is invalid")
    void validateStatus_MustReturnViolations_WhenStatusIsInvalid(String invalidDateTime) {
      UpdateTaskRequest updateTaskRequest = UpdateTaskRequest.builder()
          .status(invalidDateTime)
          .build();

      Set<ConstraintViolation<UpdateTaskRequest>> actualViolations = validator
          .validateProperty(updateTaskRequest, "status");

      List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

      Assertions.assertThat(actualViolations).isNotEmpty();
      Assertions.assertThat(actualMessages).contains("status must be a valid TaskStatus (OPEN, CONCLUDED)");
    }

  }

}