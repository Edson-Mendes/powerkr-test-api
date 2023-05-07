package br.com.emendes.powerkrtestapi.unit.dto.request;

import br.com.emendes.powerkrtestapi.dto.request.CreateUserRequest;
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

@DisplayName("Unit tests for CreateUserRequest")
class CreateUserRequestTest {

  private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

  @Nested
  @DisplayName("Tests for name validation")
  class NameValidation {

    @ParameterizedTest
    @ValueSource(strings = {
        "Lorem Ipsum", "Lorem",
        "LoremIpsumLoremIpsumLoremIpsumLoremIpsumLoremIpsumLoremIpsumLoremIpsumLoremIpsumLoremIpsumLoremIpsum"
    })
    @DisplayName("Validate name must not return violations when name is valid")
    void validateName_MustNotReturnViolations_WhenNameIsValid(String validName) {
      CreateUserRequest createUserRequest = CreateUserRequest.builder()
          .name(validName)
          .build();

      Set<ConstraintViolation<CreateUserRequest>> actualViolations = validator
          .validateProperty(createUserRequest, "name");

      Assertions.assertThat(actualViolations).isEmpty();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    @DisplayName("Validate name must return violations when name is blank")
    void validateName_MustReturnViolations_WhenNameIsBlank(String blankName) {
      CreateUserRequest createUserRequest = CreateUserRequest.builder()
          .name(blankName)
          .build();

      Set<ConstraintViolation<CreateUserRequest>> actualViolations = validator
          .validateProperty(createUserRequest, "name");

      List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

      Assertions.assertThat(actualViolations).isNotEmpty();
      Assertions.assertThat(actualMessages).contains("name must not be blank");
    }

    @Test
    @DisplayName("Validate name must return violations when name contains more than 100 characters")
    void validateName_MustReturnViolations_WhenNameContainsMoreThan100Characters() {
      String nameWithMoreThan150Characters = "namenamenamenamenamenamenamenamenamename" +
          "namenamenamenamenamenamenamenamenamenamel" +
          "namenamenamenamenamenamenamenamenamenamel";

      Assertions.assertThat(nameWithMoreThan150Characters).hasSizeGreaterThan(100);

      CreateUserRequest createUserRequest = CreateUserRequest.builder()
          .name(nameWithMoreThan150Characters)
          .build();

      Set<ConstraintViolation<CreateUserRequest>> actualViolations = validator
          .validateProperty(createUserRequest, "name");

      List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

      Assertions.assertThat(actualViolations).isNotEmpty();
      Assertions.assertThat(actualMessages).contains("name must contain between 2 and 100 characters");
    }

  }

  @Nested
  @DisplayName("Tests for email validation")
  class EmailValidation {

