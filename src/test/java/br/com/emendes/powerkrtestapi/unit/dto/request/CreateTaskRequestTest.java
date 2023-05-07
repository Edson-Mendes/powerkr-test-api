package br.com.emendes.powerkrtestapi.unit.dto.request;

import br.com.emendes.powerkrtestapi.dto.request.CreateTaskRequest;
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

@DisplayName("Unit tests for CreateTaskRequest")
class CreateTaskRequestTest {
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
      CreateTaskRequest createTaskRequest = CreateTaskRequest.builder()
          .title(validTitle)
          .build();

      Set<ConstraintViolation<CreateTaskRequest>> actualViolations = validator
          .validateProperty(createTaskRequest, "title");

      Assertions.assertThat(actualViolations).isEmpty();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    @DisplayName("Validate title must return violations when title is blank")
    void validateTitle_MustReturnViolations_WhenTitleIsBlank(String blankTitle) {
      CreateTaskRequest createTaskRequest = CreateTaskRequest.builder()
          .title(blankTitle)
          .build();

      Set<ConstraintViolation<CreateTaskRequest>> actualViolations = validator
          .validateProperty(createTaskRequest, "title");

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

      CreateTaskRequest createTaskRequest = CreateTaskRequest.builder()
          .title(titleWithMoreThan150Characters)
          .build();

      Set<ConstraintViolation<CreateTaskRequest>> actualViolations = validator
          .validateProperty(createTaskRequest, "title");

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
      CreateTaskRequest createTaskRequest = CreateTaskRequest.builder()
          .description("Descrição da tarefa")
          .build();

      Set<ConstraintViolation<CreateTaskRequest>> actualViolations = validator
          .validateProperty(createTaskRequest, "description");

      Assertions.assertThat(actualViolations).isEmpty();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    @DisplayName("Validate description must return violations when description is blank")
    void validateDescription_MustReturnViolations_WhenDescriptionIsBlank(String blankDescription) {
      CreateTaskRequest createTaskRequest = CreateTaskRequest.builder()
          .description(blankDescription)
          .build();

      Set<ConstraintViolation<CreateTaskRequest>> actualViolations = validator
          .validateProperty(createTaskRequest, "description");

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

      CreateTaskRequest createTaskRequest = CreateTaskRequest.builder()
          .description(descriptionWithMoreThan255Characters)
          .build();

      Set<ConstraintViolation<CreateTaskRequest>> actualViolations = validator
          .validateProperty(createTaskRequest, "description");

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
      CreateTaskRequest createTaskRequest = CreateTaskRequest.builder()
          .creationDate("2023-05-07T10:00:00")
          .build();

      Set<ConstraintViolation<CreateTaskRequest>> actualViolations = validator
          .validateProperty(createTaskRequest, "creationDate");

      Assertions.assertThat(actualViolations).isEmpty();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    @DisplayName("Validate creationDate must return violations when creationDate is blank")
    void validateCreationDate_MustReturnViolations_WhenCreationDateIsBlank(String blankCreationDate) {
      CreateTaskRequest createTaskRequest = CreateTaskRequest.builder()
          .creationDate(blankCreationDate)
          .build();

      Set<ConstraintViolation<CreateTaskRequest>> actualViolations = validator
          .validateProperty(createTaskRequest, "creationDate");

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
      CreateTaskRequest createTaskRequest = CreateTaskRequest.builder()
          .creationDate(invalidDateTime)
          .build();

      Set<ConstraintViolation<CreateTaskRequest>> actualViolations = validator
          .validateProperty(createTaskRequest, "creationDate");

      List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

      Assertions.assertThat(actualViolations).isNotEmpty();
      Assertions.assertThat(actualMessages).contains("creationDate must be ISO 8601 format: yyyy-MM-ddTHH:mm:ss");
    }

  }

}