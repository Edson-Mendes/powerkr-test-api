package br.com.emendes.powerkrtestapi.handler;

import br.com.emendes.powerkrtestapi.exception.EmailAlreadyInUseException;
import br.com.emendes.powerkrtestapi.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.stream.Collectors;

/**
 * Controller Advice para tratamento de algumas exception que podem ocorrer na aplicação.
 */
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      @NonNull MethodArgumentNotValidException ex,
      @NonNull HttpHeaders headers,
      @NonNull HttpStatusCode status,
      @NonNull WebRequest request) {
    String fields = ex.getFieldErrors().stream().map(FieldError::getField)
        .collect(Collectors.joining("; "));
    String messages = ex.getFieldErrors().stream().map(FieldError::getDefaultMessage)
        .collect(Collectors.joining("; "));

    ProblemDetail problemDetail = ProblemDetail
        .forStatusAndDetail(HttpStatusCode.valueOf(400), "Some fields are invalid");

    problemDetail.setType(URI.create("https://github.com/Edson-Mendes/powerkr-test-api#campos-inv%C3%A1lidos"));
    problemDetail.setTitle("Invalid arguments");
    problemDetail.setProperty("fields", fields);
    problemDetail.setProperty("messages", messages);

    return ResponseEntity.badRequest().body(problemDetail);
  }

  @ExceptionHandler(EmailAlreadyInUseException.class)
  public ResponseEntity<ProblemDetail> handleEmailAlreadyInUse(EmailAlreadyInUseException ex) {
    int status = 409;
    ProblemDetail problemDetail = ProblemDetail
        .forStatusAndDetail(HttpStatusCode.valueOf(status), ex.getMessage());

    problemDetail.setType(URI.create("https://github.com/Edson-Mendes/powerkr-test-api#email-j%C3%A1-est%C3%A1-em-uso"));
    problemDetail.setTitle("Email conflict");

    return ResponseEntity.status(status).body(problemDetail);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleEmailAlreadyInUse(ResourceNotFoundException ex) {
    int status = 404;
    ProblemDetail problemDetail = ProblemDetail
        .forStatusAndDetail(HttpStatusCode.valueOf(status), ex.getMessage());

    problemDetail.setType(URI.create("https://github.com/Edson-Mendes/powerkr-test-api#recurso-n%C3%A3o-encontrado"));
    problemDetail.setTitle("Resource not found");

    return ResponseEntity.status(status).body(problemDetail);
  }

  @ExceptionHandler(BadCredentialsException.class)
  public ResponseEntity<ProblemDetail> handleBadCredentials(BadCredentialsException ex) {
    ProblemDetail problemDetail = ProblemDetail
        .forStatusAndDetail(HttpStatusCode.valueOf(400), "Incorrect email or password");

    problemDetail.setType(URI.create("https://github.com/Edson-Mendes/powerkr-test-api#credenciais-inv%C3%A1lidas"));
    problemDetail.setTitle(ex.getMessage());

    return ResponseEntity.badRequest().body(problemDetail);
  }

}