    @Test
    @DisplayName("Validate email must not return violations when email is valid")
    void validateEmail_MustNotReturnViolations_WhenEmailIsValid() {
      CreateUserRequest createUserRequest = CreateUserRequest.builder()
          .email("lorem@email.com")
          .build();

      Set<ConstraintViolation<CreateUserRequest>> actualViolations = validator
          .validateProperty(createUserRequest, "email");

      Assertions.assertThat(actualViolations).isEmpty();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    @DisplayName("Validate email must return violations when email is blank")
    void validateEmail_MustReturnViolations_WhenEmailIsBlank(String blankEmail) {
      CreateUserRequest createUserRequest = CreateUserRequest.builder()
          .email(blankEmail)
          .build();

      Set<ConstraintViolation<CreateUserRequest>> actualViolations = validator
          .validateProperty(createUserRequest, "email");

      List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

      Assertions.assertThat(actualViolations).isNotEmpty();
      Assertions.assertThat(actualMessages).contains("email must not be blank");
    }

    @ParameterizedTest
    @ValueSource(strings = {"lorememailcom", "lorem.com", "@email.com"})
    @DisplayName("Validate email must return violations when email is not well formed")
    void validateEmail_MustReturnViolations_WhenEmailIsNotWellFormed(String notWellFormedEmail) {
      CreateUserRequest createUserRequest = CreateUserRequest.builder()
          .email(notWellFormedEmail)
          .build();

      Set<ConstraintViolation<CreateUserRequest>> actualViolations = validator
          .validateProperty(createUserRequest, "email");

      List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

      Assertions.assertThat(actualViolations).isNotEmpty();
      Assertions.assertThat(actualMessages).contains("must be a well formed email");
    }

    @Test
    @DisplayName("Validate email must return violations when email contains more than 255 characters")
    void validateEmail_MustReturnViolations_WhenEmailContainsMoreThan255Characters() {
      String emailWithMoreThan255Characters = "loremloremloremloremloremloremloremloremloremlorem" +
          "loremloremloremloremloremloremloremloremloremlorem" +
          "loremloremloremloremloremloremloremloremloremlorem" +
          "loremloremloremloremloremloremloremloremloremlorem" +
          "loremloremloremloremloremloremloremloremloremlorem" +
          "@email.com";

      Assertions.assertThat(emailWithMoreThan255Characters).hasSizeGreaterThan(255);

      CreateUserRequest createUserRequest = CreateUserRequest.builder()
          .email(emailWithMoreThan255Characters)
          .build();

      Set<ConstraintViolation<CreateUserRequest>> actualViolations = validator
          .validateProperty(createUserRequest, "email");

      List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

      Assertions.assertThat(actualViolations).isNotEmpty();
      Assertions.assertThat(actualMessages).contains("email must contain max 255 characters");
    }

  }

  @Nested
  @DisplayName("Tests for password validation")
  class PasswordValidation {

    @Test
    @DisplayName("Validate password must not return violations when password is valid")
    void validatePassword_MustNotReturnViolations_WhenPasswordIsValid() {
      CreateUserRequest createUserRequest = CreateUserRequest.builder()
          .password("1234567890")
          .build();

      Set<ConstraintViolation<CreateUserRequest>> actualViolations = validator
          .validateProperty(createUserRequest, "password");

      Assertions.assertThat(actualViolations).isEmpty();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"   ", "\t", "\n"})
    @DisplayName("Validate password must return violations when password is blank")
    void validatePassword_MustReturnViolations_WhenPasswordIsBlank(String blankPassword) {
      CreateUserRequest createUserRequest = CreateUserRequest.builder()
          .password(blankPassword)
          .build();

      Set<ConstraintViolation<CreateUserRequest>> actualViolations = validator
          .validateProperty(createUserRequest, "password");

      List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

      Assertions.assertThat(actualViolations).isNotEmpty();
      Assertions.assertThat(actualMessages).contains("password must not be blank");
    }

    @Test
    @DisplayName("Validate password must return violations when password contains less than 6 characters")
    void validatePassword_MustReturnViolations_WhenPasswordContainsLessThan6Characters() {
      CreateUserRequest createUserRequest = CreateUserRequest.builder()
          .password("12345")
          .build();

      Set<ConstraintViolation<CreateUserRequest>> actualViolations = validator
          .validateProperty(createUserRequest, "password");

      List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

      Assertions.assertThat(actualViolations).isNotEmpty();
      Assertions.assertThat(actualMessages).contains("password must contain between 6 and 20 characters");
    }

    @Test
    @DisplayName("Validate password must return violations when password contains more than 20 characters")
    void validatePassword_MustReturnViolations_WhenPasswordContainsMoreThan20Characters() {
      CreateUserRequest createUserRequest = CreateUserRequest.builder()
          .password("123456789012345678900")
          .build();

      Set<ConstraintViolation<CreateUserRequest>> actualViolations = validator
          .validateProperty(createUserRequest, "password");

      List<String> actualMessages = actualViolations.stream().map(ConstraintViolation::getMessage).toList();

      Assertions.assertThat(actualViolations).isNotEmpty();
      Assertions.assertThat(actualMessages).contains("password must contain between 6 and 20 characters");
    }

  }

}